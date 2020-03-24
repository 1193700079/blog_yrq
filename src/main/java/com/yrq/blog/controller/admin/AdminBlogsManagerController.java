package com.yrq.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 08:34
 **/
@Controller
@RequestMapping("/admin")
public class AdminBlogsManagerController {

    @GetMapping("/blogs")
    public String blog(HttpSession session){
        return "/admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String blog_input(HttpSession session){
        return "/admin/blogs_input";
    }


}
