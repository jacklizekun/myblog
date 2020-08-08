package cn.gdut.system.mapper;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<SysComment> {

    List<SysComment> findAll(@Param("state") String state, @Param("queryPage")QueryPage queryPage);
}
