package com.yrq.blog.controller;

import com.yrq.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 18:02
 **/
@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }


}
