package com.yrq.blog;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 11:05
 **/
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.mapper.TypeMapper;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.vo.BlogSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TypeTest {
    @Resource
    private TypeMapper typeMapper;

    @Resource
    private BlogService blogService;

    //逻辑删除
    @Test
    public void testDeleteById(){
        int i = typeMapper.deleteById(3L);
        System.out.println(i);

    }

    @Test
    public void SearchListBlog(){
        BlogSearchVO blogSearchVO = new BlogSearchVO();
        blogSearchVO.setPage(1);
        Page<Blog>  page= blogService.SearchListBlog(blogSearchVO);
        List<Blog> list = page.getRecords();
        for (Blog blog: list) {
            System.out.println(blog.toString());
        }

    }

}
