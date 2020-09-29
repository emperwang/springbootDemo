package com.wk.controlelr;

import com.wk.client.NettyClient;
import com.wk.codec.Invocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private NettyClient nettyClient;

    @PostMapping("/mock")
    private String mock(String type, String message){
      log.info("mock type:{}, message:{}", type,message);
        final Invocation invocation = new Invocation(type, message);
        nettyClient.send(invocation);
        return "success";
    }
}
