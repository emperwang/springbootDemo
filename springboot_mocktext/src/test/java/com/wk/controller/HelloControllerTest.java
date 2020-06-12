package com.wk.controller;


import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class HelloControllerTest extends BaseTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNull(){
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    public void testGetStr() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getstr.do"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hello")));
    }
}
