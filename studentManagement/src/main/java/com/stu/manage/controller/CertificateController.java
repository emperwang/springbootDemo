package com.stu.manage.controller;

import com.stu.manage.entiry.Certificate;
import com.stu.manage.entiry.CommonResult;
import com.stu.manage.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:48
 * @Description
 */
@RestController
@RequestMapping("cert")
public class CertificateController {

    private ICertificateService certificateService;


    @GetMapping("list")
    public CommonResult<Certificate> listCerts(){
        CommonResult.CommonResultBuilder<Certificate> builder = new CommonResult.CommonResultBuilder<>();
        List<Certificate> certs = certificateService.listCerts();
        return builder.counts(certs.size()).results(certs).build();
    }




    @Autowired
    public void setCertificateService(ICertificateService certificateService) {
        this.certificateService = certificateService;
    }
}
