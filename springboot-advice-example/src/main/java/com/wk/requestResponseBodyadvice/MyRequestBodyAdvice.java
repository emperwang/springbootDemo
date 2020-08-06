package com.wk.requestResponseBodyadvice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 *  相当于对  RequestBody的AOP 编程
 */
@ControllerAdvice
public class MyRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println("supports  parameter name: "+methodParameter.getParameterName()+"---"
                + "method name:"+methodParameter.getMethod().getName());
        System.out.println("supports  type= " + type.getTypeName());
        System.out.println("supports  aclass = " + aClass.getName());
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,
                                           Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        System.out.println(" beforeBodyRead  httpInputMessage = " + httpInputMessage.toString());
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,
                                Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println("afterBodyRead  o = " + o.toString());
        System.out.println("afterBodyRead  httpInputMessage = " + httpInputMessage.toString());
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,
                                  Class<? extends HttpMessageConverter<?>> aClass) {

        Method method=methodParameter.getMethod();
        System.out.println("handleEmptyBody method = " + method.getDeclaringClass().getSimpleName());
        System.out.println("handleEmptyBody o = " + o.toString());
        return o;
    }
}
