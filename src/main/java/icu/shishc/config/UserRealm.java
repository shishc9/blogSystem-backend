package icu.shishc.config;

import cn.hutool.core.lang.Validator;
import icu.shishc.exception.CustomException;
import icu.shishc.entity.Perms;
import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import icu.shishc.util.MD5Utils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
        User user = null;
        String account = token.getUsername();
        // 判断是不是邮箱登录
        if(Validator.isEmail(account)) {
            user = userService.getUserByEmail(account);
        } else {
            user = userService.getUserByName(account);
        }
        if(user == null) {
            log.warn("【UserReal】doGetAuthenticationInfo:身份认证 username error! username = {}",account);
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        String pwd = MD5Utils.toMd5(String.valueOf(token.getPassword()), "shishc", 10);
        if(!user.getPassword().equals(pwd)) {
            // 这里密码验证可以不用写  下面SimpleAuthenticationInfo第二个参数也会进行密码校验
            log.warn("【UserReal】doGetAuthenticationInfo:身份认证 pwd error! username = {}, pwd = {}", account, pwd);
            throw new CustomException(HttpStatus.BAD_REQUEST, "USERNAME_OR_PWD_ERROR");
        }
        log.info("【UserReal】doGetAuthenticationInfo:身份认证 login successfully! username, pwd = {}", account + pwd);
        //创建认证盐值
        ByteSource salt = ByteSource.Util.bytes("shishc");
        return new SimpleAuthenticationInfo(user, pwd, salt, getName());
    }
}
