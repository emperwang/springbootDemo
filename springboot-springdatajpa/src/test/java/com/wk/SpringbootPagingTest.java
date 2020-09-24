package com.wk;

import com.wk.entity.UserDao;
import com.wk.repository.UserRepository02;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootPagingTest {

    @Autowired
    private UserRepository02 userRepository02;

    @Test
    public void testFindAll(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Iterable<UserDao> all = userRepository02.findAll(sort);
        all.forEach(System.out::println);
    }

    @Test
    public void testFindPage(){
        // 创建排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        // 创建分页条件
        PageRequest pageRequest = PageRequest.of(1, 10, sort);
        // 执行分页条件
        Page<UserDao> all = userRepository02.findAll(pageRequest);
        // 打印
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
    }
}
