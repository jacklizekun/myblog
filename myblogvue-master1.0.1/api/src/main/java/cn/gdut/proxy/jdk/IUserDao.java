package cn.gdut.proxy.jdk;

/**
 * 静态代理使用时，需要定义接口或者父类，被代理对象和代理对象一起实现相同的接口
 * 通过调用相同的方法来调用目标对象的方法
 * 目标对象接口，完成保存操作
 */
public interface IUserDao {
    void save();
    void speek();
}
