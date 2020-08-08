package cn.gdut.system.controller;

import cn.gdut.common.controller.BaseController;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysLoginLog;
import cn.gdut.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@RequestMapping("/api/loginlog")
@RestController
public class LoginLogController extends BaseController {

    @Autowired
    LoginLogService loginLogService;

    @PostMapping("/list")
    public R list(SysLoginLog loginLog, QueryPage queryPage){
        return new R<>(super.getData(loginLogService.findByPage(loginLog, queryPage)));
    }

}
