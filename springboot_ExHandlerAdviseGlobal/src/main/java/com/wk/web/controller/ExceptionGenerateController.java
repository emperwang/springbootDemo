package com.wk.web.controller;

import com.wk.exception.NfvoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExceptionGenerateController {


    @GetMapping("io.do")
    public String ioEx() throws IOException {
        throw new IOException("file not found.");
    }


    @GetMapping("nfv.do")
    public String nfvExc() throws NfvoException {
        throw new NfvoException(201301);
    }
}
