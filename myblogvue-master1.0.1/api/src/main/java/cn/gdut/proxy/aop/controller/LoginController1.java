package cn.gdut.proxy.aop.controller;

import cn.gdut.proxy.aop.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
@RestController
public class LoginController1 {

    @Autowired
    UserService1 userService;

    @RequestMapping("/login1")
    public boolean login() throws InterruptedException {
        userService.login();
        return true;
    }
}
