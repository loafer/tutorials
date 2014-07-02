package com.github.loafer.mybatis;

import com.github.loafer.mybatis.generics.ICrudService;
import com.github.loafer.mybatis.generics.UserService;
import com.github.loafer.mybatis.simple.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhaojh
 */
public class UserServiceForGenerisTest {
    public ApplicationContext context;

    @Before
    public void prepare(){
        context = new ClassPathXmlApplicationContext("generics.xml");
    }

    @Test
    public void selectOneTest(){
        UserService userService = (UserService) context.getBean("userService");
        User user = userService.find("66DBB0064D4543A18FA8508BDC144434");
        System.out.println(user);
        Assert.assertEquals("井宪军", user.getRealName());
    }
}
