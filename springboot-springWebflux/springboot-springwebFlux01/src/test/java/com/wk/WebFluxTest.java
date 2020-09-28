package com.wk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringWebFluxStarter.class)
@AutoConfigureWebFlux
@AutoConfigureWebTestClient
public class WebFluxTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testList(){
        webClient.get().uri("/users/list")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[{\"id\":1,\"username\":\"zhangsan1\"}," +
                "{\"id\":2,\"username\":\"zhangsan2\"},{\"id\":3,\"username\":\"zhangsan3\"}]");
    }

    @Test
    public void testGet(){
        //  获得指定用户编号的用户
        webClient.get().uri("/users/get?id=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(ir -> {
            final String s = new String(ir.getResponseBody());
            System.out.println(s);
        });
    }

    @Test
    public void testAdd(){
        Map<String, Object> params = new HashMap<>();
        params.put("username", "yudaoyuanma");
        params.put("password","nicai");
        // 添加用户
        webClient.post().uri("/users/add")
                .bodyValue(params)  // 请求参数
                .exchange()  // 指定请求
                .expectStatus().isOk() // 响应码状态
                .expectBody().json("1");    // 响应结果
    }

    @Test
    public void testAdd2(){
        final BodyInserters.FormInserter<String> formData = BodyInserters.fromFormData("username", "yudaoyuanma")
                .with("password", "nicai");
        webClient.post().uri("/users/add2")
                .body(formData)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("1");
    }

    @Test
    public void testUpdate(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        params.put("username","yudaoyuanma");

        webClient.post().uri("/users/update")
                .bodyValue(params)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .consumeWith(res -> {
                    Assert.assertTrue("返回结果需要为true", res.getResponseBody());
                });
    }

    @Test
    public void testDelete(){
        webClient.post().uri("/users/delete?id=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .isEqualTo(false);
    }
}
