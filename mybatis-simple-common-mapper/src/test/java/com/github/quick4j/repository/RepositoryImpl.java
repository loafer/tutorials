package com.github.quick4j.repository;

import com.github.quick4j.mybatis.mapper.BaseMapper;
import com.github.quick4j.mybatis.mapper.builder.SqlBuilder;
import com.github.quick4j.mybatis.mapper.builder.assistant.MappedStatementAssistant;
import com.github.quick4j.mybatis.mapper.builder.assistant.MapperAssistant;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhaojh.
 */
@Component("Repository")
public class RepositoryImpl implements Repository {
    private static Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private SqlSessionTemplate sqlSessionTemplate;
    private MapperAssistant mapperAssistant;

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapperAssistant = new MapperAssistant(this.sqlSessionTemplate);
    }

    @Override
    public <T> T findOne(Class<T> entityClass, String id) {
        String statementName = String.format("%sMapper.%s", entityClass.getName(), SqlBuilder.SELECT_BY_ID);
        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)
                && isNotProviderSqlSource(statementName)){
            return sqlSessionTemplate.selectOne(statementName, id);
        }

        Class mapperClass = getEntityMapperFor(entityClass);
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        return mapper.selectById(entityClass, id);
    }

    @Override
    public <T> void insert(T entity) {
        String entityClassName = entity.getClass().getName();
        String statementName = String.format("%sMapper.%s", entityClassName, SqlBuilder.INSERT);

        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)){
            sqlSessionTemplate.insert(statementName, entity);
            return;
        }

        Class mapperClass = getEntityMapperFor(entity.getClass());
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        mapper.insert(entity);
    }

    @Override
    public <T> void update(T entity) {
        String entityClassName = entity.getClass().getName();
        String statementName = String.format("%sMapper.%s", entityClassName, SqlBuilder.UPDATE_BY_ID);

        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)){
            sqlSessionTemplate.update(statementName, entity);
            return;
        }

        Class mapperClass = getEntityMapperFor(entity.getClass());
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        mapper.updateById(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, String id) {
        String entityClassName = entityClass.getName();
        String statementName = String.format("%sMapper.%s", entityClassName, SqlBuilder.DELETE_BY_ID);

        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)
                && isNotProviderSqlSource(statementName)){
            sqlSessionTemplate.delete(statementName, id);
            return;
        }

        Class mapperClass = getEntityMapperFor(entityClass);
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        mapper.deleteById(entityClass, id);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass, List<String> ids) {
        String entityClassName = entityClass.getName();
        String statementName = String.format("%sMapper.%s", entityClassName, SqlBuilder.SELECT_BY_IDS);

        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)
                && isNotProviderSqlSource(statementName)){
            return sqlSessionTemplate.selectList(statementName, ids);
        }

        Class mapperClass = getEntityMapperFor(entityClass);
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        return mapper.selectByIds(entityClass, ids);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass, Map parameters) {
        String entityClassName = entityClass.getName();
        String statementName = String.format("%sMapper.%s", entityClassName, SqlBuilder.SELECT_LIST);

        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)
                && isNotProviderSqlSource(statementName)){
            return sqlSessionTemplate.selectList(statementName, parameters);
        }

        Class mapperClass = getEntityMapperFor(entityClass);
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        return mapper.selectList(entityClass, parameters);
    }

    @Override
    public <T> void delete(Class<T> entityClass, List<String> ids) {
        String entityClassName = entityClass.getName();
        String statementName = String.format("%sMapper.%s", entityClassName, SqlBuilder.DELETE_BY_IDS);

        if(MappedStatementAssistant.hasStatementInSqlSession(statementName, sqlSessionTemplate)
                && isNotProviderSqlSource(statementName)){
            sqlSessionTemplate.delete(statementName, ids);
            return;
        }

        Class mapperClass = getEntityMapperFor(entityClass);
        BaseMapper<T> mapper = (BaseMapper<T>) sqlSessionTemplate.getMapper(mapperClass);
        mapper.deleteByIds(entityClass, ids);
    }

    private Class getEntityMapperFor(Class entityClass){
        String mapperName = String.format("%sMapper", entityClass.getName());

        if(mapperAssistant.hasMapper(mapperName)){
            return mapperAssistant.getMapper(mapperName);
        }

        return mapperAssistant.buildAndRegistMapper(mapperName, BaseMapper.class);
    }

    private boolean isNotProviderSqlSource(String statementName){
        return !(MappedStatementAssistant.getMappedStatement(statementName, sqlSessionTemplate).getSqlSource() instanceof ProviderSqlSource);
    }
}
