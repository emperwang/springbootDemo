package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.Certificate;
import com.stu.manage.mapper.CertificateMapper;
import com.stu.manage.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:36
 * @Description
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    private CertificateMapper mapper;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss");
    @Override
    public List<Certificate> listCerts() {
        QueryWrapper<Certificate> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("pid");
        return mapper.selectList(wrapper);
    }

    @Override
    public int deleteCertById(long id) {
        return mapper.deleteById(id);
    }

    @Override
    public int deleteCertByIds(List<Long> ids) {
        return mapper.deleteBatchIds(ids);
    }

    @Override
    public int updateCertById(Certificate r) {
        String format = formatter.format(LocalDateTime.now());
        r.setUpdateTime(format);
        return mapper.updateById(r);
    }

    @Override
    public Certificate saveCert(Certificate r) {
        String format = formatter.format(LocalDateTime.now());
        r.setCreateTime(format);
        r.setUpdateTime(format);
        return mapper.insert(r)>0?r:null;
    }


    @Autowired
    public void setMapper(CertificateMapper mapper) {
        this.mapper = mapper;
    }
}