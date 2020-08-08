package cn.gdut.common.controller;

import cn.gdut.system.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * 封装展示数据
     * @param page 得到的页数信息
     * @return map
     */
    public Map<String, Object> getData(IPage<?> page){
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;
    }

    protected Session getSession(){
        return getSubject().getSession();
    }

    protected static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public Map<String, Object> getToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("token", getSession().getId());
        return map;
    }

    protected SysUser getCurrentUser() {
        return (SysUser) getSubject().getPrincipal();
    }
}
