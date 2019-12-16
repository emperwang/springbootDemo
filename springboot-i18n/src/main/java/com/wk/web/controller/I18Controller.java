package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@Slf4j
public class I18Controller {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/message")
    @ResponseBody
    public String message(HttpServletRequest request){
        // 获取当前的本地信息
        Locale locale = LocaleContextHolder.getLocale();
        // or
        Locale locale1 = RequestContextUtils.getLocale(request);

        Locale aDefault = Locale.getDefault();
        // 不能使用 Locale.ENGLISH
        Locale us = Locale.US;

        Locale china = Locale.CHINA;
        // 其中第二个参数为占位符 数据
        String enWelcome = messageSource.getMessage("welcome", null, us);
        String usHello = messageSource.getMessage("hello", new String[]{"i18n"}, us);

        String zhWelcome = messageSource.getMessage("welcome", null, china);
        String hello = messageSource.getMessage("hello", new String[]{"i18n-china"}, china);

        log.info("us, welcome:{}, hello:{}", enWelcome, usHello);
        log.info("zh, welcome:{}, hello:{}", zhWelcome, hello);
        String s = new StringBuilder().append(enWelcome).append("   ").append(usHello)
                .append("    china:  ").append(zhWelcome).append("   ").append(hello).toString();

        return s;
    }

}
