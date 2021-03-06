package com.yrq.blog.service.iservice;

import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.User;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 00:58
 **/
public interface IUserService {
    User checkUser(String username, String password);
    Boolean save(User user);

    User findUserByBlog(Blog blog);
}
