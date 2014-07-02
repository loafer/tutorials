package com.github.loafer.mybatis.other;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhaojh
 */
public class Repository<T> implements IRepository<T> {

    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public T selectOne(String statement, String id) {
        return sqlSessionTemplate.selectOne(statement, id);
    }

    @Override
    public List<T> selectList(String statement, Object parameter) {
        return sqlSessionTemplate.selectList(statement, parameter);
    }

    @Override
    public int insert(String statement, Object parameter) {
        return sqlSessionTemplate.insert(statement, parameter);
    }

    @Override
    public void batchInsert(String statement, List<?> objectList) {
        if(CollectionUtils.isEmpty(objectList)){
            return;
        }

        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);

        try{
            for(int i=0; i<objectList.size(); i++){
                sqlSession.insert(statement, objectList.get(i));
            }
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(String statement, Object parameter) {
        sqlSessionTemplate.update(statement, parameter);
    }

    @Override
    public void batchUpdate(String statement, List<?> objectList) {
        if(CollectionUtils.isEmpty(objectList)){
            return;
        }

        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);

        try{
            for(int i=0; i<objectList.size(); i++){
                sqlSession.update(statement, objectList.get(i));
            }
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(String statement, Object parameter) {
        sqlSessionTemplate.delete(statement, parameter);
    }
}
