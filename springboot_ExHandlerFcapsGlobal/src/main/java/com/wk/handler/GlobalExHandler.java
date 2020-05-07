package com.wk.handler;

import com.wk.exception.NfvoException;
import com.wk.util.ExUtil;
import com.wk.util.JSONUtil;
import com.wk.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ControllerAdvice
@ConditionalOnWebApplication
// @ConditionalOnMissingBean(GlobalExHandler.class)
public class GlobalExHandler {

    /**
     *  处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = NfvoException.class)
    public ResponseEntity handleNfvException(NfvoException e){
        log.error(ExUtil.buildErrorMessage(e));
        int statusCode = e.getStatusCode() == null ? 500 : e.getStatusCode();
        HttpStatus status = null;
        status = HttpStatus.resolve(statusCode);
        if (status == null){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        Map<String,String> res = new HashMap<>();
        res.put("code", e.getErrorCode()==null?"DefaultCode":e.getErrorCode().toString());
        res.put("message", e.getMessage());
        return ResponseUtil.getResponse(status, JSONUtil.beanToJson(res));
    }

    /**
     * 处理controller 上一层异常
     * @param e
     * @return
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public String hadleServletException(Exception e){
        log.error(ExUtil.buildErrorMessage(e));
        // 对应异常和code的映射处理 key=e.getClass().getSimpleName()
        return e.getMessage();
    }

    /**
     *  参数绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public String handleBindException(BindException e){
        log.error("参数绑定异常: ", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常, 将校验失败的所有异常组合成一条错误信息
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public String handleValidException(MethodArgumentNotValidException e){
        log.error(ExUtil.buildErrorMessage(e));

        return wrapperBindingResult(e.getBindingResult());
    }

    private String wrapperBindingResult(BindingResult bindingResult){
        StringBuilder builder = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error instanceof FieldError){
                builder.append(((FieldError)error).getField()).append(":");
            }
            builder.append(StringUtils.isEmpty(error.getDefaultMessage())?"":error.getDefaultMessage());
            builder.append(", ");
        }
        return builder.toString();
    }

    /**
     * 未定义异常
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public String handleException(Exception e){
        log.error("process other exception.{}",e.getClass().getSimpleName());
        log.error(ExUtil.buildErrorMessage(e));

        return StringUtils.isEmpty(e.getMessage())?"system error":e.getMessage();
    }
}
