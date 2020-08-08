package cn.gdut.common.realm;

import cn.gdut.common.exception.GlobalException;
import cn.gdut.system.entity.SysUser;
import cn.gdut.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Desctiption TODO
 * @Date 2019/11/17/017
 **/
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 身份校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 权限校验
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  username = (String) authenticationToken.getPrincipal();
        SysUser user = userService.findByName(username);
        if (user == null){
            throw new GlobalException(new UnknownAccountException().getMessage());
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
        return simpleAuthenticationInfo;
    }

}
