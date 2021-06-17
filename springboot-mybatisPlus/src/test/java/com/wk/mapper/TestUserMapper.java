package com.wk.mapper;

import com.wk.TestParent;
import com.wk.entity.Users;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2021/6/17 21:57
 * @Description
 */
public class TestUserMapper extends TestParent {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<Users> users = userMapper.selectList(null);
        System.out.println(users.toString());
    }

    @Test
    public void testInsert(){
        Users users = new Users();
        users.setName("zhangsan").setAge(100);
        int insert = userMapper.insert(users);
        System.out.println("insert num:" + insert);
    }

    @Test
    public void testUpdate(){
        Users users = new Users();
        users.setName("zhangsanUpdateName").setAge(100).setId(41);
        int i = userMapper.updateById(users);
        System.out.println("update num : " + i);
    }
}
