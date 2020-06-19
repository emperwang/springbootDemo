package com.wk.actuator.demo.definebyself;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义一个端点
 * @Endpoint是构建rest的唯一路径
 * 有三个不同的请求操作,调用时缺少必需参数或者无法转换为所需的类型
 * 则不会调用操作方法,响应状态为400
 * @ReadOperation = GET 响应状态为200  如果没有返回值响应404(资源未找到)
 * @WriteOperation = POST 响应状态为200 如果没有返回值响应204(无响应内容)
 * @DeleteOperation = DELETE 响应状态为200 如果没有返回值响应204(无响应内容)
 */
//访问地址  http://127.0.0.1:8888/actuatordemo/actuator/mfirst
@Endpoint(id="mfirst")   //注意这里的id需要小写
public class MyEndpointOne {

    @ReadOperation
    public Map<String,String> hello(){
        Map<String,String> result = new HashMap<>();
        result.put("author","wk");
        result.put("age","20");
        result.put("email","www.@163.com");
        return  result;
    }
}
