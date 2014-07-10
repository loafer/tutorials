package com.github.loafer.examples.web;

import com.github.loafer.examples.exception.UserNotFoundException;
import com.github.loafer.examples.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zhaojh
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping("/test.htm")
    public String test(){
        if(true){
            throw new RuntimeException("this is runtime exception from test method.");
        }
        return "hello";
    }

    @RequestMapping(
            value = "/hello/{name}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public User getUser(@PathVariable("name") String name){
        if(true){
            throw new UserNotFoundException(name);
        }
        return new User(name);
    }
}
