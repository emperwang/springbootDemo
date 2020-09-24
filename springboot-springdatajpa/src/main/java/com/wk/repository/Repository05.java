package com.wk.repository;

import com.wk.entity.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Repository05 extends JpaRepository<UserDao, Integer> {

    @Query(value = "select * from users where username = ?1",
    countQuery = "select count(*) from users where username = ?1",
    nativeQuery = true)
    Pageable findByUsername(String username);
}
