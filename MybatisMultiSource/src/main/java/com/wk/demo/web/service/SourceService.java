package com.wk.demo.web.service;

import com.wk.demo.entity.AmCollectorSource;
import com.wk.demo.entity.UserBean;

import java.util.List;

public interface SourceService {
    List<UserBean>  getAllUser();

    List<AmCollectorSource> getAll();
}
