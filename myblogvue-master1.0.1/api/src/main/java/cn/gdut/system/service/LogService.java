package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Queue;

public interface LogService {

    void saveLog(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException;

    IPage<SysLog> findByPage(SysLog sysLog, QueryPage queryPage);

    void delete(Long id);

}
