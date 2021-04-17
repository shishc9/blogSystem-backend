package icu.shishc.util;

import icu.shishc.service.BlogService;
import icu.shishc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName:icu.shishc.util
 * @Date:2021/3/29, 17:02
 * @Auther:ShiShc
 */

/**
 * 一个调试过程的辅助类
 */
@Component
public class MyUtils {

    private static final boolean printFlag = true;

    private static BlogService blogService;
    private static UserService userService;


    @Autowired
    public MyUtils(BlogService blogService,
                   UserService userService) {
        MyUtils.blogService = blogService;
        MyUtils.userService = userService;
    }


    public static void print(Object msg) {
        if(printFlag) {
            System.out.println("ShiShc =>{" + msg + "}");
        }
    }


    public static BlogService getBlogService() {
        return blogService;
    }


    public static UserService getUserService() {
        return userService;
    }


}
