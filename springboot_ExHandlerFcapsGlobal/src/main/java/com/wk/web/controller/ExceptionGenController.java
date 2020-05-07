package com.wk.web.controller;

import com.wk.exception.NfvoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExceptionGenController {


    @GetMapping("io.do")
    public String io() throws IOException {
        throw new IOException("file not found.");
    }

    @GetMapping("ex1.do")
    public String ex1() throws NfvoException {
        throw new NfvoException(201301,500);
    }

    @GetMapping("ex2.do")
    public String ex2() throws NfvoException {
        throw new NfvoException(201302,400);
    }

    @GetMapping("ex3.do")
    public String ex3() throws NfvoException {
        throw new NfvoException(201303,700);
    }
}
