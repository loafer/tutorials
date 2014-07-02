package com.github.loafer.mybatis.generics;

import com.github.loafer.mybatis.generics.mapper.UserMapper;
import com.github.loafer.mybatis.simple.model.User;

import javax.annotation.Resource;

/**
 * @author zhaojh
 */
public class UserService extends SimpleCrudService<User, UserMapper> {
    @Resource
    public void setUserMapper(UserMapper userMapper){
        this.mapper = userMapper;
    }
}
