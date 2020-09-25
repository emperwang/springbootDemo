package com.wk.repository;

import com.wk.common.DBConstances;
import com.wk.entity.OrderDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OrderRepository {

    @Resource(name = DBConstances.JDBC_TEMPLATE_ORDERS)
    private JdbcTemplate template;

    public OrderDao selectById(int id){
        return template.queryForObject("select id,user_id from orders where id=?",
                new BeanPropertyRowMapper<>(OrderDao.class), id);
    }
}
