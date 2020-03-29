package com.yrq.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from comment where blog_id = #{blogId} order by create_time desc")
    List<Comment> listCommentByBlogId(Long blogId);
}
