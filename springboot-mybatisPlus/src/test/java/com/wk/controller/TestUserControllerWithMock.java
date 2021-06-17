package com.wk.controller;

import com.wk.TestParent;
import com.wk.entity.Users;
import com.wk.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

/**
 * @author: Sparks
 * @Date: 2021/6/17 22:19
 * @Description
 */
public class TestUserControllerWithMock extends TestParent {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void testControllerGetAll() throws Exception {
        Users mockUser = new Users().setId(2).setName("mockControllerName").setAge(20);
        Mockito.when(userService.getAll()).thenReturn(Arrays.asList(mockUser));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all.do");
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("mockControllerName")))
                .andReturn();

    }

}
