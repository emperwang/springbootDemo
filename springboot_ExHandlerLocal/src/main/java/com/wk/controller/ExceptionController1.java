package com.wk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
public class ExceptionController1 {

    /**
     * 此方法是处理本controller出现的异常。不过参数为IOException,表示此方式之处理此种方式的异常
     * 出现其他异常则不进行处理
     * @param ex
     * @return
     */
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity Exhandler1(IOException ex){
        log.info(ex.getLocalizedMessage());
        ex.printStackTrace();
        HashMap<String, String> map = new HashMap<>();
        map.put("code","500");
        map.put("message","IOException");
        return new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("ex1.do")
    public String genExce() throws IOException {
        log.info("into IOException");
        throw new IOException("generate io exception");
    }

    @GetMapping("ex2.do")
    public String genExce2() throws IOException {
        log.info("into NullPointerException");
        throw new NullPointerException("null point exception");
    }
}
