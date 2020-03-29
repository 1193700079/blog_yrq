package com.yrq.blog.mapper;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 11:50
 **/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id =#{userId} limit 1")
    User findUserByBlog(Blog blog);

}