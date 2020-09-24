package com.wk;

import com.wk.entity.UserDao;
import com.wk.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
//@org.springframework.boot.test.context.SpringBootTest(classes = JPAstarter.class)
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert(){
        UserDao userDao = new UserDao();
        userDao.setUsername(UUID.randomUUID().toString());
        userDao.setPassword("nicai");
        userDao.setCreateTime(new Date());
        userRepository.save(userDao);
    }

    @Test
    public void testUpdateById(){
        UserDao userDao = new UserDao();
        userDao.setId(7);
        userDao.setPassword("wobushicai");
        userRepository.save(userDao);
    }

    @Test
    public void testInsertall(){
        List<UserDao> list = new ArrayList<>();
        Arrays.asList(7,8,9,10,11).forEach(t -> {
            UserDao userDao = new UserDao();
            userDao.setId(t);
            userDao.setPassword("admin " + t);
            userDao.setUsername("username " + t);
            userDao.setCreateTime(new Date());
            list.add(userDao);
        });
        System.out.println(list.toString());
        userRepository.saveAll(list);
    }

    @Test
    public void testDeleteById(){
        userRepository.deleteById(4);
    }
    @Test
    public void testSelectById(){
        Optional<UserDao> byId = userRepository.findById(5);
        if (byId.isPresent()){
            System.out.println(byId);
        }
    }
    @Test
    public void testGetAll(){
        Iterable<UserDao> all = userRepository.findAll();
        all.forEach(it -> {
            System.out.println(it);
        });
    }
}
