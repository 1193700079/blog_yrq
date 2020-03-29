package com.yrq.blog;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 11:52
 **/
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Blog;
import com.yrq.blog.mapper.BlogMapper;
import com.yrq.blog.mapper.UserMapper;
import com.yrq.blog.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SimpleTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BlogMapper blogMapper;
    //查询
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    //插入
    @Test
    public void save() {
        User user = new User();
        user.setId(123416L);
        user.setUsername("qq123456");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setEmail("123456@qq.com");
        userMapper.insert(user);
    }
    //修改
    @Test
    public void update(){
        User user = new User();
        user.setId(1L);
        userMapper.updateById(user);

    }

    //删除
    @Test
    public void delete(){
        User qUser = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<User>(qUser);
        User user = userMapper.selectOne(wrapper);
        userMapper.deleteById(user.getId());

    }

//    分布式ID测试
    @Test
    public void test() {
        User user = new User();
        user.setPassword("123456");
        user.setEmail("sjadnas@");
        userMapper.insert(user);
        Assert.assertEquals(Long.valueOf(1L), user.getId());
    }

    @Test
        public void page(){
            Page<Blog> page = new Page<Blog>(2,2) ;// 第当前页显示size个数据
            QueryWrapper<Blog> wrapper = new QueryWrapper<>();
            IPage<Blog> iPage = blogMapper.selectPage(page,wrapper);
            System.out.println("数据总条数: "+iPage.getTotal() );
            System.out.println("数据总页数: "+iPage.getPages());
            System.out.println("当前页数: "+iPage.getCurrent());

            List<Blog> records  = iPage.getRecords();
            for (Blog record: records) {
                System.out.println(record);
            }
    }

    @Test
    public void page2(){
        Page<User> page = new Page<User>(2,2) ;// 第当前页显示size个数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.like("")
        IPage<User> iPage = userMapper.selectPage(page,wrapper);
        System.out.println("数据总条数: "+iPage.getTotal() );
        System.out.println("数据总页数: "+iPage.getPages());
        System.out.println("当前页数: "+iPage.getCurrent());

        List<User> records  = iPage.getRecords();
        for (User record: records) {
            System.out.println(record);
        }
    }

//    @Test
//    public void saveBlog(){
//        Blog blog = new Blog();
//        blog.setCreateTime(new Date());
//        blog.setUpdateTime(new Date());
//        blog.setViews(0);
//
//        return blogMapper.insert(blog);
//    }


}
