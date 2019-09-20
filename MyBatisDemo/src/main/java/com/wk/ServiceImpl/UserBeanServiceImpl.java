package com.wk.ServiceImpl;

import com.wk.Entity.UserBean;
import com.wk.IService.UserBeanService;
import com.wk.mapper.UserBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
