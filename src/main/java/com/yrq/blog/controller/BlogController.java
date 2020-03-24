package com.yrq.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 18:02
 **/
@Controller
public class BlogController {
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}
