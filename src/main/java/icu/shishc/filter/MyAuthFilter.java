package icu.shishc.filter;

import icu.shishc.util.FilterUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * @date: 2021-5-15, 21:44
 * @author: ShiShc
 * 覆盖自带过滤器，让未登录的行为统一返回401.
 */

public class MyAuthFilter extends FormAuthenticationFilter {


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
        System.out.println("MyAuthFilter");
        FilterUtils.onAccessDenied(request, response, HttpStatus.FORBIDDEN);
        return false;
    }

}
