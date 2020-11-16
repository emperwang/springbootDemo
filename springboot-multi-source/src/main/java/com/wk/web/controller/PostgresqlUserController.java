package com.wk.web.controller;

import com.wk.web.service.slave.PostgresqlUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostgresqlUserController {
    @Autowired
    private PostgresqlUserService userService;

    @GetMapping("seq")
    public String testSeq(){
        userService.seqs();
        return "success";
    }
}
