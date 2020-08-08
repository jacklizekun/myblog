package cn.gdut.system.service.impl;

import cn.gdut.system.entity.ArticleCategory;
import cn.gdut.system.entity.SysCategory;
import cn.gdut.system.mapper.ArticleCategoryMapper;
import cn.gdut.system.service.ArticleCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desctiption TODO
 * @Date 2019/11/22/022
 **/
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;


    @Override
    public void deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategory::getArticleId, articleId);
        articleCategoryMapper.delete(wrapper);
    }

    @Override
    public void findByArticleId(Long articleId) {

    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        LambdaQueryWrapper<ArticleCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategory::getCategoryId, categoryId);
        articleCategoryMapper.delete(wrapper);
    }
}
