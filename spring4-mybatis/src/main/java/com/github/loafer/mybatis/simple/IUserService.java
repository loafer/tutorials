package com.github.loafer.mybatis.simple;

import com.github.loafer.mybatis.simple.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author zhaojh
 */
public interface IUserService {
    User find(String id);

    List<User> find(Map<String, Object> parameter);
}
