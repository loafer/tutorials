package com.github.quick4j;

import static org.hamcrest.CoreMatchers.*;

import com.github.quick4j.entity.Teacher;
import com.github.quick4j.repository.Repository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhaojh.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "/spring-mybatis-config.xml"})
public class AppTest {
    private static Logger logger = LoggerFactory.getLogger(AppTest.class);
    @Resource
    private Repository repository;

    public List<Teacher> prepareTeacherData(){
        List<Teacher> list = new ArrayList<Teacher>();
        for (int i=1; i<=5; i++){
            Teacher teacher = new Teacher();
            teacher.setId(String.valueOf(i));
            teacher.setName(String.format("teacher-%s", i));
            teacher.setAge(20 + i);
            teacher.setTel("110");
            list.add(teacher);
        }
        return list;
    }

    @Test
    public void test1(){
        System.out.println(true || false || true);
        System.out.println(true && false && true);
        Object s = "";
        System.out.println(StringUtils.hasText(s.toString()));
    }

    @Test
    @Transactional
    public void testFindOne(){
        repository.findOne(Teacher.class, String.valueOf(System.currentTimeMillis()));
    }

    @Test
    @Transactional
    public void testInsert(){
        Teacher teacher = new Teacher();
        teacher.setId(String.valueOf(System.currentTimeMillis()));
        teacher.setName("Jack");
        teacher.setAge(35);
        teacher.setTel("110");

        repository.insert(teacher);
        Teacher other = repository.findOne(Teacher.class, teacher.getId());
        Assert.assertThat(other.getName(), is("Jack"));
        repository.findOne(Teacher.class, teacher.getId());
    }

    @Test
    @Transactional
    public void testUpdateById(){
        Teacher teacher = new Teacher();
        teacher.setId(String.valueOf(System.currentTimeMillis()));
        teacher.setName("Jack");
        teacher.setAge(35);
        teacher.setTel("110");
        repository.insert(teacher);

        teacher.setTel("120");
        repository.update(teacher);

        Teacher other = repository.findOne(Teacher.class, teacher.getId());
        logger.info("===>teacher: {}", other);
        Assert.assertThat(other.getTel(), is("120"));
    }

    @Test
    @Transactional
    public void testDeleteById(){
        Teacher teacher = new Teacher();
        teacher.setId(String.valueOf(System.currentTimeMillis()));
        teacher.setName("Jack");
        teacher.setAge(35);
        teacher.setTel("110");
        repository.insert(teacher);

        repository.delete(Teacher.class, teacher.getId());
        Teacher other = repository.findOne(Teacher.class, teacher.getId());
        Assert.assertNull(other);
    }

    @Test
    @Transactional
    public void testSelectByIds(){
//        String[] s1 = new String[]{"1","2","3"};
//        List<String> ids1 = Arrays.asList(s1);
//        repository.findAll(Teacher.class, ids1);


        List<Teacher> list = prepareTeacherData();
        for(Teacher teacher : list){
            repository.insert(teacher);
        }

        List<Teacher> oneGroup = repository.findAll(Teacher.class, Arrays.asList(new String[]{"1", "2", "3"}));
        Assert.assertThat(oneGroup.size(), is(3));
        logger.info("=====>oneGroup: {}", oneGroup);

        List<Teacher> othersGroup = repository.findAll(Teacher.class, Arrays.asList(new String[]{"4", "5"}));
        Assert.assertThat(othersGroup.size(), is(2));
        logger.info("=====>othersGroup: {}", othersGroup);
    }

    @Test
    public void testMultiThreadSelectByIds() throws InterruptedException {
        int count = 4;
        final CountDownLatch doneSignal = new CountDownLatch(count-1);
        for(int i=2; i<=count; i++){
            new Thread(""+i){
                @Override
                public void run() {
                    System.out.println("===> Thread: " + getName() + "running");
                    int len = Integer.parseInt(getName());
                    List<String> ids = new ArrayList<String>();
                    for (int j=1; j<=len; j++){
                        ids.add(String.valueOf(j));
                    }
                    repository.findAll(Teacher.class, ids);
                    doneSignal.countDown();
                }
            }.start();
        }
        doneSignal.await();
    }

    @Test
    @Transactional
    public void testDeleteByIds(){
        String[] ids = new String[]{"1", "2", "3"};
        repository.delete(Teacher.class, Arrays.asList(ids));
    }

    @Test
    @Transactional
    public void testSelectList(){
        List<Teacher> list = prepareTeacherData();
        for(Teacher teacher : list){
            repository.insert(teacher);
        }

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("age", 22);
        parameters.put("name", "teacher-2");
        List<Teacher> teachers = repository.findAll(Teacher.class, parameters);
        logger.info("====> result: {}", teachers);
    }

    @Test
    @Transactional
    public void testMultiThreadSelectList() throws InterruptedException {
        List<Teacher> list = prepareTeacherData();
        for(Teacher teacher : list){
            repository.insert(teacher);
        }

        int count = 4;
        final CountDownLatch doneSignal = new CountDownLatch(count-1);
        for(int i=1; i<=count; i++){
            new Thread(""+i){
                @Override
                public void run() {
                    System.out.println("===> Thread: " + getName() + "running");
                    int i = Integer.parseInt(getName());
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("age", 20 + i);
                    parameters.put("name", "teacher-" + i);
                    List<Teacher> teachers = repository.findAll(Teacher.class, parameters);
                    logger.info("====>result: {}", teachers);
                    doneSignal.countDown();
                }
            }.start();

        }
        doneSignal.await();
    }

}
