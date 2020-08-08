package cn.gdut.system.controller;

import cn.gdut.common.controller.BaseController;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysCategory;
import cn.gdut.system.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desctiption TODO
 * @Date 2019/11/20/020
 **/
@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/list")
    public R list(@RequestBody SysCategory category, QueryPage queryPage){
        return new R<>(super.getData(categoryService.fingByPage(category, queryPage))) ;
    }

    @GetMapping("/findAll")
    public R findAll(){
        return new R<>(categoryService.findAll());
    }

    @PostMapping
    public R add(@RequestBody SysCategory category){
        categoryService.save(category);
        return new R<>();
    }


    @PutMapping
    public R update(@RequestBody SysCategory sysCategory){
        try {
            categoryService.updateById(sysCategory);
            return new  R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        try {
            categoryService.delete(id);
            return new R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }

    }
}
