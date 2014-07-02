package com.github.loafer.mybatis.generics;

import com.github.loafer.mybatis.generics.mapper.ICrudMapper;

import java.util.List;
import java.util.Map;

/**
 * @author zhaojh
 */
public class SimpleCrudService<Model, Mapper extends ICrudMapper> implements ICrudService<Model> {
    protected Mapper mapper;

    @Override
    public Model find(String id) {
        return (Model) mapper.selectOne(id);
    }

    @Override
    public List<Model> find(Map<String, Object> parameter) {
        return mapper.selectList(parameter);
    }
}
