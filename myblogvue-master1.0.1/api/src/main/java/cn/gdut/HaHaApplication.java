package cn.gdut;

import cn.gdut.proxy.aop.service.UserService1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
@EnableAspectJAutoProxy
@ComponentScan("cn.gdut.*")
public class HaHaApplication {
    // 放到
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(HaHaApplication.class);
        UserService1 userService1 = annotationConfigApplicationContext.getBean(UserService1.class);
        userService1.login();
    }
}
