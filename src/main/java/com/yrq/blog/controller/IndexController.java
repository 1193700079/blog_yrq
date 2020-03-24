package com.yrq.blog.controller;

import com.yrq.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 14:21
 **/
@Controller
public class IndexController {

//    @GetMapping("/{id}/{name}")
//    public String index(@PathVariable int id,@PathVariable String name){
////        int i = 9/0;
//        String blog = null;
//        if(blog == null){
//            throw  new NotFoundException("博客不存在");
//        }
//        System.out.println("-------doing-------"+id+"......"+name);
//        return "index";
//    }
        @GetMapping("/")
        public String index(){
            return "index";
        }


}
