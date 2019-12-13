package com.wk.web.service.slave.impl;

import com.wk.entity.slave.AmCollectorSource;
import com.wk.mapper.slave.AmCollectorSourceMapper1;
import com.wk.web.service.slave.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectorServiceImpl implements CollectorService{

    @Autowired
    private AmCollectorSourceMapper1 amCollectorSourceMapper1;

    @Override
    @Transactional(transactionManager = "slaveTransactionManager")
    public List<AmCollectorSource> getAll() {
        return amCollectorSourceMapper1.selectByExample(null);
    }
}
