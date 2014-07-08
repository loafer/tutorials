package com.github.loafer.examples.user.web;

import com.github.loafer.examples.user.exception.UserNotFoundException;
import com.github.loafer.examples.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaojh
 */
@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String listing(){
        return "user/index";
    }

    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.GET,
            produces = "application/json;charset=utf-8"
    )
    @ResponseBody
    public User getUser(@PathVariable("name") String name){
        if(true){
            throw new UserNotFoundException(name);
        }
        return new User(name);
    }
}
