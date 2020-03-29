package com.yrq.blog.controller;

import com.yrq.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-26 18:46
 **/
@Controller
public class TagsController {

    @Autowired
    TagService tagService;

    @GetMapping("tags/{id}")
    public String typesId(@PathVariable Long id, Model model,
                          @RequestParam(value="page",required = false,defaultValue = "1") Integer page){
        model.addAttribute("tagCounts",tagService.getTotalTag());
        model.addAttribute("page",tagService.getTagBlog(id,page));
        model.addAttribute("tags",tagService.listTypeTop(1000));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
