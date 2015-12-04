package com.github.quick4j.repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhaojh.
 */
public interface Repository {
    <T> T findOne(Class<T> entityClass, String id);
    <T> void insert(T entity);
    <T> void update(T entity);
    <T> void delete(Class<T> entityClass, String id);

    <T> List<T> findAll(Class<T> entityClass, List<String> ids);
    <T> List<T> findAll(Class<T> entityClass, Map parameters);
    <T> void delete(Class<T> entityClass, List<String> ids);
}
