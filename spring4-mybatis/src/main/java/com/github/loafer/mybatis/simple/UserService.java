package com.github.loafer.mybatis.simple;

import com.github.loafer.mybatis.simple.mapper.UserMapper;
import com.github.loafer.mybatis.simple.model.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhaojh
 */
public class UserService implements IUserService {

    private UserMapper userMapper;

    @Resource
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User find(String id) {
        return userMapper.selectOne(id);
    }

    @Override
    public List<User> find(Map<String, Object> parameter) {
        return userMapper.selectList(parameter);
    }
}
