package com.wk.controller;

import com.wk.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserControllerWithServiceTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        // mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @MockBean
    private HelloService helloService;

    @Test
    public void testUserHello() throws Exception {
        Mockito.when(helloService.getHelloResult()).thenReturn("hello, mock");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/gethello.do")
                          // 丰富的api, 可以配置header 参数  session cookie accept-header 等信息
                          /*.contentType()
                            .cookie()
                            .params()*/
                            )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hello")));
    }
}
