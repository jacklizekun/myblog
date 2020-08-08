package cn.gdut.common.config;

import cn.gdut.common.properties.BlogProperties;
import cn.gdut.common.properties.ShiroProperties;
import cn.gdut.common.realm.AuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private BlogProperties properties;

    /**
     * 安全管理器，必须配置
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置用户
        securityManager.setRealm(userRealm(hashedCredentialsMatcher()));
        // 设置session
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public Realm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return authRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(){

        ShiroProperties shiro = properties.getShiro();
        MySessionManager session = new MySessionManager();
        session.setGlobalSessionTimeout(shiro.getSessionTimeout() * 1000L);
        session.setDeleteInvalidSessions(true);
        session.setSessionValidationSchedulerEnabled(true);
        session.setSessionDAO(new EnterpriseCacheSessionDAO());

        return session;
    }

 /*   @Bean
    public DefaultWebSessionManager sessionManager() {
        ShiroProperties shiro = properties.getShiro();
        //使用自定义SessionManager
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setGlobalSessionTimeout(shiro.getSessionTimeout() * 1000L);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(true);
        return sessionManager;
    }*/

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroProperties shiro = properties.getShiro();
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        Map<String, String > filterMap = new LinkedHashMap<>();
        String[] urls = shiro.getAnonUrl().split(",");
        for (String url : urls){
            filterMap.put(url, "anon");
        }
        filterMap.put("/**", "user");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }


/*    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }*/

}
