package com.github.loafer.examples.user;

import java.util.List;
import java.util.Map;

/**
 * Date Created  14-5-21
 *
 * @author zjh
 */
public abstract class AbstractCrudService<T> implements ICrudService<T> {
    @Override
    public T selectOne(String id) {
        return null;
    }

    @Override
    public List<T> selectList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void saveOrUpdate(T bean) {

    }

    @Override
    public void deleteOne(String id) {

    }

    @Override
    public void deleteAll(Map<String, Object> params) {

    }

    @Override
    public void removeOne(String id) {

    }

    @Override
    public void removeAll(Map<String, Object> params) {

    }
}
