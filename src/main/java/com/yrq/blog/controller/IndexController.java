package com.yrq.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.Tag;
import com.yrq.blog.entity.Type;
import com.yrq.blog.exception.NotFoundException;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.service.TagService;
import com.yrq.blog.service.TypeService;
import com.yrq.blog.vo.BlogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 14:21
 **/
@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    //ajax 请求 渲染的方法 局部刷新
    @PostMapping("/")
    public String pageable(Model model,@RequestParam(value="page",required = false,defaultValue = "1") Integer page,
                         BlogSearchVO blog){
        System.out.println("a ------------------------------a");
        blog.setPage(page);
        model.addAttribute("page", blogService.SearchListBlog(blog,"publish"));
        model.addAttribute("counts", blogService.getCountBlog());
        return "index :: blogList";
    }

    @GetMapping("/")
    public  String index(Model model, @RequestParam(value="page",required = false,defaultValue = "1") Integer page,
                            BlogSearchVO blog){
        blog.setPage(page);
        model.addAttribute("page", blogService.SearchListBlog(blog,"publish"));//个数
        model.addAttribute("counts", blogService.getCountBlog());
        //分类
        model.addAttribute("types",typeService.listTypeTop(6));
        //标签
        model.addAttribute("tags",tagService.listTagTop(6));
        //最新推荐
        model.addAttribute("blogs",blogService.findTop());

        return "index";
    }

    @PostMapping("/search")
    public String search(Model model,@RequestParam(value="page",required = false,defaultValue = "1") Integer page,
                         BlogSearchVO blog){
        blog.setPage(page);
        model.addAttribute("page", blogService.SearchListBlog(blog,"publish"));
        model.addAttribute("counts", blogService.globalSearchCountBlog(blog));
        return "search";
    }





}
