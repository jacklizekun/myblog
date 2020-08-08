package cn.gdut.proxy.aop.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Pointcut("execution(* cn.gdut.proxy.aop.service.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(){
        System.out.println("完成");
    }

    @Around("execution(* cn.gdut.proxy.aop.service.*.*(..))")
    public void aroud(ProceedingJoinPoint proceedingJoinPoint){
        long start = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end - start));

    }

}
