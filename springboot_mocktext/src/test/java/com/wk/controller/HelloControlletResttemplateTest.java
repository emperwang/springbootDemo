package com.wk.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HelloControlletResttemplateTest {
    // 获取controll的端口
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testNull(){
        Assertions.assertThat(port).isEqualTo(8888);
        Assertions.assertThat(restTemplate).isNotNull();
    }

    @Test
    public void testHelloController(){
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:"+port+"/getstr.do",String.class)
                .getStatusCode().value()).isEqualTo(200);
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:"+port+"/getstr.do",String.class)
                .getBody().toString()).contains("hello");
    }
}
