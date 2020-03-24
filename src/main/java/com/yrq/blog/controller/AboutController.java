package com.yrq.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 18:18
 **/
@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
