package icu.shishc.config;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Perms;
import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * @author Closer
 * @DESC: UserRealm
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 权限认证
     * @param principalCollection 权限集合
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【UserRealm】doGetAuthorizationInfo:身份授权 {}", principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // 将BLOGGER / ADMIN 保存为字符串
        List<Perms> list = userService.getUserPerms(user.getUserIdentity());
        // 将BlOGGER / ADMIN 进行授权
        for(Perms perm:list) {
            info.addStringPermission(perm.getEntity().toUpperCase() + ":" + perm.getPerm().toUpperCase());
        }
        System.out.println(info.getStringPermissions());
        log.info("【UserRealm】doGetAuthorization:身份授权, user[{}]授权完成, 身份是{}", user.getUsername(), user.getUserIdentity().toString());
        return info;
    }

    /**
     * 身份认证
     * @param authenticationToken token
     * @return AuthenticationInfo
     * @throws AuthenticationException AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("【UserReal】doGetAuthenticationInfo:身份认证 {}", authenticationToken.toString());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        log.info("【UserReal】doGetAuthenticationInfo:要登录的用户是: {}", username);
        User user = userService.getUserByName(username);
        if(user == null) {
            log.warn("【UserReal】doGetAuthenticationInfo:身份认证 username error! username = {}",username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        } else if(!user.getPassword().equals(new String(token.getPassword()))) {
            // 这里密码验证可以不用写  下面SimpleAuthenticationInfo第二个参数也会进行密码校验
            log.warn("【UserReal】doGetAuthenticationInfo:身份认证 pwd error! username = {}, pwd = {}", username, user.getPassword());
            throw new CustomException(HttpStatus.BAD_REQUEST, "PWD_ERROR");
        }
        log.info("【UserReal】doGetAuthenticationInfo:身份认证 login successfully! username, pwd = {}", username + user.getPassword());
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
