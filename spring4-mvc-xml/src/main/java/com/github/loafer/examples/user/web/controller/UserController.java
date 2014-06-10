package com.github.loafer.examples.user.web.controller;

import com.github.loafer.examples.user.IUserService;
import com.github.loafer.examples.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Date Created  14-5-20
 *
 * @author zjh
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    /**
     * 显示列表页面
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String listing(@RequestParam(value = "age", required = false) String age, Model model){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("age", age);

        List userList =  userService.selectList(params);
        model.addAttribute("userList", userList);
        return "user/index";
    }

    /**
     * 显示增加页面
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showAddPage(){
        return "user/edit";
    }

    /**
     * 显示编辑页面
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
    public String showEditPage(@PathVariable("userid") String userid, Model model){
        User user = userService.selectOne(userid);
        model.addAttribute("user", user);
        return "user/edit";
    }

    /**
     * 保存操作数据
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String save(User user){
        logger.info(user.toString());
        userService.saveOrUpdate(user);
        return "redirect:user";
    }

    @RequestMapping(value = "/{userid}", method = RequestMethod.DELETE , produces = "application/json")
    @ResponseBody
    public Map<String, Boolean> delete(@PathVariable("userid") String userid){
        logger.info("delete {}", userid);
        userService.removeOne(userid);
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        m.put("success", true);
        return m;
    }
}
