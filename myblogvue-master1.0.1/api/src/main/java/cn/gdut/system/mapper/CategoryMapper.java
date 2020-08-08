package cn.gdut.system.mapper;

import cn.gdut.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CategoryMapper extends BaseMapper<SysCategory> {

    List<SysCategory> findCategoryByArticleId(Long id);
}
