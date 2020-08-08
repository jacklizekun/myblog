package cn.gdut.common.properties;

import lombok.Data;

/**
 * shiro的配置文件
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    // 请求的路径
    private String anonUrl;
}
