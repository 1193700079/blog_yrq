package com.yrq.blog.controller;

import com.yrq.blog.entity.User;
import com.yrq.blog.service.UserService;
import com.yrq.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user == null){
            attributes.addAttribute("message","用户名密码错误");
            return "redirect:login";  //重定向！！
        }else{
            user.setPassword(null);
            session.setAttribute("user",user);
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email,
                           @RequestParam String password,@RequestParam String password2,HttpSession session,
                        RedirectAttributes attributes){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(MD5Utils.code(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        userService.save(user);

        user.setPassword(null);
        session.setAttribute("user",user);

        return "redirect:login";

//        if(user == null){
//            attributes.addAttribute("message","用户名密码错误");
//            return "redirect:login";  //重定向！！
//        }else{
//            user.setPassword(null);
//            session.setAttribute("user",user);
//            return "index";
//        }
    }



}
