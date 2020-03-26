package com.yrq.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private String content;
    private Long blogId;
    private Long userId;
    private Date createTime;

}
