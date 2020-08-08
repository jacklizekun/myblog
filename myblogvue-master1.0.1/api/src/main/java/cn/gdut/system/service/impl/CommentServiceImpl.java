package cn.gdut.system.service.impl;

import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.common.utils.TreeUtils;
import cn.gdut.system.entity.SysComment;
import cn.gdut.system.entity.dto.Tree;
import cn.gdut.system.mapper.CommentMapper;
import cn.gdut.system.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, SysComment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public List<SysComment> findAll() {
        return commentMapper.findAll("1",new QueryPage(0,12));
    }

    @Override
    public IPage<SysComment> findByPage(SysComment comment, QueryPage queryPage) {
        IPage<SysComment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysComment> queryWrapper  = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(comment.getName()), SysComment::getName, comment.getName());
        queryWrapper.like(!StringUtils.isBlank(comment.getIp()),SysComment::getIp,comment.getIp());
        return commentMapper.selectPage(page, queryWrapper);

    }

    @Override
    public void add(SysComment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public Map<String, Object> findCommentList(QueryPage queryPage, String articleId, int sort) {
        // 查询依据，根据文章id。排序顺序。降序
        LambdaQueryWrapper<SysComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(articleId), SysComment::getArticleId, articleId);
        wrapper.eq(SysComment::getSort, sort );
        wrapper.orderByDesc(SysComment::getId);
        List<SysComment> list = commentMapper.selectList(wrapper);

        // 先查询所有的数据，存放在Tree中
        List<Tree<SysComment>> trees = new ArrayList<>();
        list.forEach(comment -> {
            Tree<SysComment> tree = new Tree<>();
            tree.setId(comment.getId());
            tree.setAId(comment.getArticleId());
            tree.setSort(comment.getSort());
            tree.setContent(comment.getContent());
            tree.setName(comment.getName());
            tree.setUrl(comment.getUrl());
            tree.setPId(comment.getPId());
            tree.setTime(comment.getTime());
            tree.setTarget(comment.getCName());
            tree.setDevice(comment.getDevice());
            trees.add(tree);
        });

        // 返回map数据
        Map<String, Object> map = new HashMap<>();
        try{
            /* 根据列表构建树 */
            List<Tree<SysComment>> treeList = TreeUtils.build(trees);
             if (treeList == null){
                 treeList = new ArrayList<>();
             }

             if (treeList.size() == 0){
                 map.put("rows", new ArrayList<>());
             }

             else {
                 int start = (queryPage.getPage() - 1) * queryPage.getLimit();
                 int end = queryPage.getPage() * queryPage.getLimit();
                 if (queryPage.getPage() * queryPage.getLimit() >= treeList.size()) {
                     end = treeList.size();
                 }
                 map.put("rows", treeList.subList(start, end));
             }
            map.put("count", list.size());
            map.put("total", treeList.size());
            map.put("current", queryPage.getPage());
            map.put("pages", (int) Math.ceil((double) treeList.size() / (double) queryPage.getLimit()));

        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }

        return map;
    }

    @Override
    public void delete(String id) {
        commentMapper.deleteById(id);
    }

    @Override
    public void deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<SysComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysComment::getArticleId, articleId);
        commentMapper.delete(wrapper);
    }


}
