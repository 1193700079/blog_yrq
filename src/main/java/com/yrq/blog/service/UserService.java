package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.User;
import com.yrq.blog.mapper.UserMapper;
import com.yrq.blog.service.iservice.IUserService;
import com.yrq.blog.utils.MD5Utils;
import com.yrq.blog.utils.ShiroVerityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    //用户登录
    public User selectUser(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        return userMapper.selectOne(wrapper);
    }

    //用户注册
    @Override
    public Boolean save(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User u = userMapper.selectOne(queryWrapper);
        if(u!=null){
            //做错误处理
            return false;
        }else{
            Map<String,String> map = ShiroVerityUtil.encryptPassword(user.getPassword());
            user.setPassword(map.get("password"));
            user.setSalt(map.get("salt"));
            userMapper.insert(user);
            return true;
        }
    }



    @Override
    public User findUserByBlog(Blog blog) {
        return userMapper.findUserByBlog(blog);
    }
}
