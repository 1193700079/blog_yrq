package com.yrq.blog.mapper;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 11:50
 **/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}