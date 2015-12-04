package com.github.quick4j.core.repository.mybaits.support;

import com.github.quick4j.core.entity.Entity;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.EntityAssistant;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.EntityPersistentInfo;
import com.github.quick4j.core.repository.mybaits.sqlbuilder.metadata.ColumnMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @author zhaojh.
 */
public class OurSqlBuilder {
    private static final Logger logger = LoggerFactory.getLogger(OurSqlBuilder.class);

    public static final String BUILD_FIND_BY_KEY_SQL = "buildFindByKeySql";
    public static final String BUILD_FIND_ALL_SQL = "buildFindAllSql";
    public static final String BUILD_INSERT_SQL = "buildInsertSql";
    public static final String BUILD_UPDATE_SQL = "buildUpdateSql";
    public static final String BUILD_DELETE_BY_KEY_SQL = "buildDeleteByKeySql";

    public String buildFindByKeySql(Map<String, Object> parameter){
        Class entityClass = (Class) parameter.get("type");
        EntityPersistentInfo entityPersistentInfo = EntityAssistant.parse(entityClass);
        final String tableName = entityPersistentInfo.getTableName();
        final String[] columnNames = entityPersistentInfo.getColumns();

        logger.info("===> table name: {}", tableName);
        logger.info("===> columns: {}", Arrays.asList(columnNames));

        final StringBuilder columns = new StringBuilder();
        columns.append("id");
        if(columnNames.length > 0){
            columns.append(',').append(StringUtils.arrayToDelimitedString(columnNames, ","));
        }

        String sql = new SQL(){{
            SELECT(columns.toString());
            FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();

        return sql;
    }

    public String buildFindAllSql(){
        return null;
    }

    public String buildInsertSql(final Entity entity) throws IllegalAccessException {
        EntityPersistentInfo entityPersistentInfo = EntityAssistant.parse(entity.getClass());
        final String tableName = entityPersistentInfo.getTableName();
        final List<EntityPersistentInfo.MappedColumn> mappedColumns = entityPersistentInfo.getMappedColumns();

        logger.info("table name: {}", tableName);
        logger.info("mappedColumns: {}", mappedColumns);

        String sql = new SQL(){{
            INSERT_INTO(tableName);
            VALUES("id", "#{id}");
            for (EntityPersistentInfo.MappedColumn mappedColumn : mappedColumns){
                if(null != mappedColumn.getValue(entity)){
                    VALUES(mappedColumn.getName(), String.format("#{%s}", mappedColumn.getProperty()));
                }
            }
        }}.toString();

        return sql;
    }

    public String buildUpdateSql(){
        return null;
    }

    public String buildDeleteByKeySql(Map<String, Object> parameter){
        return null;
    }

}
