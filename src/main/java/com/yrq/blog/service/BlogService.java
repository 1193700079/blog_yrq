package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.dto.BlogTagDTO;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.Type;
import com.yrq.blog.exception.NotFoundException;
import com.yrq.blog.mapper.BlogMapper;
import com.yrq.blog.service.iservice.IBlogService;
import com.yrq.blog.vo.BlogSearchVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-25 14:47
 **/
@Service
public class BlogService implements IBlogService {

    //一页显示的个数
    private final  int size = 10;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    TypeService typeService;

    @Override
    public Blog checkBlog(String title) {
        return null;
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        BlogTagDTO bt = new BlogTagDTO();
        blogMapper.insert(blog);
        return blog;
    }

    /**
     *
     * @param id   修改的博客id
     * @param blog  修改以后的博客
     * @return
     */
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogMapper.selectById(id);
        if(b==null){
            throw new NotFoundException("博客不存在");
        }
        blog.setUpdateTime(new Date());
        BeanUtils.copyProperties(blog,b); //将b的内容传给blog？？
        blogMapper.updateById(b);
        return b;
    }

    //根据条件查询 返回blog 先封装一些 条件类
    public Page<Blog>  SearchListBlog(BlogSearchVO blogSearchVO){
        System.out.println("--------start---------------");
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if( blogSearchVO.getTitle()!=null){
            wrapper.like("title",blogSearchVO.getTitle());
        }
        if( blogSearchVO.getTypeId()!=null ){
            Long id  = blogSearchVO.getTypeId();
            wrapper.eq("type_id",id);
            System.out.println("--------id---------------"+id);
        }
        if(blogSearchVO.isRecommend()){
            wrapper.eq("recommend",1);
            System.out.println("--------id---------------"+blogSearchVO.isRecommend());
        }
        Page<Blog> page = new Page<Blog>(blogSearchVO.getPage(),size) ;// 每一页显示10个数据

        Page<Blog> result = blogMapper.selectPage(page,wrapper.lambda().orderByDesc(Blog::getUpdateTime));

        for (Blog blog:result.getRecords()) {
            Long typeId = blog.getTypeId();
            if(typeId!=null){
                Type type = typeService.getType(typeId);
                if(type!=null){
                    blog.setTypeName(type.getName());
                }
            }
        }
        System.out.println("--------end---------------");
        return result;

}


    //应该是针对于普通显示的情况
    @Override
    public Page<Blog> pageBlog(int current, int size) {
        Page<Blog> page = new Page<Blog>(current,size) ;// 每一页显示一个数据
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
//        wrapper.like("")
        IPage<Blog> iPage = blogMapper.selectPage(page,wrapper);
        page.setRecords(iPage.getRecords());
        Page<Blog> result = blogMapper.selectPage(page, Wrappers.<Blog>lambdaQuery().ge(Blog::getId, 1).orderByDesc(Blog::getUpdateTime));
        return result;
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteById(id);
    }


    public Blog getBlog(Long id) {

        return blogMapper.selectById(id);
    }


}
