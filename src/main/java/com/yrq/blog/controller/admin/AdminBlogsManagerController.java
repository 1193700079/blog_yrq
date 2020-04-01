package com.yrq.blog.controller.admin;

import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.Type;
import com.yrq.blog.entity.User;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.service.TagService;
import com.yrq.blog.service.TypeService;
import com.yrq.blog.vo.BlogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final String INPUT = "admin/blogs_input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    //列表显示
    @GetMapping("/blogs")
    public String blog(Model model,@RequestParam(value="page",required = false,defaultValue = "1") Integer page,
                       BlogSearchVO blog){
        blog.setPage(page);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page", blogService.SearchListBlog(blog,""));
        return LIST;
    }

    //编辑 or 修改
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        blog.setTypeName(typeService.getType(blog.getTypeId()).getName());
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }


    //ajax 请求 渲染的方法 局部刷新
    @PostMapping("/blogs/search")
    public String search(Model model,@RequestParam(value="page",required = false,defaultValue = "1") Integer page,
                         BlogSearchVO blog){
        blog.setPage(page);
        System.out.println(blog);
        model.addAttribute("page", blogService.SearchListBlog(blog,""));
        return "admin/blogs :: blogList";
    }

    //得到分类下拉框坏和标签下拉框中的数据 ！
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }


    //新增和修改博客都可以 ！
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(1L);
        blog.setType(typeService.getType(blog.getTypeName()));

        Type type = blog.getType();
        blog.setTypeName(type.getName());
        blog.setTypeId(type.getId()); //得到ID
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }





}
