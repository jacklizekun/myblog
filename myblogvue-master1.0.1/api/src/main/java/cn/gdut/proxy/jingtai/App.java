package cn.gdut.proxy.jingtai;

/**
 * @Desctiption T
 * 1 可以做到不修改目标对象功能时，对目标功能扩展
 * 2 缺点： 因为代理对象需要对象对象一样的接口，所有会有很多代理类。
 * @Date 2019/11/27/027
 **/
public class App {
    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDao();

        // 代理对象，把目标对象传给代理对象，建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);
        // 代理方法执行
        proxy.save();
    }
}
