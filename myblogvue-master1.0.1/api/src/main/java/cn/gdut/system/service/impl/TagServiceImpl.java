package cn.gdut.system.service.impl;

import cn.gdut.common.annotation.Log;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysTag;
import cn.gdut.system.mapper.TagMapper;
import cn.gdut.system.service.ArticleTagService;
import cn.gdut.system.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desctiption TODO
 * @Date 2019/11/20/020
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, SysTag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleTagService articleTagService;

    @Override
    public IPage<SysTag> findByPage(SysTag tag, QueryPage queryPage) {
        IPage<SysTag> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(tag.getName()),SysTag::getName, tag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysTag> findAll() {
        return tagMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<SysTag> findByArticleId(Long id) {
        return tagMapper.findByArticleId(id);
    }

    /**
     * 1 删除tags表
     * 2 删除tag-article表中的管理信息
     * @param id
     */

    @Override
    public void delete(Long id) {
        tagMapper.deleteById(id);
        // 删除tags-article表
        articleTagService.deleteByTagId(id);
    }


    @Override
    public void add(SysTag tag) {
        tagMapper.insert(tag);
    }
}
