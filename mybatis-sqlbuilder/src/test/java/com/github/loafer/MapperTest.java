package com.github.loafer;

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
public class MapperTest {
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    @Transactional
    public void test1(){
        sqlSessionTemplate.selectOne("com.github.loafer.entity.StudentMapper.findOne", String.valueOf(System.currentTimeMillis()));
    }
}
