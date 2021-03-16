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
@RequestMapping("/shiro")
public class ShiroTestController {
    @GetMapping("/testLogin")
    public String testLogin(@RequestParam String user, @RequestParam String pswd) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user, pswd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "UnknownAccountException";
        } catch (IncorrectCredentialsException e) {
            return "IncorrectCredentialsException";
        }
        return "login";
    }

    @GetMapping("/testIndex")
    public String Index() {
        return "index";
    }
}
