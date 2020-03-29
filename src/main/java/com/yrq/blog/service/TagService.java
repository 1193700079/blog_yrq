package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.Tag;
import com.yrq.blog.entity.Type;
import com.yrq.blog.mapper.BlogMapper;
import com.yrq.blog.mapper.TagMapper;
import com.yrq.blog.service.iservice.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserService userService;


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

    public List<Tag> listTag() {
        return tagMapper.selectList(null);
    }

    public List<Tag> listTag(String ids) {
        List<Tag> result = new ArrayList<>();
        if(ids!=null && ids!=""){
            String[] s = ids.split(",");
            for (int i = 0; i < s.length; i++) {
                int id = Integer.parseInt(s[i]);
                Tag tag = tagMapper.selectById(id);
                result.add(tag);
            }
        }
        return result;
    }

    public void updateTag(Tag tag) {
        tagMapper.updateById(tag);
    }

    public List<Tag> listTagTop(int num) {
        Page<Tag> page = new Page<Tag>(1,num) ;// 每一页显示一个数据
        Page<Tag> result = tagMapper.selectPage(page, Wrappers.<Tag>lambdaQuery().ge(Tag::getId, 1).orderByDesc(Tag::getBlogSize));
        return result.getRecords();
    }

    public  List<Tag> idsTotags(String tagIds){
        String[] ids = tagIds.split(",");
        List<Tag> tags = new ArrayList<>();
        for (String s: ids) {
            Tag tag = tagMapper.selectById(Long.parseLong(s));
            tags.add(tag);
        }
        return tags;
    }

    //数据库每个都+1 or -1
    public List<Tag> updateTags(String tagIds,String m) {
        String[] s = tagIds.split(",");
        List<Tag> res = new ArrayList<>();
        if(m.equals("+")){
            for (String str: s ) {
                Tag tag = tagMapper.selectById(Long.parseLong(str));
                int i = tag.getBlogSize();
                tag.setBlogSize(i+1);
                tagMapper.updateById(tag);
                res.add(tag);
            }
        }else{
            for (String str: s ) {
                Tag tag = tagMapper.selectById(Long.parseLong(str));
                int i = tag.getBlogSize();
                tag.setBlogSize(i-1);
                tagMapper.updateById(tag);
                res.add(tag);
            }
        }

        return res;
    }

    public Integer getTotalTag() {
        return tagMapper.findTotalTag();
    }

    public List<Tag> listTypeTop(int num) {
        Page<Tag> page = new Page<Tag>(1,num) ;// 每一页显示一个数据
        Page<Tag> result = tagMapper.selectPage(page, Wrappers.<Tag>lambdaQuery().ge(Tag::getId, 1).orderByDesc(Tag::getBlogSize));
        return result.getRecords();
    }

    public Page<Blog> getTagBlog(Long id, Integer page) {
        //        tag的id
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.like("tag_ids",id);
        wrapper.eq("published",1);
        Page<Blog> pages = new Page<Blog>(page,2) ;// 每一页显示10个数据
        Page<Blog> result = blogMapper.selectPage(pages,wrapper.lambda().orderByDesc(Blog::getUpdateTime));
        for (Blog blog:result.getRecords()) {
//            String[] s = blog.getTagIds().split(",");
//            for (String i:s) {
//                if(i.equals(id.toString())){
//
//                    break;
//                }
//                //如果不含有的话 设为空
////                blog = null;
//            }
            blog.setUser(userService.findUserByBlog(blog));

        }
        return result;
    }
}
