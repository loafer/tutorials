package com.github.quick4j.core.repository.mybaits.support;

import com.github.quick4j.core.entity.Entity;
import com.github.quick4j.core.repository.mybaits.MyBatisRepository;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.MapperBuilder;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.MapperRegistry;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.SqlBuilderAssistant;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.mapper.Mapper;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author zhaojh.
 */
@Component
public class MyBatisRepositoryImpl implements MyBatisRepository {

    private SqlSessionTemplate sqlSessionTemplate;
    private SqlBuilderAssistant sqlBuilderAssistant;

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        sqlBuilderAssistant = new SqlBuilderAssistant(sqlSessionTemplate);
    }

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    public <T extends Entity> T findOne(Class<T> entityClass, String id) {
        Mapper<T> mapper = (Mapper<T>) sqlSessionTemplate.getMapper(getEntityMapper(entityClass));
        return mapper.findOne(entityClass, id);
    }

    @Override
    public <T extends Entity> void insert(T entity) {
        Mapper<T> mapper = (Mapper<T>) sqlSessionTemplate.getMapper(getEntityMapper(entity.getClass()));
        mapper.insert(entity);
    }

    @Override
    public <T extends Entity> void update(T entity) {
        Mapper<T> mapper = (Mapper<T>) sqlSessionTemplate.getMapper(getEntityMapper(entity.getClass()));
        mapper.update(entity);
    }

    @Override
    public <T extends Entity> void delete(Class<T> entityClass, String id) {
        Mapper<T> mapper = (Mapper<T>) sqlSessionTemplate.getMapper(getEntityMapper(entityClass));
        mapper.delete(entityClass, id);
    }

    private Class getEntityMapper(Class entityClass){
        if(sqlBuilderAssistant.hasMapper(sqlSessionTemplate, entityClass)){
            return sqlBuilderAssistant.getMapper(entityClass);
        }

        Class entityMapper = sqlBuilderAssistant.buildAndRegistMapper(entityClass, Mapper.class, sqlSessionTemplate);
        return entityMapper;
    }
}
