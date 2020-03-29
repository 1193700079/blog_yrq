package com.yrq.blog.controller;

import com.yrq.blog.entity.Type;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-26 18:46
 **/
@Controller
public class TypesController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;


    @GetMapping("types/{id}")
    public String typesId(@PathVariable Long id, Model model,
                @RequestParam(value="page",required = false,defaultValue = "1") Integer page){
            model.addAttribute("typeCounts",typeService.getTotalType());
            model.addAttribute("page",blogService.getTypeBlog(id,page));
            model.addAttribute("types",typeService.listTypeTop(1000));
            model.addAttribute("activeTypeId",id);
            return "types";
    }



}
