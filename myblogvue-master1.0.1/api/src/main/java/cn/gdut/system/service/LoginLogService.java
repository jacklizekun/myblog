package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
public interface LoginLogService extends IService<SysLoginLog> {

    void saveLog(SysLoginLog loginLog);

    IPage<SysLoginLog> findByPage(SysLoginLog loginLog, QueryPage queryPage);
}
