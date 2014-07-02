package com.github.loafer.mybatis.generics.mapper;

import java.util.List;

/**
 * @author zhaojh
 */
public interface ICrudMapper<T> {
    T selectOne(String id);

    List<T> selectList(Object parameter);

    int insert(T parameter);

    void update(T parameter);

    void delete(String id);
}
