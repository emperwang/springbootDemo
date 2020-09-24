package com.wk;

import com.wk.entity.UserDao;
import com.wk.repository.UserRepository03;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest03 {

    @Autowired
    private UserRepository03 repository03;

    @Test
    public void testFindByUserName(){
        UserDao username = repository03.findByUsername("username 7");
        System.out.println(username);
    }

    @Test
    public void testFindByCreateTimeAfter(){
        // 创建分页操作
        Pageable pageable = PageRequest.of(1,10);
        // 执行分页操作
        Date date = new Date(2018 - 1990, Calendar.FEBRUARY, 24);
        System.out.println(date);
        // 打印
        Page<UserDao> userDaos = repository03.findByCreateTimeAfter(date, pageable);

        System.out.println(userDaos.getTotalPages());
        System.out.println(userDaos.getTotalElements());

        while (userDaos.iterator().hasNext()){
            UserDao next = userDaos.iterator().next();
            System.out.println(next);
        }
    }
}
