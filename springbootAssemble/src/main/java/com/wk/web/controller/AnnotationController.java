package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
public class AnnotationController {


    @GetMapping(value = "insetRequest.do")
    @ResponseBody
    public String testRequest(WebRequest request, NativeWebRequest request2,
                              HttpSession session){
        log.info("WebRueqst :{},contextPath :{}",request.hashCode(),request.getContextPath());
        log.info("NativeWebRequest:{},nativeRequest:{},nvtiveResponse",
                request2.hashCode(),request2.getNativeRequest(),request2.getNativeResponse());

        log.info("session:{},id:{}",session.hashCode(),session.getId());
        return "success";
    }
    @GetMapping(value = "injectHttp.do")
    @ResponseBody
    public String testInject(ServletRequest request, ServletResponse response,
                             HttpMethod method){ //HttpStatus status, HttpHeaders headers

        log.info("request:{},contentTYpe:{},protocol:{}",request.hashCode(),request.getContentType(),request.getProtocol());
        log.info("response:{},encoding:{},buffersize:{}",response.hashCode(),response.getCharacterEncoding(),response.getBufferSize());
        /*log.info("headers:{}",headers.hashCode());
        Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            log.info("key :{},value:{}",key,value.toString());
        }*/
        //log.info("httpStatus:{},statusValue:{}",status.hashCode(),status.value());
        log.info("httpMethod:{},methodName:{}",method.hashCode(),method.name());
        return "success";
    }
    // GET /pets/42;q=11;r=22
    @RequestMapping("/pets/{pid}")
    @ResponseBody
    public String testGetValue(@RequestHeader Map<String,String> header,
                               @PathVariable(name = "pid") String pid,
                               @MatrixVariable(name = "q") Integer q){ //, @MatrixVariable int q
        log.info("pid:{},q={}",pid,q);
        log.info("header:{}",header.hashCode());
        for (Map.Entry<String, String> entry : header.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            log.info("{}:{}",key,value);
        }
        return "success";
    }
    @GetMapping("/user/{id}")
    @ResponseBody
    public String pathVarTest(@PathVariable(name = "id") Integer id){
        log.info("get user id:{}",id);
        return "success";
    }

    // GET /owner/42;q=11/pet/21;q=22
    @GetMapping("/owners/{ownerid}/pet/{petid}")
    @ResponseBody
    public String testGetPathVariable(@MatrixVariable(name = "q",pathVar = "ownerid") int q1,
                                      @MatrixVariable(name = "q",pathVar = "petid") int q2){
        log.info("q1 = {},q2={}",q1,q2);
        // q1=11, q2=22
        return "success";
    }

}
