package com.fuckyou.controller.wed;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 陈源熹 on 2018-01-05.
 */
@Controller
public class loginAction {

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){

        //1.创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();

        //2.判断当前用户是否登录
        if (currentUser.isAuthenticated() == false) {
            //将用户名和密码封装到UsernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //记住我
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e)

            {
                System.out.print("登录失败....");
                return "abc";
            }
        }
        return "success";
    }
}
