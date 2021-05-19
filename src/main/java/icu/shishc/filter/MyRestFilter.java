package icu.shishc.filter;

import icu.shishc.util.FilterUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @date: 2021-5-19, 14:48
 * @author: ShiShc
 */

public class MyRestFilter extends HttpMethodPermissionFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("MyRestFilter");
        FilterUtils.onAccessDenied(request, response, HttpStatus.UNAUTHORIZED);
        return false;
    }
}
