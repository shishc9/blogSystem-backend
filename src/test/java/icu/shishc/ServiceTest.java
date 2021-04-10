package icu.shishc;

import icu.shishc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date:2021/4/10, 14:40
 * @author:ShiShc
 */

@SpringBootTest
public class ServiceTest {

    @Autowired
    UserService userService;


    /**
     * 邮箱格式测试
     */
    @Test
    public void mailTest() {
        System.out.println(userService.regexMatch("123"));
        System.out.println(userService.regexMatch("123@"));
        System.out.println(userService.regexMatch("123@q.com"));
        System.out.println(userService.regexMatch("123@qq.com"));
    }

}
