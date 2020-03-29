package com.yrq.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("tag")
public class Tag {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableLogic
    private Integer deleted;

    //每次插入发布的一个blog 则++
    private Integer blogSize;

    @TableField(exist = false)
    private List<Blog> blogs = new ArrayList<>();

    private Date createTime;

    private Date updateTime;
}
