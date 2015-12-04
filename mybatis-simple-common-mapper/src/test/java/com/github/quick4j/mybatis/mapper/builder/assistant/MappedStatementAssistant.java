package com.github.quick4j.mybatis.mapper.builder.assistant;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;

/**
 * @author zhaojh.
 */
public class MappedStatementAssistant {
    private MappedStatementAssistant(){}

    public static boolean hasStatementInSqlSession(String statementName, SqlSession sqlSession){
        return sqlSession.getConfiguration().hasStatement(statementName, false);
    }

    public static MappedStatement getMappedStatement(String statementName, SqlSession sqlSession){
        return sqlSession.getConfiguration().getMappedStatement(statementName);
    }
}
