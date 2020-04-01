package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.dto.BlogTagDTO;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.Tag;
import com.yrq.blog.entity.Type;
import com.yrq.blog.exception.NotFoundException;
import com.yrq.blog.mapper.BlogMapper;
import com.yrq.blog.service.iservice.IBlogService;
import com.yrq.blog.utils.MarkdownUtils;
import com.yrq.blog.vo.BlogSearchVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-25 14:47
 **/
@Service
public class BlogService implements IBlogService {

    //一页显示的个数
    private final  int size = 4;

    int num = 0;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;


    public Integer getCountBlog(){
        return blogMapper.findTotalBlog();
    }

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

        if(blog.isPublished()==true){
            Type type = typeService.updateType(blog.getTypeId(),"+");
            List<Tag> tags = tagService.updateTags(blog.getTagIds(),"+");
        }

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
        if(!b.isPublished()){
            Type type = typeService.updateType(blog.getTypeId(),"+");
            List<Tag> tags = tagService.updateTags(blog.getTagIds(),"+");
        }
        blogMapper.updateById(b);
        return b;
    }

    //根据条件查询 返回blog 先封装一些 条件类
    public Page<Blog>  SearchListBlog(BlogSearchVO blogSearchVO,String status){
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
        //返回的blog 状态应为发布状态
        if(status.equals("publish")){
            wrapper.eq("published",1);
        }
        Page<Blog> page = new Page<Blog>(blogSearchVO.getPage(),size) ;// 每一页显示10个数据

        Page<Blog> result = blogMapper.selectPage(page,wrapper.lambda().orderByDesc(Blog::getUpdateTime));

        for (Blog blog:result.getRecords()) {
            Long typeId = blog.getTypeId();
            blog.tags = tagService.idsTotags(blog.getTagIds());
            if(typeId!=null){
                Type type = typeService.getType(typeId);
                if(type!=null){
                    blog.setTypeName(type.getName());
                }
            }
            blog.setUser(userService.findUserByBlog(blog));
        }
        num = result.getOrders().size();
        System.out.println("--------end---------------");
        return result;

}


    //应该是针对于普通显示的情况
    @Override
    public Page<Blog> pageBlog(int current, int size) {
        Page<Blog> page = new Page<Blog>(current,size) ;// 每一页显示一个数据
//        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
////        wrapper.like("")
//        IPage<Blog> iPage = blogMapper.selectPage(page,wrapper);
//        page.setRecords(iPage.getRecords());
        Page<Blog> result = blogMapper.selectPage(page, Wrappers.<Blog>lambdaQuery().ge(Blog::getId, 1).orderByDesc(Blog::getUpdateTime));
        return result;
    }

    @Override
    public void deleteBlog(Long id) {

        Blog blog = blogMapper.selectById(id);
        if(blog.isPublished()==true){
            Type type = typeService.updateType(blog.getTypeId(),"-");
            List<Tag> tags = tagService.updateTags(blog.getTagIds(),"-");
        }
        blogMapper.deleteById(id);

    }

    @Override
    public List<Blog> findTop() {
        return  blogMapper.findTopRecommend();

    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years =  blogMapper.yearByGroup();
        Map<String,List<Blog>> res = new HashMap<>();
        for (String  s: years) {
            List<Blog> blogs = blogMapper.blogByYear(s);
            res.put(s,blogs);
        }
        return res;
    }


    public Blog getBlog(Long id) {

        return blogMapper.selectById(id);
    }


    @Transactional
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.selectById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        b.tags = tagService.idsTotags(b.getTagIds());
        b.setUser(userService.findUserByBlog(blog));
        blogMapper.updateViews(id);
        return b;
    }

    public Integer globalSearchCountBlog(BlogSearchVO blogSearchVO) {
        System.out.println("--------start---------------");
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if( blogSearchVO.getTitle()!=null){
            wrapper.like("title",blogSearchVO.getTitle());
        }
        Page<Blog> page = new Page<Blog>(blogSearchVO.getPage(),size) ;// 每一页显示10个数据
        Page<Blog> result = blogMapper.selectPage(page,wrapper.lambda().orderByDesc(Blog::getUpdateTime));
        return result.getRecords().size();
    }

    public Page<Blog> getTypeBlog(Long id,Integer page) {
//        type的id
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id",id);
        wrapper.eq("published",1);
        Page<Blog> pages = new Page<Blog>(page,size) ;// 每一页显示10个数据
        Page<Blog> result = blogMapper.selectPage(pages,wrapper.lambda().orderByDesc(Blog::getUpdateTime));
        for (Blog blog:result.getRecords()) {
            blog.setUser(userService.findUserByBlog(blog));
        }
        return result;
    }
}
