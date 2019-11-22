package com.wk.IService;

import com.wk.Entity.UserBean;

import java.util.List;

public interface UserBeanService {

    int insertAndDelete(UserBean user);

    List<UserBean> getAllUserBean();

    List<UserBean> getBeanOrderAsc();

    List<UserBean> getBeanOrder();

    List<UserBean> getDistinct();

    List<UserBean> selectByIdOrderDesc();

    List<UserBean> selectByIdOrderAsc();
}
