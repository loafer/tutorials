package com.github.loafer;

import com.github.loafer.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhaojh.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "/spring-mybatis-config.xml"})
public class MybatisTest {
    @Resource
    private SqlSessionTemplate sessionTemplate;

    @Test
    @Transactional
    public void test1(){
        Teacher teacher = new Teacher();
        teacher.setId(String.valueOf(System.currentTimeMillis()));
        teacher.setName("Jack");
        teacher.setAge(30);
        teacher.setTel("110");
        sessionTemplate.insert("com.github.quick4j.entity.TeacherMapper.insert", teacher);

        Teacher t = sessionTemplate.selectOne("com.github.quick4j.entity.TeacherMapper.selectOne", teacher.getId());
        System.out.println(t);
    }
}
