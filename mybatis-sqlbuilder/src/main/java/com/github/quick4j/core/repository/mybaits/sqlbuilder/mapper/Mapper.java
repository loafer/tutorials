package com.github.quick4j.core.repository.mybaits.sqlbuilder.mapper;

import com.github.quick4j.core.repository.mybaits.support.OurSqlBuilder;
import org.apache.ibatis.annotations.*;

/**
 * @author zhaojh.
 */
public interface Mapper<T> {
    @SelectProvider(type = OurSqlBuilder.class, method = OurSqlBuilder.BUILD_FIND_BY_KEY_SQL)
    T findOne(@Param("type") Class<T> entityClass, @Param("id") String id);

    @InsertProvider(type = OurSqlBuilder.class, method = OurSqlBuilder.BUILD_INSERT_SQL)
    void insert(T entity);

    @UpdateProvider(type = OurSqlBuilder.class, method = OurSqlBuilder.BUILD_UPDATE_SQL)
    void update(T entity);

    @DeleteProvider(type = OurSqlBuilder.class, method = OurSqlBuilder.BUILD_DELETE_BY_KEY_SQL)
    void delete(Class<T> entityClass, @Param("id") String id);
}
