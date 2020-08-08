package cn.gdut.proxy.jingtai;

/**
 * @Desctiption 代理对象，静态代理
 * @Date 2019/11/27/027
 **/
public class UserDaoProxy implements IUserDao {
    // 接收保存目标对象对象
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }


    @Override
    public void save() {
        System.out.println("开始事物");
        target.save();
        System.out.println("执行完成");
    }
}
