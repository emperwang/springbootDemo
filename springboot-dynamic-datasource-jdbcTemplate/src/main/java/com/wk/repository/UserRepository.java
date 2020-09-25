package com.wk.repository;

import com.wk.common.DBConstances;
import com.wk.entity.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepository {

    @Resource(name = DBConstances.JDBC_TEMPLATE_USERS)
    private JdbcTemplate template;

    public UserDao selectById(int id){
        return template.queryForObject("select id,username,password,create_time from users where id=?",
                new BeanPropertyRowMapper<>(UserDao.class), id);
    }
}
