package com.yrq.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 21:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("type")
public class Type {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableLogic
    private Integer deleted;

    private Date createTime;
    private Date updateTime;
}
