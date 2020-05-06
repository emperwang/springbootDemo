package com.wk.config;

import com.alibaba.fastjson.JSON;
import com.wk.exception.NfvoException;
import com.wk.util.ExUtil;
import com.wk.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CusExceptionHandler implements HandlerExceptionResolver {
    /**
     * @param httpServletRequest    current HTTP request
     * @param httpServletResponse   current HTTP response
     * @param handler               the execute handler
     * @param e                     the exception
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler, Exception e) {
        Map<String, String> res = new HashMap<>();
        ModelAndView model = new ModelAndView();
        if (e instanceof NfvoException){
            res.put("code", ((NfvoException) e).getErrorCode().toString());
            res.put("message", e.getMessage());

        }else{
            res.put("code", "500");
            res.put("message", e.getMessage());
        }
        httpServletResponse.setStatus(500);
        log.info(JSONUtil.beanToJson(res));
        try {
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write(JSONUtil.beanToJson(res));
            writer.flush();
        } catch (IOException e1) {
            log.error(ExUtil.buildErrorMessage(e1));
        }finally {
            model.clear();
            return model;
        }
        // return null;
    }
}
