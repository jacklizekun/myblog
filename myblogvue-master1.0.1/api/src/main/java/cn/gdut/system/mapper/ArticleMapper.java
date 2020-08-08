package cn.gdut.system.mapper;

import cn.gdut.system.entity.SysArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ArticleMapper extends BaseMapper<SysArticle> {

    List<SysArticle> findAll();
}
