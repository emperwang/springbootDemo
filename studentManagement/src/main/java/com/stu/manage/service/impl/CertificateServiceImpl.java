package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.Certificate;
import com.stu.manage.mapper.CertificateMapper;
import com.stu.manage.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:36
 * @Description
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    private CertificateMapper mapper;

    @Override
    public List<Certificate> listCerts() {
        QueryWrapper<Certificate> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("pid");
        return mapper.selectList(wrapper);
    }


    @Autowired
    public void setMapper(CertificateMapper mapper) {
        this.mapper = mapper;
    }
}