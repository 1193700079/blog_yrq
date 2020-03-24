package com.yrq.blog.service.iservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Tag;
import com.yrq.blog.entity.Type;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 22:14
 **/
public interface ITagService  {
    //检查是否存在
    Tag checkTag(String name);

    //添加
    int saveTag(Tag tag);

    //更新
    Tag updateTagName(Long id, String name);

    //分页
    Page<Tag> pageTag(int current, int size);

    //删除
    void deleteTag(Long id);
}
