package com.yrq.blog.controller;

import com.yrq.blog.entity.User;
import com.yrq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-28 15:08
 **/
@Controller
public class RegisterController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email,
                           @RequestParam String password, @RequestParam String nickname,HttpSession session,
                           RedirectAttributes attributes, Model model){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);//后面会更改
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setNickname(nickname);
        user.setAvatar("../static/image/1.png");
        if(userService.save(user)){
            //
            user.setPassword(null);
            session.setAttribute("user",user);
            return "login";
        }else{
            attributes.addAttribute("msg","用户名已经存在");
            return "redirect:login";  //重定向！！
        }
    }
}
