package com.wk.IService;

import com.wk.Entity.UserBean;

import java.util.List;

public interface UserBeanService {

    int insertAndDelete(UserBean user);

    List<UserBean> getAllUserBean();
}
