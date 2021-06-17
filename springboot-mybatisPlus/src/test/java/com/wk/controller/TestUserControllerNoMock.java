package com.wk.controller;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author: Sparks
 * @Date: 2021/6/17 22:35
 * @Description
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestUserControllerNoMock {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void before(){
        Assertions.assertThat(this.mockMvc != null);
        Assertions.assertThat(this.restTemplate != null);
    }

    @Test
    public void testRequest(){
        String url = new StringBuilder("http://localhost:").append(port).append("all.do").toString();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        int statusCodeValue = entity.getStatusCodeValue();
        String body = entity.getBody();
        System.out.println("status code: " + statusCodeValue);
        System.out.println("body : "+ body);
    }

}
