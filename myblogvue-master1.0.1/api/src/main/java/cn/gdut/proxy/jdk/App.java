package cn.gdut.proxy.jdk;

/**
 * @Desctiption 代理对象不需要实现接口。但目标对象一定要实现接口
 * @Date 2019/11/27/027
 **/
public class App {
    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDao();

        System.out.println(target.getClass());
        // 给目标对象创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();

        // 内存中动态生成代理的对象
        System.out.println(proxy.getClass());

        // 代理对象执行 目标对象的方法
        proxy.save();
    }
}
