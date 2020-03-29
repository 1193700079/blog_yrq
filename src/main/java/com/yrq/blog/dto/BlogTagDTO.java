package com.yrq.blog.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-26 09:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_tag")
//blog和tag的表连接
public class BlogTagDTO{
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long blogId;
    private Long tagId;
}
