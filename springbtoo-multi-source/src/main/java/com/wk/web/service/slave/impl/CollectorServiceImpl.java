package com.wk.web.service.slave.impl;

import com.wk.entity.slave.AmCollectorSource;
import com.wk.mapper.slave.AmCollectorSourceMapper1;
import com.wk.web.service.slave.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectorServiceImpl implements CollectorService{

    @Autowired
    private AmCollectorSourceMapper1 amCollectorSourceMapper1;

    @Override
    @Transactional(transactionManager = "slaveTransactionManager")
    public List<AmCollectorSource> getAll() {
        return amCollectorSourceMapper1.selectByExample(null);
    }

    @Override
    public List<AmCollectorSource> getPages(Integer offset, Integer limits) {
        return amCollectorSourceMapper1.selectPages(offset,limits);
    }

    @Override
    public List<AmCollectorSource> getPageWithMap(Map<String, Object> maps) {
        return amCollectorSourceMapper1.selectPagesWithMap(maps);
    }
}
