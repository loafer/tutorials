package com.github.loafer.examples.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaojh on 14-5-28.
 */
@Component(value = "userService")
public class UserService implements IUserService {
    private static List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();

    static {
        String[] ages = new String[]{"0", "24", "25", "28", "30", "26"};
        for(int i=1; i<=5; i++){
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("name", "user"+i);
            m.put("age", ages[i]);
            userList.add(m);
        }
    }

    @Override
    public List selectList() {
        return userList;
    }
}
