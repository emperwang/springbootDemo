package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.Certificate;
import com.stu.manage.mapper.CertificateMapper;
import com.stu.manage.service.ICertificateService;
import com.stu.manage.utils.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:36
 * @Description
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    private CertificateMapper mapper;
    private DateTimeFormatter formatter = TimeFormatUtil.getFormat(TimeFormatUtil.format1);
    private ImageService imageService;


    @Override
    public List<Certificate> listCerts() {
        QueryWrapper<Certificate> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("pid");
        return mapper.selectList(wrapper);
    }

    @Override
    public int deleteCertById(long id) {
        Certificate certificate = mapper.selectById(id);
        String certificatePath = certificate.getCertificatePath();
        if (!StringUtils.isEmpty(certificatePath)){
            Arrays.stream(certificatePath.split(",")).forEach(it -> {
                imageService.deleteImage(it);
            });
        }
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

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}