package com.wk.controller;

import com.wk.util.CodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: wk
 * @Date: 2020/10/29 10:04
 * @Description
 */
@Controller
public class VerifCodeController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("/img")
    public void generateImg(HttpServletRequest request, HttpServletResponse response){
        // 设置响应类型,告诉浏览器输出的内容是图片
        response.setContentType("image/jpeg");
        // 设置响应头信息,告诉浏览器不要缓存此内容
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expire",0);
        final CodeUtil codeUtil = new CodeUtil();
        // 输出验证码图片
        codeUtil.getRandCode(request,response);
    }

    @PostMapping("check")
    @ResponseBody
    public String checkCode(String code){
        System.out.println("receive code: " + code);
        return "success";
    }
}
