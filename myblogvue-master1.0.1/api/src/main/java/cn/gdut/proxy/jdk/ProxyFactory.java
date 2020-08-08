package cn.gdut.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Desctiption TODO
 * 创建动态代理对象
 * 动态代理不需要实现接口，但是需要指定接口类型
 * @Date 2019/11/27/027
 **/
public class ProxyFactory {

    /*// 维护一个目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target = target;
    }

    // 给目标对象生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事物");
                        // 执行目标方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("提交事物");
                        return returnValue;
                    }
                }
        );
    }*/

    // 目标代理对象
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }
    // 使用JDK动态代理
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                // ClassLoader 被代理类加载器
                target.getClass().getClassLoader(),
                // 被代理类的的接口数组
                target.getClass().getInterfaces(),
                // 用于增强的代码,调用处理类的实例
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事物");
                        Object returnValue = method.invoke(target, args);
                        return returnValue;
                    }
                }
        );
    }
}
