package com.github.loafer.mybatis.generics;


import java.util.List;
import java.util.Map;

/**
 * @author zhaojh
 */
public interface ICrudService<T> {
    T find(String id);

    List<T> find(Map<String, Object> parameter);
}
