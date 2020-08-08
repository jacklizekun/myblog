package cn.gdut.system.controller;

import cn.gdut.common.controller.BaseController;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysUser;
import cn.gdut.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desctiption TODO
 * @Date 2019/11/19/019
 **/
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public R getInfo() {
        SysUser user = this.getCurrentUser();
        return new R<>(user);
    }


}
