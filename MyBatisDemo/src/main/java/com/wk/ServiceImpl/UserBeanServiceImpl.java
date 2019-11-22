package com.wk.ServiceImpl;

import com.wk.Entity.UserBean;
import com.wk.Entity.UserBeanExample;
import com.wk.IService.UserBeanService;
import com.wk.mapper.UserBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserBeanServiceImpl implements UserBeanService {
    @Autowired
    private UserBeanMapper userBeanMapper;

    /**
     *  同一个事务内 先插入再删除
     *  经验证: 同一个事务先插入 后 删除，是可以操作的。
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public int insertAndDelete(UserBean user) {
        int count = 0;
        count += userBeanMapper.insert(user);
        System.out.println(user.toString());
        System.out.println("insert bean primary key is :"+user.getId());
        count += userBeanMapper.deleteByPrimaryKey(user.getId());

        System.out.println("sum operations = "+count);

        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<UserBean> getAllUserBean() {
        List<UserBean> userBeans = userBeanMapper.selectByExample(null);
        return userBeans;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<UserBean> getBeanOrder() {
        List<UserBean> lists = new ArrayList<>();
        UserBeanExample example = new UserBeanExample();
        example.setOrderByClause(" id  desc");
        UserBeanExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIn(Arrays.asList("bj","nj"));
        lists = userBeanMapper.selectByExample(example);
        return lists;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<UserBean> getBeanOrderAsc() {
        List<UserBean> lists = new ArrayList<>();
        UserBeanExample example = new UserBeanExample();
        example.setOrderByClause(" id  ASC");
        UserBeanExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIn(Arrays.asList("bj","nj"));
        lists = userBeanMapper.selectByExample(example);
        return lists;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<UserBean> getDistinct() {
        List<UserBean> lists = new ArrayList<>();
        UserBeanExample example = new UserBeanExample();
        example.setDistinct(true);
        UserBeanExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIn(Arrays.asList("bj","nj"));
        lists = userBeanMapper.selectByExample(example);
        return lists;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<UserBean> selectByIdOrderDesc() {
        List<UserBean> lists = new ArrayList<>();
        lists = userBeanMapper.selectByIdOrderDesc();
        return lists;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<UserBean> selectByIdOrderAsc() {
        List<UserBean> lists = new ArrayList<>();
        lists = userBeanMapper.selectByIdOrderAsc();
        return lists;
    }
}
