package cn.gdut.system.service.impl;

import cn.gdut.common.annotation.Log;
import cn.gdut.common.constant.CommonConstant;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.*;
import cn.gdut.system.mapper.ArticleMapper;
import cn.gdut.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, SysArticle> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleCategoryService articleCategoryService;

    @Autowired
    ArticleTagService articleTagService;

    @Autowired
    CommentService commentService;

    @Override
    public List<SysArticle> findAll() {
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysArticle::getId);
        queryWrapper.eq(SysArticle::getState,"1");
        Page<SysArticle> page = new Page<>(0, 8);

        List<SysArticle> records = articleMapper.selectPage(page, queryWrapper).getRecords();
        return records;
    }

    @Override
    public IPage<SysArticle> findByPage(SysArticle article, QueryPage queryPage) {
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        IPage<SysArticle> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        queryWrapper.eq(!StringUtils.isBlank(article.getCategory()), SysArticle::getCategory, article.getCategory());
        queryWrapper.like(!StringUtils.isBlank(article.getTitle()),SysArticle::getTitle, article.getTitle());
        IPage<SysArticle> page1 = articleMapper.selectPage(page, queryWrapper);
        initArticle(page1.getRecords());
        return page1;
    }

    /**
     * 添加文章
     * 发表的状态，为空，则默认未发表状态
     * 设置发表时间(状态为发表 并且发表时间为空)
     * 设置发表的人
     * 得到插入的当前id。并设置id
     * @param article
     */
    @Override
    public void add(SysArticle article) {
        try {
            if (article.getState() == null){
                article.setState(CommonConstant.DEFAULT_DEAFT_STATUS);
            }
            // 发表时间
            if (article.getPublishTime() == null){
                article.setPublishTime(new Date());
            }
            // 设置编辑时间
            article.setEditTime(new Date());
            article.setCreateTime(new Date());
            // 设置作者
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            article.setAuthor(user.getUsername());
            //插入文章
            articleMapper.insert(article);
            article.setId(article.getId());
            // 同步处理分类个tag之间的关系
            this.updateArticleCategory(article);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null && id != 0){
            // 删除当前的文章
            articleMapper.deleteById(id);
            // 删除分类信息和tag
            articleTagService.deleteByArticleId(id);
            articleCategoryService.deleteByArticleId(id);
            // 删除评论
            commentService.deleteByArticleId(id);

        }


    }

    @Override
    public SysArticle findById(Long id) {
        SysArticle sysArticle = articleMapper.selectById(id);
        ArrayList<SysArticle> sysArticles = new ArrayList<>();
        sysArticles.add(sysArticle);
        initArticle(sysArticles);
        return sysArticle;
    }

    @Override
    public void update(SysArticle article) {
        if (article.getPublishTime() == null && article.getState().equals("1")){
            article.setPublishTime(new Date());
        }
        // 更新
        articleMapper.updateById(article);
        // 更新其他表
        updateArticleCategory(article);
    }

    /**
     * 更新文章-分类-标签三表
     * 1.如果分类不为空，先删除article-category表
     * 2 根据分类id找到分类信息
     * 3 article-category表新增当条记录信息
     * Tags 一样处理
     * @param sysArticle
     */
    private void updateArticleCategory(SysArticle sysArticle){
        // 判空
        if (sysArticle.getId() != null){
            //
            if (sysArticle.getCategory() != null){
                // 删除article-category表的记录
                articleCategoryService.deleteByArticleId(sysArticle.getId());
                // 添加新的分类信息
                SysCategory category = categoryService.getById(sysArticle.getCategory());
                if (category != null){
                    // 将分类信息插入到article-category表中
                    articleCategoryService.save(new ArticleCategory(sysArticle.getId(),category.getId()));
                }

                // Tags更新
                if (sysArticle.getTags() != null && sysArticle.getTags().size() > 0){
                    // 删除articletags的记录
                    articleTagService.deleteByArticleId(sysArticle.getId());
                    //往article-category表中添加
                    sysArticle.getTags().forEach(tag -> {
                        articleTagService.save(new ArticleTag(sysArticle.getId(), tag.getId()));
                    });
                }
            }
        }
    }

    /**
     * 封装展示的数据，报货分类信息，标签信息
     * @param articles
     */
    private void initArticle(List<SysArticle> articles){
        if (!articles.isEmpty()){
            // 循环获取分类信息和标签信息
            articles.forEach(article -> {
                // 设置分类
//                SysCategory category = categoryService.findById(Long.valueOf(article.getCategory()));
                List<SysCategory> categoryList = categoryService.findByArticleId(article.getId());
                if (categoryList.size() > 0){
                    article.setCategory(categoryList.get(0).getName());
                }

                // 设置tags
                // 先查找当前文章所有的tags
                List<SysTag> tagList = tagService.findByArticleId(article.getId());
                ArrayList<String> strList = new ArrayList<>();
                tagList.forEach(tag -> {
                    strList.add(tag.getName());
                });

                article.setTags(tagList);

            });
        }
    }
}
