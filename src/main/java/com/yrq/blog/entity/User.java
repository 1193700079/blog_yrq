package com.yrq.blog.entity;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-23 11:47
 **/

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String nickname;
    private String username;

//    @TableField(select = false)   //查询时不显示这个值
    private String password;

    private String email;
    private String avatar;
    private Integer type;

    //权限
    private String perms;
    //盐值
    private String salt;

    private Date createTime;
    private Date updateTime;

}
