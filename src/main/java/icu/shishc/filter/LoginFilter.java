package icu.shishc.filter;

import com.alibaba.fastjson.JSONObject;
import icu.shishc.dto.MyDTO;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @date: 2021-5-15, 21:44
 * @author: ShiShc
 * 覆盖自带过滤器，让未登录的行为统一返回401.
 */

public class LoginFilter extends FormAuthenticationFilter {


    /**
     * 判断是否登录。 在登录的情况下会执行这个方法，此方法返回true直接访问控制器. 如果isAccessAllowed返回false 则执行onAccessDenied
     * @param request request
     * @param response response
     * @param mappedValue mappedValue
     * @return boolean
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if ("OPTIONS".equals(((HttpServletRequest) request).getMethod().toUpperCase())) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }


    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * 是否拒绝登录。 在没有登录的情况下访问此方法。
     * @param request request
     * @param response response
     * @return boolean
     * @throws IOException IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        MyDTO myDTO = new MyDTO(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", null);
        httpServletResponse.getWriter().write(JSONObject.toJSON(myDTO).toString());
        return false;
    }
}
