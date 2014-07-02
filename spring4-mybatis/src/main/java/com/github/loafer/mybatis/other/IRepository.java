package com.github.loafer.mybatis.other;

import java.util.List;

/**
 * @author zhaojh
 */
public interface IRepository<T> {
    T selectOne(String statement, String id);

    List<T> selectList(String statement, Object parameter);

    int insert(String statement, Object parameter);

    void batchInsert(String statement, List<?> objectList);

    void update(String statement, Object parameter);

    void batchUpdate(String statement, List<?> objectList);

    void delete(String statement, Object parameter);
}
