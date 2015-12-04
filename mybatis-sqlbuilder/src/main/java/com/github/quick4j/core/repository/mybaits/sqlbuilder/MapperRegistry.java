package com.github.quick4j.core.repository.mybaits.sqlbuilder;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojh.
 */
public class MapperRegistry {
    private final Map<String, Class> knownMappers = new HashMap<String, Class>();

    public void addMapper(Class entityClass, Class entityMapperClass){
        String entityClassName = entityClass.getName();
        if(!knownMappers.containsKey(entityClassName)){
            knownMappers.put(entityClassName, entityMapperClass);
        }
    }

    public Class getMapper(Class entityClass){
        return knownMappers.get(entityClass.getName());
    }

    public boolean exist(Class entityClass){
        return knownMappers.containsKey(entityClass.getName());
    }
}
