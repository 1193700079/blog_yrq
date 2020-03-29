package com.yrq.blog.service.iservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-25 14:19
 **/
public interface IBlogService  {
    //查询  根据标题查询 使用正则 或者like之类的  要将搜索到的返回到前端显示

    //检查是否存在  标题可以重复

    //添加

    //修改

    //删除

    //分页

    //检查是否存在
    Blog checkBlog(String title);

    //添加
    Blog saveBlog(Blog blog);

    //更新
    Blog updateBlog(Long id, Blog blog);

    //分页
    Page<Blog> pageBlog(int current, int size);

    //删除
    void deleteBlog(Long id);

    //得到前8个最新的博客
    List<Blog> findTop();


    Map<String,List<Blog>> archiveBlog();
}
