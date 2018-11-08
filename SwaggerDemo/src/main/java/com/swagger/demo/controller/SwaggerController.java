package com.swagger.demo.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户接口",tags = {"dogapi"}) //接口简要标注,对中文支持不太好
@RequestMapping("swagger")
public class SwaggerController {

    @RequestMapping("test.do")
    public String test(){

        return "test";
    }

    /**
     *  paramType 有多种,还有path  query body, form
     * @return
     */

    @ApiImplicitParams({ //接口需要的参数描述
            @ApiImplicitParam(paramType = "header",name = "Token",value = "token",
                    dataType = "string",required = true,defaultValue = "123")
    })
    //接口功能描述
    @ApiOperation(value = "获取一直狗")
    //接口响应信息,这里定义了一个401,当出现401,接口返回的是自定的错误实例,当然可以定义多个
    @ApiResponses(value = {@ApiResponse(code = 401,message = "请求为通过")})
    @RequestMapping("getdog.do")
    public String getDog(String Token){
        System.out.println(Token);
        return "dog";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = "Token",value = "token",dataType = "String",
            required = true,defaultValue = "123")
    })
    @ApiOperation(value = "创建一直狗")
    @ApiResponses(value = {
            @ApiResponse(code = 401,message = "请求未通过认证")
    })
    @RequestMapping(value = "/dog/{name}.do")
    public String createGog(@ApiParam(defaultValue = "二哈") //ApiParam 和 RequestParam效果相同
                            @PathVariable("name") String name){
        System.out.println("create a dog named:"+name);
        return "create a dog";
    }
}
