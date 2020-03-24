package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yrq.blog.entity.User;
import com.yrq.blog.mapper.UserMapper;
import com.yrq.blog.service.iservice.IUserService;
import com.yrq.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 00:58
 **/
@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        wrapper.eq("password", MD5Utils.code(password));
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    //用户注册
    @Override
    public void save(User user) {
        userMapper.insert(user);
    }
}
