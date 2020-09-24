package com.wk.repository;

import com.wk.entity.UserDao;
import org.springframework.data.repository.CrudRepository;

/**
 * 第一个实体对应泛型
 * 第二个泛型 主键类型
 */
public interface UserRepository extends CrudRepository<UserDao,Integer> {
}
