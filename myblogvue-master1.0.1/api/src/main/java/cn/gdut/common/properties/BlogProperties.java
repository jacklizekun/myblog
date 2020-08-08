package cn.gdut.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Desctiption 所有Bean的配置文件
 * @Date 2019/11/17/017
 **/

@Data
@SpringBootConfiguration
@PropertySource(value = "classpath:blog.properties")
@ConfigurationProperties(prefix = "blog")
public class BlogProperties {

    private ShiroProperties shiro = new ShiroProperties();
    private TengxunProperties tengxun = new TengxunProperties();
}
