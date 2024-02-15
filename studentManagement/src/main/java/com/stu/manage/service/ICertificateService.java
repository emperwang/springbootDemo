package com.stu.manage.service;

import com.stu.manage.entity.Certificate;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:35
 * @Description
 */
public interface ICertificateService {

    List<Certificate> listCerts();

    int deleteCertById(long id);

    int deleteCertByIds(List<Long> ids);

    int updateCertById(Certificate r);

    Certificate saveCert(Certificate r);
}
