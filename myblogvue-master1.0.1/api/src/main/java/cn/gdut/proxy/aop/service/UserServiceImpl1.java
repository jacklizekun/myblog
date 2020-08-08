package cn.gdut.proxy.aop.service;

import cn.gdut.proxy.aop.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
@Service
public class UserServiceImpl1 implements UserService1 {
    @Autowired
    UserDao userDao;

    @Override
    public void login() throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("登录成功");
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
