package cn.gdut.proxy.cjlib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
public class ProxyFactory implements MethodInterceptor {

    // 维护目标对象
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    // 给目标对象创建一个代理对象
    public Object getProxyInstance(){
        // 1 工具类
        Enhancer enhancer = new Enhancer();
        // 2 设置父类
        enhancer.setSuperclass(target.getClass());
        // 3 设置回调函数
        enhancer.setCallback(this);
        // 4 创建子类（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事物");
        Object retuenValue = method.invoke(target, args);
        System.out.println("提交事物");
        return retuenValue;
    }
}
