//package icu.shishc;
//
//import icu.shishc.exception.CustomException;
//import icu.shishc.entity.User;
//import icu.shishc.service.UserService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//
//
///**
// * @date:2021/4/10, 14:40
// * @author:ShiShc
// */
//
//@SpringBootTest
//public class ServiceTest {
//
//    @Autowired
//    UserService userService;
//
//
//    /**
//     * 邮箱格式测试
//     */
//    @Test
//    public void mailTest() {
//        System.out.println(userService.regexMatch("123"));
//        System.out.println(userService.regexMatch("123@"));
//        System.out.println(userService.regexMatch("123@q.com"));
//        System.out.println(userService.regexMatch("123@qq.com"));
//    }
//
//    @Test
//    public void getIdentity() throws CustomException {
//        System.out.println(userService.getUserByName("admin").getUserIdentity());
//    }
//
//
//    @Test
//    public void testShiro() throws CustomException {
//        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
//        String username = token.getUsername();
//        System.out.println("登录的用户是: " + username);
//        User user = userService.getUserByName(username);
//        System.out.println("getPwd : " + String.valueOf(token.getPassword()));
//        System.out.println("getPrin : " + token.getPrincipal());
//        if(user == null) {
//            System.out.println("身份认证，不存在此用户 username = " + username);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "the user doesn't exist!");
//        } else if(!user.getPassword().equals(String.valueOf(token.getPassword()))) {
//            System.out.println("身份认证，密码错误 username = " + username + " pwd = " +user.getPassword());
//            throw new CustomException(HttpStatus.BAD_REQUEST, "pwd error!");
//        }
//        System.out.println("身份认证，登陆成功 username = " + username + " pwd = " + user.getPassword());
//        System.out.println(SecurityUtils.getSubject().toString());
////        String temp = (String) SecurityUtils.getSubject().getPrincipal();
////        System.out.println("temp = " + temp);
////        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////        String role = userService.getRole(temp);
////        Set<String> set = new HashSet<>();
////        set.add(role);
////        info.setRoles(set);
////        System.out.println("set = " + set.toString());
////        System.out.println("info = " + info.toString());
//    }
//
//
//    @Test
//    public void testString(String s) throws Exception {
//        System.out.println(s);
//    }
//
//}
