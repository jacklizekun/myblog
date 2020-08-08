package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysLink;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


public interface LinkService extends IService<SysLink> {

    IPage<SysLink> findByPage(SysLink link, QueryPage queryPage);
}
