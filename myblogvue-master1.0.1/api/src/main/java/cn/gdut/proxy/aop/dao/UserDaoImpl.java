package cn.gdut.proxy.aop.dao;

import org.springframework.stereotype.Repository;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void login() throws InterruptedException {
        Thread.sleep(200);
    }
}
