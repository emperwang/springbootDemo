package com.wk.controller;

import com.wk.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author: wk
 * @Date: 2020/10/29 17:22
 * @Description
 */
@Controller
@Slf4j
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping(value = "getcheckcode")
    @ResponseBody
    public String getCheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999)+100000);
        String message = "您的注册码为:"+checkCode;
        log.info("sending to {}",email);
        mailService.sendSimpleMail(email,"注册验证码", message);
        return checkCode;
    }
}
