package com.yrq.blog.service;

import com.yrq.blog.entity.Comment;
import com.yrq.blog.mapper.CommentMapper;
import com.yrq.blog.service.iservice.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-27 16:06
 **/
public class CommentService implements ICommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {

        return commentMapper.listCommentByBlogId(blogId);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getPreCommentId();
        if(parentCommentId != -1){
            //说明不是第一次插入
            comment.setParentComment(commentMapper.selectById(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
        return comment;
    }
}
