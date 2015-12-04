package com.github.quick4j.mybatis.mapper;

import com.github.quick4j.mybatis.mapper.builder.assistant.EntityAssistant;
import com.github.quick4j.mybatis.mapper.builder.assistant.EntityPersistentInfo;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhaojh.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class})
})
public class MapperInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(MapperInterceptor.class);

    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;
    private static final int ROWBOUNDS_INDEX = 2;
    private static final int RESULT_HANDLER_INDEX = 3;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final String sql = ms.getBoundSql(parameter).getSql();

        logger.info("===> statement id: {}", ms.getId());
        logger.info("===> parameter: {}", parameter);
        logger.info("===> sql: {}", sql);

        if(ms.getSqlSource() instanceof ProviderSqlSource){
            switch (ms.getSqlCommandType()){
                case SELECT:
                    MappedStatement newStatement = newSelectMappedStatement(ms, (Class) ((MapperMethod.ParamMap)parameter).get("type"));
                    queryArgs[MAPPED_STATEMENT_INDEX] = newStatement;
                    break;
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor){
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {}

    private MappedStatement newSelectMappedStatement(final MappedStatement ms, Class entityClass){
        String statementId = ms.getId() + "-InLine";
        Configuration configuration = ms.getConfiguration();

        if(configuration.hasStatement(statementId, false)){
            return configuration.getMappedStatement(statementId);
        }

        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(
                configuration,
                statementId,
                ms.getSqlSource(),
                SqlCommandType.SELECT
        );

        statementBuilder.resource(ms.getResource())
                .fetchSize(ms.getFetchSize())
                .statementType(ms.getStatementType())
                .keyGenerator(ms.getKeyGenerator())
                .timeout(ms.getTimeout())
                .resultSetType(ms.getResultSetType())
                .cache(ms.getCache())
                .flushCacheRequired(ms.isFlushCacheRequired())
                .useCache(ms.isUseCache())
                .parameterMap(ms.getParameterMap());

        String[] keyProperties = ms.getKeyProperties();
        statementBuilder.keyProperty(keyProperties == null ? null : keyProperties[0]);


        //构建resultMap
        List<ResultMapping> resultMappings = new ArrayList<ResultMapping>();
        EntityPersistentInfo entityPersistentInfo = EntityAssistant.parse(entityClass);
        List<EntityPersistentInfo.MappedColumn> mappedColumns = entityPersistentInfo.getMappedColumns();
        for (EntityPersistentInfo.MappedColumn mappedColumn : mappedColumns){
            resultMappings.add(
                    new ResultMapping.Builder(
                            configuration,
                            mappedColumn.getProperty(),
                            mappedColumn.getName(),
                            mappedColumn.getJavaType()
                    ).build()
            );
        }

        ResultMap.Builder resultMapBuilder = new ResultMap.Builder(
                configuration,
                statementBuilder.id() + "-Inline",
                entityClass,
                resultMappings
        );
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        resultMaps.add(resultMapBuilder.build());
        statementBuilder.resultMaps(resultMaps);
        MappedStatement statement = statementBuilder.build();
        if(!configuration.hasStatement(statement.getId(), false)){
            configuration.addMappedStatement(statement);
        }

        return statement;
    }
}
