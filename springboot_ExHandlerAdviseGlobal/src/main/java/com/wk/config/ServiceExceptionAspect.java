package com.wk.config;

import com.wk.exception.NfvoException;
import com.wk.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// service异常
@Aspect
@Component
@Slf4j
public class ServiceExceptionAspect {

    /*@Pointcut("execution(* com.wk.web.service.*.*(*))")
    private void servicePointcut(){}*/


    /*@AfterThrowing(pointcut = "servicePointcut()", throwing = "e")
    public void handlerServiceThrowing(Exception e){
        //String errMsg = StringUtils.isEmpty(e.getMessage())?"system error":e.getMessage();
        Map<String,String> res = new HashMap<>();
        if (e instanceof NfvoException){
            res.put("code",((NfvoException)e).getErrorCode().toString());
            res.put("message", ((NfvoException)e).getMessage());
        }else{
            res.put("code","500");
            res.put("message", "System error.");
        }
        String errMsg = JSONUtil.beanToJson(res);
        writeContent(errMsg);
    }

    // 将内容输出到浏览器
    private void writeContent(String content){
        HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        //response.setHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.setHeader("icop-content-type","exception");
        response.setStatus(500);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
