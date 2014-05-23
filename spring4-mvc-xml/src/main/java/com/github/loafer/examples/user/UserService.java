package com.github.loafer.examples.user;


import com.github.loafer.examples.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date Created  14-5-21
 *
 * @author zjh
 */
public class UserService  extends AbstractCrudService<User> implements IUserService{
    private List<User> userList = new ArrayList<>();

    @Override
    public User selectOne(String id) {
        for(User user : userList){
            if(user.getId().equals(id)){
                return user;
            }
        }

        return null;
    }

    @Override
    public List<User> selectList(Map<String, Object> params) {
        String age = (String) params.get("age");
        if(params != null && StringUtils.isNotBlank(age)){
            List<User> list = new ArrayList<>();
            for(User user:userList){
                if(user.getAge() == Integer.parseInt(age)){
                    list.add(user);
                }
            }
            return list;
        }

        return userList;
    }

    @Override
    public void saveOrUpdate(User user) {
        if(StringUtils.isBlank(user.getId())){
            user.setId(generateID());
            save(user);
        }else {
            update(user);
        }
    }

    @Override
    public void removeOne(String id) {
        for(User user : userList){
            if(user.getId().equals(id)){
                userList.remove(user);
                return;
            }
        }
    }

    private String generateID(){
        return String.valueOf(System.currentTimeMillis());
    }

    private void save(User user){
        userList.add(user);
    }

    public void update(User user){
        if(userList.contains(user)){
            int index = userList.indexOf(user);
            User u = userList.get(index);
            u.setAge(user.getAge());
            u.setName(user.getName());
        }
    }
}
