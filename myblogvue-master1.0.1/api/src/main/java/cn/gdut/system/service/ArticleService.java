package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysArticle;
import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArticleService extends IService<SysArticle> {

    List<SysArticle> findAll();

    IPage<SysArticle> findByPage(SysArticle article, QueryPage queryPage);

    /**
     * 添加文章
     * @param article
     */
    void add(SysArticle article);

    void delete(Long id);

    SysArticle findById(Long id);

    void update(SysArticle article);
}
