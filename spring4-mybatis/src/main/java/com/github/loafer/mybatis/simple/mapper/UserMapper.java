package com.github.loafer.mybatis.simple.mapper;

import com.github.loafer.mybatis.generics.mapper.ICrudMapper;
import com.github.loafer.mybatis.simple.model.User;

import java.util.List;

/**
 * @author zhaojh
 */
public interface UserMapper{

    User selectOne(String id);

    List<User> selectList(Object parameter);
}
