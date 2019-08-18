package com.wk.web.service;

import com.wk.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> listUser(){
        ArrayList<User> users = new ArrayList<>(10);
        for (int i = 0; i < 10 ; i++) {
            User user = new User("zs"+i,10+i,"bj"+i);
            users.add(user);
        }
        return users;
    }
}
