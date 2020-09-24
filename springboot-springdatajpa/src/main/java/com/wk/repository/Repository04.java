package com.wk.repository;

import com.wk.entity.UserDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface Repository04 extends PagingAndSortingRepository<UserDao,Integer> {
    // 使用Query自定义了一个SQL操作,并且参数使用占位符(?) + 参数位置的形式
    @Query("select u from UserDao u where u.username = ?1")
    UserDao findByUsername01(String username);
    // 和上面类似, 差异在于使用占位符(:) + 参数名字 (需要使用@Param声明) 的形式
    @Query("select u from UserDao u where u.username = :username")
    UserDao findByUsername02(@Param("username") String username);
    // 和2 类似,差别在于增加了nativeQuery=true,表示在@Query自定义的是原生SQL,而非在1和2自定义的JPQL.
    // 1和2处, from UserDao 使用的是 实体名
    // 此处使用的是 表名
    @Query(value = "select * from users u where u.username=:username",nativeQuery = true)
    UserDao findByUsername03(@Param("username") String username);
    // 定义了更新操作,需要加上@Modifier注解, 另外 发现,使用参数名字时,可以不用配合@Param注解
    @Query("update UserDao u set u.username = :username where u.id = :id")
    @Modifying
    int updateUsernameById(Integer id, String username);
}
