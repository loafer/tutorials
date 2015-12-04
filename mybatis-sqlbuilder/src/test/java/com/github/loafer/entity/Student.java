package com.github.loafer.entity;

import com.github.quick4j.core.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhaojh.
 */
@Table(name = "test_student")
public class Student extends AbstractEntity {
    @Id
    private String id;
    @Column(name = "stu_name")
    private String name;
    @Column(name = "stu_age")
    private int age;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
