package cn.gdut.system.service;

import cn.gdut.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<SysUser> {

    SysUser findByName(String username);
}
