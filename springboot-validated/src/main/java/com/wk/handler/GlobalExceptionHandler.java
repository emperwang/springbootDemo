package com.wk.handler;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author: Sparks
 * @Date: 2021/7/16 21:39
 * @Description
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({BindException.class})
    public String bindException(Exception e){
        BindingResult bindingResult = ((BindException) e).getBindingResult();
        StringBuilder builder = new StringBuilder();
        bindingResult.getFieldErrors().stream().forEach(ferror -> {
            String field = ferror.getField();
            String defaultMessage = ferror.getDefaultMessage();
            builder.append(field).append(" --> ").append(defaultMessage).append("\n");
        });
        return builder.toString();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public String constraintViolationException(Exception e){
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        constraintViolations.stream().forEach(con -> {
            System.out.println(con.toString());
            String message = con.getMessage();
            Object invalidValue = con.getInvalidValue();
            System.out.println(invalidValue);
            builder.append(message).append("\n");
        });
        return builder.toString();
    }

    @ExceptionHandler({ValidationException.class})
    public String validationException(Exception e){
        String message = ((ValidationException) e).getMessage();
        return message;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String methodArgumentNotValidException(Exception e){
        BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        StringBuilder builder = new StringBuilder();
        bindingResult.getFieldErrors().stream().forEach(ferror -> {
            String field = ferror.getField();
            String defaultMessage = ferror.getDefaultMessage();
            Object rejectedValue = ferror.getRejectedValue();
            builder.append(field).append(" -----> ").append(rejectedValue).append(" --> ").append(defaultMessage).append("\n");
        });
        return builder.toString();
    }

    @ExceptionHandler({Exception.class})
    public String ValidatedException(Exception e){
        return e.getMessage();
    }

}
