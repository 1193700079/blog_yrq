package com.yrq.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 21:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("comment")
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private Long blogId;
    private String avatar;
    private Long preCommentId;
    private Date createTime;

    @TableField(exist = false)
    private Blog blog;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Comment parentComment;

    @TableField(exist = false)
    private List<Comment> replyComments = new ArrayList<>();

    @TableField(exist = false)
    private boolean adminComment;

}
