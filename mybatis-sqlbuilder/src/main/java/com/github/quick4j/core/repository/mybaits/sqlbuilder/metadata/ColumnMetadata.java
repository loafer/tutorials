package com.github.quick4j.core.repository.mybaits.sqlbuilder.metadata;

/**
 * @author zhaojh.
 */
public class ColumnMetadata {
    private String column;
    private String property;
    private Object value;


    public ColumnMetadata(String column, String property, Object value) {
        this.column = column;
        this.value = value;
        this.property = property;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    public String getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return "{" +
                "column='" + column + '\'' +
                ", property='" + property + '\'' +
                ", value=" + value +
                '}';
    }
}
