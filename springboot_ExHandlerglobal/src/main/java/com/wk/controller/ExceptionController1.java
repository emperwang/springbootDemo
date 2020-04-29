package com.wk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ExceptionController1 {

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
