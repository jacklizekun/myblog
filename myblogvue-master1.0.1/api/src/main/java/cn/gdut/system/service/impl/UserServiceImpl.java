package cn.gdut.system.service.impl;

import cn.gdut.system.mapper.UserMapper;
import cn.gdut.system.entity.SysUser;
import cn.gdut.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desctiption TODO
 * @Date 2019/11/17/017
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
