package com.yrq.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yrq.blog.entity.Type;
import com.yrq.blog.entity.User;
import com.yrq.blog.mapper.TypeMapper;
import com.yrq.blog.service.iservice.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.TypeElement;
import java.util.Date;
import java.util.List;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 11:28
 **/
@Service
public class TypeService implements ITypeService {

    @Autowired
    TypeMapper typeMapper;


    @Override
    public Type checkType(String name) {
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Type type = typeMapper.selectOne(wrapper);
        return type;
    }

    @Override
    public int  saveType(Type type) {
        int i = typeMapper.insert(type);
        return i;
    }

    @Override
    public Type updateTypeName(Long id, String name) {
        //找到这个id的东西 然后把更新后的name添加进去
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Type type = typeMapper.selectOne(wrapper);
        type.setName(name);
        type.setUpdateTime(new Date());
        typeMapper.updateById(type);
        return type;
    }

    @Override
    public Page<Type> pageType(int current,int size) {
        Page<Type> page = new Page<Type>(current,size) ;// 每一页显示一个数据
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
//        wrapper.like("")
        IPage<Type> iPage = typeMapper.selectPage(page,wrapper);
        page.setRecords(iPage.getRecords());
        Page<Type> result = typeMapper.selectPage(page, Wrappers.<Type>lambdaQuery().ge(Type::getId, 1).orderByAsc(Type::getId));
        return result;
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteById(id);
    }

    public Type getType(Long id) {
        return  typeMapper.selectById(id);
    }
    public Type getType(String id) {
        Long i = Long.parseLong(id);
        return  getType(i);
    }

    public List<Type> listType() {
        return  typeMapper.selectList(null);
    }
}
