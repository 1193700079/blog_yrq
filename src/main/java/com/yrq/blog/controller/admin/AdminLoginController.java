package com.yrq.blog.controller.admin;

import com.yrq.blog.entity.User;
import com.yrq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 01:21
 **/
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login_admin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user==null || user.getType() != 1314 ){
            attributes.addAttribute("message","用户名密码错误");
//            return "redirect:login_admin";  //重定向！！
            return "redirect:login_admin";
        }else{
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/blogs";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "admin/login_admin";
    }


}
