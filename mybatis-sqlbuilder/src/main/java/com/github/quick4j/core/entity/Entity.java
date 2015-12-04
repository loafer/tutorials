package com.github.quick4j.core.entity;

/**
 * @author zhaojh.
 */
public interface Entity {
    String getId();
    boolean isNew();
    String getMapperNamespace();
}
