package com.github.loafer.entity;

import org.apache.ibatis.annotations.Select;

/**
 * @author zhaojh.
 */
public interface StudentMapper {
    @Select("select id, stu_name, stu_age from test_student where id=#{id}")
    Student findOne(String id);
}
