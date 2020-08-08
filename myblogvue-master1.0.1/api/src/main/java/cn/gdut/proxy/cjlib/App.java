package cn.gdut.proxy.cjlib;

/**
 * @Desctiption TODO
 * @Date 2019/11/27/027
 **/
public class App {
    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDao();
        // 代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        // 代理对象执行方法
        proxy.save();
    }
}
