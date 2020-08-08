package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface CommentService extends IService<SysComment> {
    List<SysComment> findAll();

    /**
     * 根据信息查找分页信息
     * @param comment
     * @param queryPage
     * @return
     */
    IPage<SysComment> findByPage(SysComment comment, QueryPage queryPage);

    /**
     * 添加
     * @param comment
     */
    void add(SysComment comment);

    /**
     * 分页查询评论数据
     * @param queryPage
     * @param articleId 当前文章Id
     * @param sort 分类依据， 0 表示文章详情页的评论信息，1 表示友联评论信息， 2 表示关于我的评论信息
     * @return
     */
    Map<String, Object> findCommentList(QueryPage queryPage, String articleId, int sort);

    void delete(String id);

    void deleteByArticleId(Long articleId);
}
