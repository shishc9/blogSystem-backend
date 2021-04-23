package icu.shishc.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.JWTToken;
import icu.shishc.util.RedisUtils;
import icu.shishc.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @date: 2021-4-22, 20:31
 * @author: ShiShc
 * @description: 自定义jwt过滤器，对token进行处理
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {


    /**
     * 创建shiro token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        log.info("【JWTFilter】createToken");
        String jwtToken = ((HttpServletRequest) request).getHeader("token");
        if(jwtToken != null) {
            log.info("【JWTFilter】createToken::create token successfully");
            return new JWTToken(jwtToken);
        }
        log.info("【JWTFilter】createToken::create token failed");
        return null;
    }


    /**
     * 判断是否允许通过
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("【JWTFilter】isAccessAllowed");
        try {
            log.info("【JWTFilter】isAccessAllowed::isAllowed");
            return executeLogin(request, response);
        } catch (Exception e) {
            log.warn("【JWTFilter】isAccessAllowed::isn't allowed");
            responseError(response, "Shiro Fail");
            return false;
        }
    }


    /**
     * isAccessAllowed失败的时候调用
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("【JWTFilter】onAccessDenied");
        this.sendChallenge(request, response);
        responseError(response, "token verify failed");
        return false;
    }


    /**
     * 判断是否进行登录请求
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        log.info("【JWTFilter】isLoginAttempt");
        String token = ((HttpServletResponse) response).getHeader("token");
        if(token != null) {
            log.info("【JWTFilter】isLoginAttempt::Attempt");
            return true;
        }
        log.warn("【JWTFilter】isLoginAttempt::not attempt");
        return false;
    }


    /**
     * Shiro验证成功使用
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.info("【JWTFilter】onLoginSuccess");
        String jwtToken = ((String) token.getPrincipal());
        if(jwtToken != null) {
            try {
                if(TokenUtils.verify(jwtToken)) {
                    String account = TokenUtils.getAccount(jwtToken);
                    Long currentTime = TokenUtils.getCurrentTime(jwtToken);
                    // Redis是否存在所对应的RefreshToken
                    if(RedisUtils.hasKey(account)) {
                        Long currentTimeMillisRedis = (Long) RedisUtils.get(account);
                        if(currentTimeMillisRedis.equals(currentTime)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                Throwable throwable = e.getCause();
                log.warn("【JWTFilter】onLoginSuccess:token access : {}", e.getClass());
                if(e instanceof TokenExpiredException) {
                    log.warn("【JWTFilter】onLoginSuccess: tokenExpiredException");
                    return refreshToken(response, request);
                }
            }
        }
        return true;
    }

    /**
     * TODO
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }

    private void responseError(ServletResponse response, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        try {
            String rj = new ObjectMapper().writeValueAsString(MyDTO.wrongDTO(HttpStatus.UNAUTHORIZED, msg));
            httpServletResponse.getWriter().append(rj);
        } catch (IOException e) {
            log.warn("【JWTFilter】responseError::UNAUTHORIZED");
            e.printStackTrace();
        }
    }


    /**
     * TODO
     * @param response
     * @param request
     * @return
     */
    private boolean refreshToken(ServletResponse response, ServletRequest request) {
        String token = ((HttpServletRequest) request).getHeader("token");
        return true;
    }
}
