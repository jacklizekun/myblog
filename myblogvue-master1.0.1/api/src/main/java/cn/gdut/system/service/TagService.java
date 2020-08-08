package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagService extends IService<SysTag> {

    IPage<SysTag> findByPage(SysTag tag, QueryPage queryPage);

    List<SysTag> findAll();

    List<SysTag> findByArticleId(Long id);

    void delete(Long id);

    void add(SysTag tag);
}
