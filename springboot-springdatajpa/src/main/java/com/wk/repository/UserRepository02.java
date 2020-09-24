package com.wk.repository;

import com.wk.entity.UserDao;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository02 extends PagingAndSortingRepository<UserDao,Integer> {
}
