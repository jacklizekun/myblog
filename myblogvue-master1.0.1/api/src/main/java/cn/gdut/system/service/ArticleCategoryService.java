package cn.gdut.system.service;

import cn.gdut.system.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Desctiption TODO
 * @Date 2019/11/22/022
 **/
public interface ArticleCategoryService extends IService<ArticleCategory> {

    void deleteByArticleId(Long articleId);

    void findByArticleId(Long articleId);

    void deleteByCategoryId(Long categoryId);
}
