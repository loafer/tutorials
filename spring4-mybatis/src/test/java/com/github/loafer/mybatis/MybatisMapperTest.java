package com.github.loafer.mybatis;

import com.github.loafer.mybatis.simple.IUserService;
import com.github.loafer.mybatis.simple.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaojh
 */
public class MybatisMapperTest {
    ApplicationContext context;

    @Before
    public void prepare(){
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    @Ignore
    public void selectOneTest(){
        IUserService userService = (IUserService) context.getBean("userService");
        User user = userService.find("66DBB0064D4543A18FA8508BDC144434");
        System.out.println(user);
        Assert.assertEquals("井宪军", user.getRealName());
    }

    @Test
    public void selectListTest(){
        IUserService userService = (IUserService) context.getBean("userService");
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("loginname", "jingxianjun.slyt");
        List<User> list = userService.find(parameter);
        System.out.println(list);
        Assert.assertEquals(1, list.size());
    }
}
