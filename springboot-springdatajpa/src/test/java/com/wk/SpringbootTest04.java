package com.wk;

import com.wk.entity.UserDao;
import com.wk.repository.Repository04;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest04 {
    @Autowired
    private Repository04 repository04;

    @Test
    public void testFindByName01(){
        UserDao byUsername01 = repository04.findByUsername01("username 7");
        System.out.println(byUsername01);
    }

    @Test
    public void testFindByUsername02(){
        UserDao byUsername02 = repository04.findByUsername02("username 8");
        System.out.println(byUsername02);
    }

    @Test
    public void testFindByUsername03(){
        UserDao byUsername03 = repository04.findByUsername03("username 9");
        System.out.println(byUsername03);
    }

    /**
     * 在单元测试时,事务默认回滚,所以如何更新都不会成功
     */
    @Test
    @Transactional
    public void testUpdateUser(){
        repository04.updateUsernameById(7,"zhangsan");
    }
}
