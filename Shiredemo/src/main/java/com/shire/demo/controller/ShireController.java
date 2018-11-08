package com.shire.demo.controller;

import com.shire.demo.entity.User;
import com.shire.demo.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShireController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("test.do")
    @ResponseBody
    public String test(){

        return "test function";
    }

    @GetMapping("save.do")
    @ResponseBody
    public String save(){
        User user = null;
        for(int i=0;i<10;i++){
            user = new User(i,"zhangsan"+i,i*10);
            userRepository.save(user);
        }

        return "save success";
    }

    @GetMapping("listall.do")
    @ResponseBody
    public List<User> findAll(){
        List<User> all = userRepository.findAll();
        return all;
    }
}
