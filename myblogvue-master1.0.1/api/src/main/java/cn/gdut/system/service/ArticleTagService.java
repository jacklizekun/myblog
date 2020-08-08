package cn.gdut.system.service;

import cn.gdut.system.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 根据文章id查找
     * @param articleId
     * @return
     */
    void deleteByArticleId(Long articleId);

    void deleteByTagId(Long tagId);
}
