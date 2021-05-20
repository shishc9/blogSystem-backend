package icu.shishc.UserService;

import icu.shishc.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date: 2021-5-20, 21:02
 * @author: ShiShc
 */

@SpringBootTest
public class UserTest {

    @Test
    public void test01() {
        System.out.println(MD5Utils.toMd5("admin", "shishc", 10));
    }
}
