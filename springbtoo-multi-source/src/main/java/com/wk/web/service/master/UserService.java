package com.wk.web.service.master;

import com.wk.entity.master.UserBean;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserBean> getAll();

    List<UserBean> getPages(Integer offset, Integer limits);

    List<UserBean> getPageWithMap(Map<String,Object> maps);
}
