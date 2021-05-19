package icu.shishc.filter;

import icu.shishc.util.FilterUtils;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @date: 2021-5-19, 14:48
 * @author: ShiShc
 * @DESC: 对rest资源未登录访问进行拦截
 */
public class MyRestFilter extends HttpMethodPermissionFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("这里是未登录且请求了授权资源的认证处~：MyRestFilter");
        FilterUtils.onAccessDenied(request, response, HttpStatus.UNAUTHORIZED);
        return false;
    }
}
