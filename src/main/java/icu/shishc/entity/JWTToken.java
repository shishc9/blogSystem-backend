package icu.shishc.entity;

/**
 * @date: 2021-4-19, 23:12
 * @author: ShiShc
 */

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 配置token实体bean进行扩展，使其适用于shiro框架
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) { this.token = token; }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
