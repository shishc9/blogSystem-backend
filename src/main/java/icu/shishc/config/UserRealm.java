package icu.shishc.config;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import icu.shishc.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【UserRealm】doGetAuthorizationInfo: {}", principalCollection.toString());
//        System.out.println("授权");
//        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User) subject.getPrincipal();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String role = userService.getRole(username);
        Set<String> set = new HashSet<>();
        set.add(role);
        info.setRoles(set);
//        System.out.println(currentUser.getUserIdentity());
//        if (currentUser.getUserIdentity().equals(UserIdentity.BLOGGER)) {
//            System.out.println("授权成功");
//            info.addStringPermission("BLOGGER");
//        }
        return info;
    }

    /**
     * @SneakyThrows lombok注解：为了在tc下捕获异常
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("【UserReal】doGetAuthenticationInfo:身份认证 {}", authenticationToken.toString());
        //System.out.println("认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        log.info("【UserReal】doGetAuthenticationInfo:登录的用户是: {}", username + Arrays.toString(token.getPassword()));
        User user = userService.getUserByName(username);
//        User user = userService.getUserById(Long.parseLong(token.getUsername()));
//        if (user == null) {
//            return null;
//        }
//        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
        if(user == null) {
            log.warn("【UserReal】doGetAuthenticationInfo:身份认证 username error! username = {}",username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "the user doesn't exist!");
        } else if(!user.getPassword().equals(new String(token.getPassword()))) {
            log.warn("【UserReal】doGetAuthenticationInfo:身份认证 pwd error! username = {}, pwd = {}", username, user.getPassword());
            throw new CustomException(HttpStatus.BAD_REQUEST, "pwd error!");
        }
//        Session session = SecurityUtils.getSubject().getSession();
//        session.setAttribute("user", user);
//        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        log.info("【UserReal】doGetAuthenticationInfo:身份认证 login successfully! username, pwd = {}", username + user.getPassword());
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
