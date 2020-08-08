package cn.gdut.proxy.jingtai;

/**
 * @Desctiption 目标对象的实现
 * @Date 2019/11/27/027
 **/
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
