package com.wk.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wk
 * @Date: 2020/10/27 16:59
 * @Description
 */
@RestController
public class DemoController {

    @GetMapping("/echo")
    public String echo(){
        return "echo";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    // 模拟访问延迟
    @GetMapping("sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(2000);
        return "sleep";
    }
    // 热点数据模拟
    @GetMapping("/pinfo")
    @SentinelResource("demo_product_info_hot")
    public String productInfo(String id){
        return "商品编号:"+id;
    }
}
