package com.wk.web.service.slave;

import com.wk.entity.slave.AmCollectorSource;

import java.util.List;
import java.util.Map;

public interface CollectorService {

    List<AmCollectorSource> getAll();

    List<AmCollectorSource> getPages(Integer offset, Integer limits);

    List<AmCollectorSource> getPageWithMap(Map<String,Object> maps);
}
