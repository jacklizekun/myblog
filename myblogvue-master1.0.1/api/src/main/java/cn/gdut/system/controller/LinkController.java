package cn.gdut.system.controller;

import cn.gdut.common.controller.BaseController;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysLink;
import cn.gdut.system.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desctiption TODO
 * @Date 2019/11/20/020
 **/
@RestController
@RequestMapping("/api/link")
public class LinkController extends BaseController {

    @Autowired
    LinkService linkService;

    @RequestMapping("/list")
    public R list(@RequestBody SysLink link, QueryPage queryPage){
        return new R<>(super.getData(linkService.findByPage(link, queryPage)));
    }

    @PostMapping
    public R add(@RequestBody SysLink link){
        linkService.save(link);
        return new R<>();
    }

    @PutMapping
    public R update(@RequestBody SysLink link){
        try {
            linkService.updateById(link);
            return new  R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }
}
