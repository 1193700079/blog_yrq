package com.yrq.blog.service.iservice;

import com.yrq.blog.entity.Comment;

import java.util.List;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-27 16:06
 **/
public interface ICommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
