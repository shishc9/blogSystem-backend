package icu.shishc.config;

import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import icu.shishc.service.UserService;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println(currentUser.getUserIdentity());
        if (currentUser.getUserIdentity().equals(UserIdentity.BLOGGER)) {
            System.out.println("授权成功");
            info.addStringPermission("BLOGGER");
        }
        return info;
    }

    /**
     * @SneakyThrows lombok注解：为了在tc下捕获异常
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        System.out.println("认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserById(Long.parseLong(token.getUsername()));
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
