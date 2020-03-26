package com.yrq.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yrq.blog.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog")
public class Blog {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    //需要用到懒加载
    private String content;

    private String description;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Long userId;
    private Long typeId;
    private String tagIds;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;

    @TableLogic
    private Integer deleted;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<Tag> tags;

    @TableField(exist = false)
    private String typeName;



    public void init() {
        this.tagIds = tagsToIds(this.getTags());
        System.out.println(typeId);
//        this.typeName = typeService.getType(typeId).getName();
    }



    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (tags!=null && !tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
