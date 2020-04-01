package com.yrq.blog.controller;

import com.yrq.blog.entity.Comment;
import com.yrq.blog.entity.User;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @program: blog_yrq
 * @description:   留言板
 * @author: yrq
 * @create: 2020-03-27 15:55
 **/
@Controller
@RequestMapping
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

//    @Value("${comment.avatar}")
//    private String avatar;

    @GetMapping("/comment")
    public String comment(){
        return "comment";
    }

//    @GetMapping("/comments/{blogId}")
//    public String comments(@PathVariable Long blogId, Model model) {
//        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
//        return "blog :: commentList";
//    }
//
//
//    @PostMapping("/comments")
//    public String post(Comment comment, HttpSession session) {
//        Long blogId = comment.getBlog().getId();
//        comment.setBlog(blogService.getBlog(blogId));
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            comment.setAvatar(user.getAvatar());
//            comment.setAdminComment(true);
//        } else {
//            comment.setAvatar(avatar);
//        }
//        commentService.saveComment(comment);
//        return "redirect:/comments/" + blogId;
//    }
}
