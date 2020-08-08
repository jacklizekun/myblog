package cn.gdut.system.service.impl;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysCategory;
import cn.gdut.system.mapper.CategoryMapper;
import cn.gdut.system.service.ArticleCategoryService;
import cn.gdut.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, SysCategory> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ArticleCategoryService articleCategoryService;

    @Override
    public SysCategory findById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public IPage<SysCategory> fingByPage(SysCategory category, QueryPage queryPage) {
        IPage<SysCategory> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(category.getName()), SysCategory::getName, category.getName());
        return categoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysCategory> findAll() {
        return categoryMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<SysCategory> findByArticleId(Long id) {
        return categoryMapper.findCategoryByArticleId(id);
    }

    /**
     * 删除分类
     * 1 删除分类表
     * 2 删除article-category
     * @param id
     */
    @Override
    public void delete(Long id) {
        // 删除category表
        categoryMapper.deleteById(id);
        // 删除article-category表
        articleCategoryService.deleteByCategoryId(id);

    }


}
