package com.github.quick4j.core.repository.mybaits.sqlbuilder;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojh.
 */
public class SqlBuilderAssistant {
    private Map<String, Class> knownMappers = new HashMap<String, Class>();
    private ClassPool pool = ClassPool.getDefault();
    private SqlSession mybatisSqlSession;

    public SqlBuilderAssistant(SqlSession mybatisSqlSession) {
        this.mybatisSqlSession = mybatisSqlSession;
        mybatisSqlSession.getConfiguration();
    }

    public Class buildAndRegistMapper(Class entityClass, Class superInterface, SqlSession session){
        Class mapper = null;
        String mapperClassName = createEntityMapperClassName(entityClass);
        try {
            CtClass superclass = pool.get(superInterface.getName());
            CtClass ctMapper = pool.makeInterface(mapperClassName, superclass);
            mapper = ctMapper.toClass();
            registMapper(session, entityClass, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return mapper;
    }

    public void registMapper(SqlSession sqlSession, Class entityClass, Class entityMapperClass){
        if(!findInLocal(entityClass)){
            knownMappers.put(entityClass.getName(), entityMapperClass);
        }

        if(!findInMapperRegistry(entityClass, sqlSession)){
            sqlSession.getConfiguration().getMapperRegistry().addMapper(entityMapperClass);
        }
    }

    public boolean hasMapper(SqlSession sqlSession, Class entityClass){
        Configuration configuration = sqlSession.getConfiguration();
        return findInLocal(entityClass) || findInMapperRegistry(entityClass, sqlSession);
    }

    public Class getMapper(Class entityClass){
        return knownMappers.get(entityClass.getName());
    }

    private String createEntityMapperClassName(Class entityClass){
        return String.format("%sMapper", entityClass.getName());
    }

    private boolean findInLocal(Class entityClass){
        String mapperName = String.format("%sMapper", entityClass.getName());
        return knownMappers.containsKey(mapperName);
    }

    private boolean findInMapperRegistry(Class entityClass, SqlSession sqlSession){
        Configuration configuration = sqlSession.getConfiguration();
        String mapperName = String.format("%sMapper", entityClass.getName());
        Collection<Class<?>> collection = configuration.getMapperRegistry().getMappers();
        for (Class mapperClass : collection){
            if(mapperClass.getName().equals(mapperName) && null != configuration.getMapper(mapperClass, sqlSession)){
                knownMappers.put(entityClass.getName(), mapperClass);
                return true;
            }
        }
        return false;
    }

    public boolean hasMappedStatement(String statementName, SqlSession sqlSession){
        return null != sqlSession.getConfiguration().getMappedStatement(statementName, false);
    }
}
