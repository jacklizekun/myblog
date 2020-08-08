package cn.gdut.system.mapper;

import cn.gdut.system.entity.SysTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface TagMapper extends BaseMapper<SysTag> {
    List<SysTag> findByArticleId(Long articleId);
}
