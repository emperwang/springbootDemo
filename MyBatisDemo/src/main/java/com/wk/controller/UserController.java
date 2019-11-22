package com.wk.controller;

import com.wk.Entity.User;
import com.wk.Entity.UserBean;
import com.wk.IService.IUserService;
import com.wk.IService.UserBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserBeanService beanService;

    @RequestMapping(value = "/listUser",method=RequestMethod.GET)
    @ResponseBody
    public List<User> listUser(){
        List<User> all = userService.findAll();
        return all;
    }
    @RequestMapping(value = "/listbean.do",method=RequestMethod.GET)
    @ResponseBody
    public List<UserBean> listUserBean(){
        return beanService.getAllUserBean();
    }

    @RequestMapping(value = "/insert.do",method=RequestMethod.GET)
    @ResponseBody
    public String insertUserBean(){
        UserBean userBean = new UserBean();
        userBean.setAddress("gd-bj");
        userBean.setAge(10);
        userBean.setName("wk");

        beanService.insertAndDelete(userBean);
        return userBean.toString();
    }

    @GetMapping("idorder.do")
    @ResponseBody
    public List<UserBean> getOrder(){
        return beanService.getBeanOrder();
    }

    @GetMapping("distinct.do")
    @ResponseBody
    public List<UserBean> getBeanDistinct(){

        return beanService.getDistinct();
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "this is test";
    }
}
