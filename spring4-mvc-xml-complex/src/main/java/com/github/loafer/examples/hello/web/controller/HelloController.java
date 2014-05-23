package com.github.loafer.examples.hello.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Date Created  14-5-22
 *
 * @author zjh
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String listing(@RequestParam(value = "name", required = false) String name, Model model){
        String value = "World";
        if(StringUtils.hasText(name)) value = name;
        model.addAttribute("name", value);
        return "hello/index";
    }
}
