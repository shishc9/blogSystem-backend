package icu.shishc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @date: 2021-4-20, 20:44
 * @author: ShiShc
 */

@Slf4j
public class TokenUtils {

    /**
     * token到期时间为5分钟，以毫秒为单位
     */
    public static final long EXPIRE_TIME = 5*60*1000;
    /**
     * token刷新时间为30分钟，以秒为单位
     */
    public static final long REFRESH_EXPIRE_TIME = 30*60;
    /**
     * 密钥盐
     */
    public static final String TOKEN_SECRET="";


    /**
     * 生成token
     * @param account
     * @param currentTime
     * @return
     */
    public static String sign(String account, Long currentTime) {
        String token = "";
        try {
            Date expireAt = new Date(currentTime + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("account", account)
                    .withClaim("currentTime", currentTime)
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException| JWTCreationException e) {
            log.warn("【TokenUtils】sign::IllegalArg or JWTCreation Exception");
        }

        return token;
    }


    /**
     * token认证
     * @param token
     * @return
     * @throws Exception
     */
    public static Boolean verify(String token) throws Exception {
        JWTVerifier jwtVerifier =
                JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                        .withIssuer("auth0")
                        .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        log.info("【TokenUtils】verify: 认证通过，account = {}, 过期时间 = {}",
                decodedJWT.getClaim("account").asString(),
                decodedJWT.getExpiresAt());

        return true;
    }


    public static String getAccount(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("account").asString();
        } catch (JWTCreationException e) {
            return null;
        }
    }


    public static Long getCurrentTime(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("currentTime").asLong();
        } catch (JWTCreationException e) {
            return null;
        }
    }
}
