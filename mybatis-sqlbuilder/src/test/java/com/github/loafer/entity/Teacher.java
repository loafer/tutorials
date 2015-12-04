package com.github.loafer.entity;

import com.github.quick4j.core.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * @author zhaojh.
 */
@Table(name = "test_teacher")
public class Teacher extends AbstractEntity{
    @Id
    private String id;
    @Column(name = "teacher_name")
    private String name;
    @Column(name = "teacher_age")
    private int age;
    @Column(name = "teacher_tel")
    private String tel;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                '}';
    }
}
