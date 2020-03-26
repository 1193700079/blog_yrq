package com.yrq.blog.vo;

import lombok.Data;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-25 16:44
 **/
@Data
public class BlogSearchVO {
    private String title;
    private Long typeId;
    private boolean isRecommend;
    private Integer page;
}
