package com.wk.web.controller;

import com.wk.exception.NfvoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class GenerateExceptionController {


    @GetMapping("io.do")
    public String IoEx() throws IOException {
        throw new IOException("Can not find file");
    }

    @GetMapping("nfv.do")
    public String NfvEx() throws NfvoException {
        throw new NfvoException(201300);
    }
}
