package cn.gdut.system.service.impl;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysLoginLog;
import cn.gdut.system.mapper.LoginLogMapper;
import cn.gdut.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, SysLoginLog> implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public void saveLog(SysLoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    @Override
    public IPage<SysLoginLog> findByPage(SysLoginLog loginLog, QueryPage queryPage) {
        IPage<SysLoginLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLoginLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(loginLog.getUserName()),SysLoginLog::getUserName, loginLog.getUserName());

        return loginLogMapper.selectPage(page, wrapper);

    }
}
