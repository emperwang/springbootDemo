package com.wk;

import com.wk.entity.UserDao;
import com.wk.repository.Repository05;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot05 {

    @Autowired
    private Repository05 repository05;

    @Test
    public void pageTest(){
        Pageable username = repository05.findByUsername("username");
        System.out.println(username.getPageNumber());
        System.out.println(username.first());
    }
}
