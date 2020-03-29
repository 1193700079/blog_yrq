package com.yrq.blog;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 11:05
 **/
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.entity.User;
import com.yrq.blog.mapper.BlogMapper;
import com.yrq.blog.mapper.TypeMapper;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.service.UserService;
import com.yrq.blog.vo.BlogSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TypeTest {
    @Resource
    private TypeMapper typeMapper;

    @Resource
    private BlogService blogService;

    @Resource
    private UserService userService;

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void SearchListBlog(){
        BlogSearchVO blogSearchVO = new BlogSearchVO();
        blogSearchVO.setPage(1);
        Page<Blog>  page= blogService.SearchListBlog(blogSearchVO,"");
        List<Blog> list = page.getRecords();
        for (Blog blog: list) {
            System.out.println(blog.toString());
        }
    }


    @Test
    public  void  user(){
        User user = userService.selectUser("yrq");
        System.out.println(user.getPassword());
    }


    public void  tesat(){
        List<String> years =  blogMapper.yearByGroup();
        Map<String,List<Blog>> res = new HashMap<>();
        for (String  s: years) {
            System.out.println("时间"+s);
            List<Blog> blogs = blogMapper.blogByYear(s);
            res.put(s,blogs);
        }
        System.out.println(res);

    }
}
