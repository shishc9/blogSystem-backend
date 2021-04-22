package icu.shishc.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.JWTToken;
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

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
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

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

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
}
