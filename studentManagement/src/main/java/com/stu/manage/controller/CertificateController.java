package com.stu.manage.controller;

import com.stu.manage.entiry.Certificate;
import com.stu.manage.entiry.CommonResult;
import com.stu.manage.entiry.Role;
import com.stu.manage.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping(value = "deleteId/{id}")
    public ResponseEntity deleteCertById(@PathVariable(name = "id") long id){
        int ct = certificateService.deleteCertById(id);
        return ct>0?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping(value = "deleteIds")
    public int deleteCertByMultipleIds(@RequestParam(name = "ids",required = true) List<Long> ids){
        return certificateService.deleteCertByIds(ids);
    }


    @PutMapping(value = "updateById")
    public int updateCert(@RequestBody Certificate u){
        return certificateService.updateCertById(u);
    }


    @PostMapping(value = "save")
    public ResponseEntity saveCert(@RequestBody Certificate u){
        return certificateService.saveCert(u)!=null?ResponseEntity.status(HttpStatus.CREATED).body(u):ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }



    @Autowired
    public void setCertificateService(ICertificateService certificateService) {
        this.certificateService = certificateService;
    }
}
