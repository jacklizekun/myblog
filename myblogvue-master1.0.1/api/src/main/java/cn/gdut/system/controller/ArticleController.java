package cn.gdut.system.controller;

import cn.gdut.common.annotation.Log;
import cn.gdut.common.controller.BaseController;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysArticle;
import cn.gdut.system.entity.SysCategory;
import cn.gdut.system.service.ArticleService;
import cn.gdut.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@RestController
@RequestMapping("/api/article")
public class ArticleController extends BaseController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/findAll")
    public R findAll(){
        return new R<>(articleService.findAll());
    }

    @GetMapping({"/listForSite/page/{id}", "/listForSite"})
    public R listForSite(@PathVariable(required = false) String p){
        // 当前页
        if (StringUtils.isBlank(p)){
            p = "1";
        }
        // 查找发表状态的文章，分页显示
        IPage<SysArticle> page = new Page<>(Integer.valueOf(p), 12);
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysArticle::getId);
        // 发表状态
        queryWrapper.eq(SysArticle::getState,"1");
        IPage<SysArticle> list = articleService.page(page, queryWrapper);
        list.getRecords().forEach(article -> {
            // 将文章的html展示位普通文本
            String htmlContext = article.getContent();
            String context = Jsoup.parse(htmlContext).text();
            if (context.length() > 80){
                context = context.substring(0,40) + "...";
                article.setContent(context);
                article.setContentMd(null);
            }
            // 展示分类信息
            if (StringUtils.isNotBlank(article.getCategory())){
                SysCategory category = categoryService.getById(article.getCategory());
                if (category != null){
                    article.setCategory(category.getName());
                }
                else {
                    article.setCategory(null);
                }
            }
        });
        Map<String, Object> data = super.getData(list);
        data.put("current",list.getCurrent());
        data.put("pages", list.getPages());
        return new R<>(data);
    }

    @PostMapping("list")
    public R list(@RequestBody  SysArticle article,QueryPage queryPage){
        IPage<SysArticle> page = articleService.findByPage(article, queryPage);
        return new R<>(super.getData(page));
    }

    /**
     * 在控制层抛出异常。并处理
     * @param article
     * @return
     */
    @PostMapping
    @Log("文章添加")
    public R add(@RequestBody SysArticle article){

        try {
            articleService.add(article);
            return new R<>();
        }catch (Exception e){
            throw  new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("文章删除")
    public R delete(@PathVariable("id") Long id){
        try {
            articleService.delete(id);
            return new R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable("id") Long id){
        return new R<>(articleService.findById(id)) ;
    }

    @PutMapping
    @Log("文章更新")
    public R update(@RequestBody SysArticle article){
        try {
            articleService.update(article);
            return new R<>();
        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }



}
