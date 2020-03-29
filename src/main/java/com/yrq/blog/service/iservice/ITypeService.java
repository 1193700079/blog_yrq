package com.yrq.blog.service.iservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Type;

import java.util.List;


/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 11:28
 **/
public interface ITypeService{

    //检查是否存在
    Type checkType(String name);

    //添加
    int saveType(Type type);

    //更新
    Type updateTypeName(Long id, String name);

    //分页
    Page<Type> pageType(int current, int size);

    //删除
    void deleteType(Long id);

    //显示最新的num个Type
    List<Type> listTypeTop(int num);

    int getTotalType();
}
