package cn.gdut.system.controller;

import cn.gdut.common.controller.BaseController;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysLog;
import cn.gdut.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@RestController
@RequestMapping("/api/log")
public class LogController extends BaseController {

    @Autowired
    LogService logService;

    @RequestMapping("/list")
    public R list(SysLog sysLog, QueryPage queryPage){
        return new R<>(super.getData(logService.findByPage(sysLog, queryPage))) ;
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        try {
            logService.delete(id);
            return new R <>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }
}
