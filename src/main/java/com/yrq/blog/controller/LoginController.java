package com.yrq.blog.controller;

import com.yrq.blog.entity.User;
import com.yrq.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 00:47
 **/
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value ={"/login","/toLogin"})
    public String loginPage(){
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, HttpServletRequest request,
                        RedirectAttributes attributes, Model model){
//        shrio 权限管理
        Subject subject = SecurityUtils.getSubject();
        User user = userService.selectUser(username);
        System.out.println(user.getPassword());
        String pwd = new SimpleHash("MD5", password, user.getSalt(), 2).toString();
        System.out.println(user.getSalt()+"---------------------------------");
        System.out.println(pwd);
        UsernamePasswordToken token = new UsernamePasswordToken(username,pwd);
        //设置记住我
//        token.setRememberMe(true);
        try {
            subject.login(token);
            System.out.println("登录成功！！！！！！！！！！！！！！！！！！！！！！！*********");
            session.setAttribute("user",user);
            return "redirect:/";
        }catch(UnknownAccountException uae){
                //用户名不存在
            model.addAttribute("msg","用户名不存在");
            System.out.println("用户名不存在！！！！！！！！！！！！！！！！！！！！！！！*********");
            return "redirect:login";
        }catch (IncorrectCredentialsException ice){
                //密码不存在
            model.addAttribute("msg","密码错误");
            System.out.println("密码错误！！！！！！！！！！！！！！！！！！！！！！！*********");
            return "redirect:login";
        }catch (Exception e){
            logger.info("登录失败，失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    @GetMapping("/noauth")
    @ResponseBody
    public String noauth(){
        return "您没有权限";
    }

}
