package cn.gdut.system.service.impl;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysLink;
import cn.gdut.system.mapper.LinkMapper;
import cn.gdut.system.service.LinkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desctiption TODO
 * @Date 2019/11/20/020
 **/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, SysLink> implements LinkService {

    @Autowired
    LinkMapper linkMapper;

    @Override
    public IPage<SysLink> findByPage(SysLink link, QueryPage queryPage) {
        IPage<SysLink> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysLink::getId);
        queryWrapper.like(!StringUtils.isBlank(link.getName()), SysLink::getName, link.getName());

        return linkMapper.selectPage(page, queryWrapper);
    }
}
