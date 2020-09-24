package com.wk.repository;

import com.wk.entity.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface UserRepository03 extends PagingAndSortingRepository<UserDao,Integer> {
    UserDao findByUsername(String username);
    Page<UserDao> findByCreateTimeAfter(Date createTime, Pageable pageable);
}
