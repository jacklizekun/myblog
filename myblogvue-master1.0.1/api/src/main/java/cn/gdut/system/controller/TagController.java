package cn.gdut.system.controller;

import cn.gdut.common.annotation.Log;
import cn.gdut.common.controller.BaseController;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysTag;
import cn.gdut.system.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desctiption TODO
 * @Date 2019/11/20/020
 **/
@RestController
@RequestMapping("/api/tag")
public class TagController extends BaseController {

    @Autowired
    TagService tagService;

    @PostMapping("/list")
    public R list(@RequestBody SysTag tag, QueryPage queryPage){
        return new R<>(super.getData(tagService.findByPage(tag, queryPage)));
    }

    @PostMapping
    @Log("增加分类")
    public R add(@RequestBody SysTag tag){
        tagService.add(tag);
        return new R<>();
    }

    @PutMapping
    public R update(@RequestBody SysTag tag){
        try {
            tagService.updateById(tag);
            return new R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public R findAll(){
        return new R<>(tagService.findAll());
    }

    @DeleteMapping("/{id}")
    @Log("删除分类")
    public R delete(@PathVariable Long id){
        try {
            tagService.delete(id);
            return new R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }
}
