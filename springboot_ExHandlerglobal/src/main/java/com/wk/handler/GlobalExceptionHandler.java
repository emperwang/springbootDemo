package com.wk.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handler(Throwable ex){
        log.info(ex.getLocalizedMessage());
        log.error(ex.getMessage());
        HashMap<String, String> map = new HashMap<>();
        map.put("code","500");
        map.put("message","Exception");
        return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
