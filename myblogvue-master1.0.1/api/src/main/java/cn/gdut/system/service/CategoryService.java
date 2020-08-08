package cn.gdut.system.service;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Locale;

public interface CategoryService extends IService<SysCategory> {
    /**
     * 根据Id查找
     * @param id
     * @return
     */
    SysCategory findById(Long id);

    IPage<SysCategory> fingByPage(SysCategory category, QueryPage queryPage);

    List<SysCategory> findAll();

    List<SysCategory> findByArticleId(Long id);

    void delete(Long id);
}
