package icu.shishc.util;

import com.alibaba.fastjson.JSONObject;
import icu.shishc.dto.MyDTO;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @date: 2021-5-19, 14:00
 * @author: ShiShc
 */

public class FilterUtils {
    public static void onAccessDenied(ServletRequest request, ServletResponse response, HttpStatus httpStatus) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        MyDTO myDTO = new MyDTO(httpStatus, httpStatus.getReasonPhrase(), null);
        httpServletResponse.getWriter().write(JSONObject.toJSON(myDTO).toString());
    }
}
