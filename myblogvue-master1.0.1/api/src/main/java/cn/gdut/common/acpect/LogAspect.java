package cn.gdut.common.acpect;

import cn.gdut.common.exception.GlobalException;
import cn.gdut.system.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@Aspect
@Component
public class LogAspect {

    @Autowired
    LogService logService;

    // 申明切入点,这里用注解完成，表示注解有Log的就是一个接入点
    @Pointcut("@annotation(cn.gdut.common.annotation.Log)")
    public void pointcut(){

    }

    // 配置通知的类型，
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        Object res = null;
        // 这里可以获取执行方法的参数
        try {
            res = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }

        // 日志记录交给LogServiceImpl完成
        logService.saveLog(proceedingJoinPoint);

        return res;
    }
}
