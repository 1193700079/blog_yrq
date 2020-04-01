package com.yrq.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.Blog;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("select * from blog where recommend = 1 and published = 1 order by update_time desc limit 8")
    List<Blog> findTopRecommend();

    @Select("select count(*) from blog where deleted = 0 and published = 1")
    int findTotalBlog();

    @Update("update blog set views =views+1 where id=#{id}")
    void updateViews(Long id);

//    @Select("select date_format(update_time,'%Y') as year from blog group by year order by year desc")
////    List<String> yearByGroup();

    @Select("select date_format(update_time,'%Y') from blog group by update_time order by update_time desc")
    List<String> yearByGroup();

    @Select("select id,title,update_time,flag from blog where date_format(update_time,'%Y') = #{s} order by update_time desc")
    List<Blog> blogByYear(String s);



}
