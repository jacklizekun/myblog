package cn.gdut.system.service.impl;

import cn.gdut.system.entity.ArticleTag;
import cn.gdut.system.mapper.ArticleTagMapper;
import cn.gdut.system.service.ArticleTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desctiption TODO
 * @Date 2019/11/22/022
 **/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public void deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, articleId);
        articleTagMapper.delete(wrapper);
    }

    @Override
    public void deleteByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getTagId, tagId);
        articleTagMapper.delete(wrapper);
    }
}
