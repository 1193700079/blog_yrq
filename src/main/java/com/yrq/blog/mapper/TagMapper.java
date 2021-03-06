package com.yrq.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.Tag;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select count(*) from tag ")
    int findTotalTag();
}
