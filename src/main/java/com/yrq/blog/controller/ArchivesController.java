package com.yrq.blog.controller;

import com.yrq.blog.entity.Blog;
import com.yrq.blog.mapper.BlogMapper;
import com.yrq.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-26 18:46
 **/

@Controller
public class ArchivesController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/archives")
    public String  Archives(Model model){
        model.addAttribute("counts", blogService.getCountBlog());
        model.addAttribute("archiveMap",blogService.archiveBlog());
        return "archives";
    }


}
