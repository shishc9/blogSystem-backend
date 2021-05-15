package icu.shishc.filter;

import com.alibaba.fastjson.JSONObject;
import icu.shishc.dto.MyDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @date: 2021-5-15, 21:44
 * @author: ShiShc
 * 覆盖自带过滤器，让未登录的行为统一返回401.
 */
public class LoginFilter extends FormAuthenticationFilter {
//    @Override
//    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
//        Subject subject = getSubject(servletRequest, servletResponse);
//        return null != subject.getPrincipals();
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws IOException {
//        if(isAjax(((HttpServletRequest) servletRequest))) {
//            WebUtils.toHttp(servletResponse).sendError(401);
//        } else {
//            String unauthorizedUrl = getUnauthorizedUrl();
//            if(StringUtils.hasText(unauthorizedUrl)) {
//                WebUtils.issueRedirect(servletRequest, servletResponse, unauthorizedUrl);
//            } else {
//                WebUtils.toHttp(servletResponse).sendError(401);
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isAjax(HttpServletRequest request) {
//        String header = request.getHeader("x-requestd-with");
//        return null != header && "XMLHttpRequest".endsWith(header);
//    }
//
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        Subject subject = SecurityUtils.getSubject();
//        if (isAjax(httpServletRequest)) {
//            if (org.apache.commons.lang3.StringUtils.contains(httpServletRequest.getRequestURI(), "/portal/admin/login")) {
//                return true;
//            }
//            if (subject.isAuthenticated()) {
//                return true;
//            } else {
//                MyDTO myDTO = new MyDTO();
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.setContentType("application/json");
//                myDTO.setCode(401);
//                myDTO.setMsg("noAuthorization");
//                httpServletResponse.getWriter().write(myDTO.toString());
//                return false;
//            }
//        } else {
//            //不是ajax进行重定向处理
////          httpServletResponse.sendRedirect("/login/local");
//            return true;
//        }
//    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (!isLoginSubmission(request, response)) {
//                if (log.isTraceEnabled()) {
//                    log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
//                            "Authentication url [" + getLoginUrl() + "]");
//                }

                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message", "没有权限访问");
                jsonObject.put("code", "401");
                jsonObject.put("data", "");
                Writer writer = httpServletResponse.getWriter();
                writer.write(jsonObject.toJSONString());
                writer.flush();
                writer.close();
            }else {
                return executeLogin(request, response);
            }
        }
        return false;
    }

}
