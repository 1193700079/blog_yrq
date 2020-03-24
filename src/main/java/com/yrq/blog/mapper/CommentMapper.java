package com.yrq.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
