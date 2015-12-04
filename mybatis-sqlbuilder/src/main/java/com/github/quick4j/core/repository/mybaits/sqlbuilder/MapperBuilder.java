package com.github.quick4j.core.repository.mybaits.sqlbuilder;

import javassist.ClassPool;
import javassist.CtClass;
import org.springframework.util.ClassUtils;

/**
 * @author zhaojh.
 */
public class MapperBuilder {

    public Class build(Class entityClass, Class superInterface){
        ClassPool pool = ClassPool.getDefault();
        Class mapper = null;
        try {
            CtClass superclass = pool.get(superInterface.getName());
            String packageName = getPackageName(entityClass);
            CtClass ctMapper = pool.makeInterface(createEntityMapperClassName(packageName, entityClass.getSimpleName()), superclass);
            mapper = ctMapper.toClass();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mapper;
    }

    private String createEntityMapperClassName(String packageName, String entitySimpleName){
        return String.format("%s.%sMapperInline", packageName, entitySimpleName);
    }

    private String getPackageName(Class clazz){
        return ClassUtils.getPackageName(clazz);
    }
}
