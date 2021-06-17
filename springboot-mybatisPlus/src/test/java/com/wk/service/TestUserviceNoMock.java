package com.wk.service;

import com.wk.TestParent;
import com.wk.entity.Users;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2021/6/17 22:09
 * @Description
 */
public class TestUserviceNoMock extends TestParent {

    @Autowired
    private UserService userService;

    @Test
    public void testGetAll(){
        List<Users> all = userService.getAll();
        System.out.println(all.toString());
    }
}
