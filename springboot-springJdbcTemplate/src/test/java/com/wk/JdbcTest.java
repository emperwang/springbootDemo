package com.wk;

import com.wk.dao.UserRepository;
import com.wk.entity.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JDBCTemplateStarter.class)
public class JdbcTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testInsert(){
        UserDao userDao = new UserDao();
        userDao.setUsername("sansan");
        userDao.setPassword("nicai");
        userDao.setCreateTime(new Date());
        repository.insert(userDao);
        System.out.println(userDao);
    }

    @Test
    public void testInsert0(){
        UserDao userDao = new UserDao();
        userDao.setUsername(UUID.randomUUID().toString());
        userDao.setPassword("nicao");
        userDao.setCreateTime(new Date());
        repository.Insert0(userDao);
        System.out.println(userDao);
    }
    @Test
    public void testUpdateById(){
        UserDao userDao = new UserDao();
        userDao.setId(5);
        userDao.setPassword("wobucai");
        userDao.setCreateTime(new Date());
        repository.updateById(userDao);
    }

    @Test
    public void testDeleteById(){
        repository.deleteById(5);
    }

    @Test
    public void testSelectById(){
        UserDao userDao = repository.selectById(5);
        System.out.println(userDao);
    }

    @Test
    public void testSelectByUsername(){
        UserDao sansan = repository.selectByUserName("zhangsan");
        System.out.println(sansan);
    }

    @Test
    public void testSelectByIds(){
        List<UserDao> userDaos = repository.selectByIds(Arrays.asList(4, 5, 6, 7, 8, 9, 10));
        System.out.println(userDaos);
    }
}
