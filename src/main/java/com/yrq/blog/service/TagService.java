package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Tag;
import com.yrq.blog.entity.Type;
import com.yrq.blog.mapper.TagMapper;
import com.yrq.blog.service.iservice.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 22:16
 **/
@Service
public class TagService implements ITagService {
    @Autowired
    TagMapper tagMapper;


    @Override
    public Tag checkTag(String name) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Tag tag = tagMapper.selectOne(wrapper);
        return tag;
    }

    @Override
    public int saveTag(Tag tag) {
        int i = tagMapper.insert(tag);
        return i;
    }

    @Override
    public Tag updateTagName(Long id, String name) {
        //找到这个id的东西 然后把更新后的name添加进去
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Tag tag = tagMapper.selectOne(wrapper);
        tag.setName(name);
        tag.setUpdateTime(new Date());
        tagMapper.updateById(tag);
        return tag;
    }

    @Override
    public Page<Tag> pageTag(int current,int size) {
        Page<Tag> page = new Page<Tag>(current,size) ;// 每一页显示一个数据
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
//        wrapper.like("")
        IPage<Tag> iPage = tagMapper.selectPage(page,wrapper);
        page.setRecords(iPage.getRecords());
        Page<Tag> result = tagMapper.selectPage(page, Wrappers.<Tag>lambdaQuery().ge(Tag::getId, 1).orderByAsc(Tag::getId));
        return result;
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteById(id);
    }

    public Tag getTag(Long id) {
        return  tagMapper.selectById(id);
    }
}
