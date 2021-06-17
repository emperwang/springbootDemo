package com.wk.service;

import com.wk.entity.Users;
import com.wk.mapper.UserMapper;
import com.wk.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2021/6/17 22:09
 * @Description
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUserServiceMockMapper {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testgetAll(){
        Users users = new Users();
        users.setName("mockName1").setAge(100).setId(1);

        Mockito.when(userMapper.selectList(null)).thenReturn(Arrays.asList(users));

        List<Users> all = userService.getAll();
        System.out.println(all);
    }
}
