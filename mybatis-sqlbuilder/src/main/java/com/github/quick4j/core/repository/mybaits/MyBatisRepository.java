package com.github.quick4j.core.repository.mybaits;

import com.github.quick4j.core.entity.Entity;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * @author zhaojh.
 */
public interface MyBatisRepository {
    SqlSessionTemplate getSqlSessionTemplate();
    <T extends Entity> T findOne(Class<T> clazz, String id);
    <T extends Entity> void insert(T entity);
    <T extends Entity> void update(T entity);
    <T extends Entity> void delete(Class<T> clazz, String id);
}
