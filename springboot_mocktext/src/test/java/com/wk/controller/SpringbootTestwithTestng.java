package com.wk.controller;

import com.wk.MockStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author: Sparks
 * @Date: 2021/5/23 9:12
 * @Description
 */
// springboot 和 testng 整合的一个测试
@SpringBootTest(classes = {MockStarter.class})
public class SpringbootTestwithTestng extends AbstractTestNGSpringContextTests {

    @Autowired
    private HelloController helloController;

    private MockMvc mock;

    private RequestBuilder requestBuilder;

    @BeforeClass
    public void init(){
        mock = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void testcase1(){
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getstr1.do")
                .param("name", "123");
        try {
            mock.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("name")
                    .value("123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
