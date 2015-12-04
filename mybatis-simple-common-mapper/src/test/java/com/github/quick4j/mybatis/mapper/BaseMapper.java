package com.github.quick4j.mybatis.mapper;

import com.github.quick4j.mybatis.mapper.builder.SqlBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaojh.
 */
public interface BaseMapper<T> {
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_SELECT_BY_ID_SQL)
    T selectById(@Param("type")Class<T> entityClass, @Param("id")String id);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_SELECT_BY_IDS_SQL)
    List<T> selectByIds(@Param("type")Class<T> entityClass, @Param("ids")List<String> ids);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_SELECT_LIST_SQL)
    List<T> selectPaging(@Param("type")Class<T> entityClass, @Param("queriedParams")Map<String, Object> parameter);

    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_SELECT_LIST_SQL)
    List<T> selectList(@Param("type")Class<T> entityClass, @Param("queriedParams")Map<String, Object> parameter);

    @InsertProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_INSERT_SQL)
    void insert(T entity);

    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_UPDATE_BY_ID_SQL)
    void updateById(T entity);

    @DeleteProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_DELETE_BY_ID_SQL)
    void deleteById(@Param("type")Class<T> entityClass, @Param("id")String id);

    @DeleteProvider(type = SqlBuilder.class, method = SqlBuilder.BUILD_DELETE_BY_IDS_SQL)
    void deleteByIds(@Param("type")Class<T> entityClass, @Param("ids")List<String> ids);
}
