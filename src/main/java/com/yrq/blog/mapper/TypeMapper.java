package com.yrq.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrq.blog.entity.Type;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMapper extends BaseMapper<Type> {

    @Select("select count(*) from type")
    int getTotalType();
}
