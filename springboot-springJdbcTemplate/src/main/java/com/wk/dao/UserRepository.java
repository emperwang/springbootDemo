package com.wk.dao;

import com.wk.entity.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    /**
     * 声明 insert操作的 PrepareStatementCreatorFactory 对象
     */
    private static final PreparedStatementCreatorFactory createFactory =
            new PreparedStatementCreatorFactory("insert into users(username,password,create_time) values(?,?,?)");

    static {
        // 设置返回主键
        createFactory.setReturnGeneratedKeys(true);
        createFactory.setGeneratedKeysColumnNames("id");
        // 设置每个占位符的类型
        createFactory.addParameter(new SqlParameter(Types.VARCHAR));
        createFactory.addParameter(new SqlParameter(Types.VARCHAR));
        createFactory.addParameter(new SqlParameter(Types.TIMESTAMP));
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *  使用PreparedStatementCreator 实现插入数据
     * @param entity
     * @return
     */
    public int insert(UserDao entity){
        // 创建 KeyHolder 对象, 设置返回的主键ID
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        // 执行插入操作
        int update = jdbcTemplate.update(createFactory.newPreparedStatementCreator(Arrays.asList(entity.getUsername(),
                entity.getPassword(), entity.getCreateTime())),keyHolder);
        // 设置ID 主键到 entity中
        if (keyHolder.getKey() != null){
            entity.setId(keyHolder.getKey().intValue());
        }
        // 返回影响行数
        return update;
    }

    /**
     * 使用SimpleJdbcInsert 实现插入数据
     * @return
     */
    public int Insert0(UserDao entity){
        // 创建 SimpleJdbcInsert 对象
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.setTableName("users");
        simpleJdbcInsert.setColumnNames(Arrays.asList("username","password","create_time"));
        simpleJdbcInsert.setGeneratedKeyName("id");

        // 拼接参数
        Map<String,Object> map = new HashMap<>();
        map.put("username", entity.getUsername());
        map.put("password", entity.getPassword());
        map.put("create_time", entity.getCreateTime());
        // 执行插入操作
        Number key = simpleJdbcInsert.executeAndReturnKey(map);
        // 设置ID 主键到 entity实体中
        entity.setId(key.intValue());
        // 返回影响行数
        return 1;
    }

    public int updateById(UserDao entity){
        // jdbcTemplate 生成更新的动态sql不是很方便,需要二次封装
        return jdbcTemplate.update("update users set password=? where id=?", entity.getPassword(),
                entity.getId());
    }

    public int deleteById(int id){
        return jdbcTemplate.update("delete from users where id=?", id);
    }

    public UserDao selectById(int id){
        return jdbcTemplate.queryForObject("select id, username,password,create_time from users where id=?",
                new BeanPropertyRowMapper<>(UserDao.class), id);
    }

    public UserDao selectByUserName(String name){
        return jdbcTemplate.queryForObject("select id,username,password,create_time from users where username=?",
                new BeanPropertyRowMapper<>(UserDao.class),name);
    }

    public List<UserDao> selectByIds(List<Integer> ids){
        // 创阿金NamedParameterJdbcTemplate对象
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        // 拼接参数
        Map<String,Object> map = new HashMap<>();
        map.put("ids", ids);
        // 执行查询
        // 使用 :ids 作为占位符,
        return template.query("select id,username,password,create_time from users where id in(:ids)",
                map, new BeanPropertyRowMapper<>(UserDao.class));// 结果转换为对象
    }

}





















