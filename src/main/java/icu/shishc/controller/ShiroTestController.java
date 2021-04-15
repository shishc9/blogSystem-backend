package icu.shishc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogbackend")
public class ShiroTestController {
//    @GetMapping("/testLogin")
//    public String testLogin(@RequestParam String userId, @RequestParam String password) {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
//        try {
//            subject.login(token);
//        } catch (UnknownAccountException e) {
//            return "UnknownAccountException";
//        } catch (IncorrectCredentialsException e) {
//            return "IncorrectCredentialsException";
//        }
//        return "login";
//    }
//
//    @GetMapping("/testIndex")
//    public String Index() {
//        return "index";
//    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "UnknownAccountException";
        } catch (IncorrectCredentialsException e) {
            return "IncorrectCredentialsException";
        }
        System.out.println("login successfully");
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        System.out.println("index");
        return "index";
    }
}
