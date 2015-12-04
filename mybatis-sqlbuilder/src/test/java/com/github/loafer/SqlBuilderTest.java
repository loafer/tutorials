package com.github.loafer;

import static org.hamcrest.CoreMatchers.*;

import com.github.loafer.entity.Teacher;
import com.github.quick4j.core.repository.mybaits.MyBatisRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhaojh.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "/spring-mybatis-config.xml"})
public class SqlBuilderTest {
    @Resource
    private MyBatisRepository repository;

    @Test
    public void testSelectOne(){
        repository.findOne(Teacher.class, String.valueOf(System.currentTimeMillis()));
//        repository.findOne(Teacher.class, String.valueOf(System.currentTimeMillis()));
    }

    @Test
    @Transactional
    public void testInsert(){
        Teacher teacher = new Teacher();
        teacher.setId(String.valueOf(System.currentTimeMillis()));
        teacher.setName("Tom");
        teacher.setAge(25);
        teacher.setTel("110");
        repository.insert(teacher);

        Teacher teacher2 = repository.findOne(Teacher.class, teacher.getId());
        Assert.assertThat(teacher2.getName(), is("Tom"));
    }

    @Test
    public void testUpdate(){
        Teacher teacher = new Teacher();
        teacher.setId(String.valueOf(System.currentTimeMillis()));
        teacher.setName("Jack");
        teacher.setAge(25);
        teacher.setTel("110");
        repository.insert(teacher);

        teacher.setAge(30);
        repository.update(teacher);
    }
}
