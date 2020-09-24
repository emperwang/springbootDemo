package com.wk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.common.DBConstants;
import com.wk.entity.Order;
import com.wk.entity.User;
import com.wk.mapper.OrderMapper;
import com.wk.mapper.UserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取自己的代理对象
     */
    private OrderService self(){
        return (OrderService) AopContext.currentProxy();
    }

    /**
     * 1.方法未使用 @Transaction 注解，不会开始事务
     * 2. 对于orderMapper和userMapper的查询操作,分别使用其接口上的@DS注解,找到对应的
     *  数据源,执行操作
     * 3. 在未开启事务的情况下,能够自由的使用多数据源
     */
    public void method01(){
        // 查询订单
        Order order = orderMapper.selectById(1);
        System.out.println(order.toString());
        // 查询用户
        User user = userMapper.selectById(1);
        System.out.println(user.toString());
    }

    /**
     * 开启事务,执行时 抛出异常
     * org.springframework.jdbc.BadSqlGrammarException:
     * ### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException:
     * Table 't_user.orders' doesn't exist
     *
     */
    @Transactional
    public void method02(){
        // 查询订单
        Order order = orderMapper.selectById(1);
        System.out.println(order.toString());
        // 查询用户
        User user = userMapper.selectById(1);
        System.out.println(user.toString());
    }

    /**
     * org.springframework.jdbc.BadSqlGrammarException:
     * ### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException:
     * Table 't_user.orders' doesn't exist
     */
    public void method03(){
        // 031 函数报错
        self().method031();
        self().method032();
    }

    /**
     * 此函数就不会报错,主要是因为this是当前对象,不是代理对象,在method031 和 method032 上的 @Transaction注解没有生效
     * 即事务没有生效
     */
    public void method033(){
        this.method031();
        this.method032();
    }

    @Transactional
    public void method031(){
        Order order = orderMapper.selectById(1);
        System.out.println("method 031: " + order.toString());
    }
    @Transactional
    public void method032(){
        User user = userMapper.selectById(1);
        System.out.println("method 032 " + user.toString());
    }

    /**
     * 此方法执行,正常结束没有报错
     * 在执行method041()前,因为有@Transaction注释,所以spring事务机制触发.DynamicRoutingDataSource根据@DS注解,
     * 获取对应的orders的Datasource,从而获得connection.所以后续的orderMapper执行查询操作时,即使使用的是线程绑定
     * 的connection,也不可能出错. 实际上,此时的OrderMapper上的@DS注解,没有起作用
     * 在执行method42()时,同理. 但是上面不是提到connection会绑定到当前线程吗? 那么在method042中,应该使用的是method041
     * 的orders对应的connection啊. 在spring事务中,在一个事务执行完成后,会将事务信息从当前线程解绑,所以在执行method042
     * 方法前,又可以执行一轮事务的逻辑
     *
     * -- 总的来说,对于声明了@Transactional 的service方法上,也可以通过@DS声明对应的数据源
     */
    public void method04(){
        self().method041();
        self().method042();
    }
    @Transactional
    @DS(DBConstants.DATASOURCE_ORDERS)
    public void method041(){
        Order order = orderMapper.selectById(1);
        System.out.println("method 031: " + order.toString());
    }
    @Transactional
    @DS(DBConstants.DATASOURCE_USERS)
    public void method042(){
        User user = userMapper.selectById(1);
        System.out.println("method 032 " + user.toString());
    }

    /**
     * 方法执行,正常结束,没有报错
     * 在执行方法method05时,和method041类似,获取到orders的datasource,并获取connection绑定到当前线程
     * 在调用方法method051时,其事务扩散规则是Propagation.REQUIRES_NEW,就会挂起 method05的事务,重新创建
     * 一个新的事务, 当 method051执行完成后, 又恢复method05的事务,并继续执行.
     */
    @Transactional
    @DS(DBConstants.DATASOURCE_ORDERS)
    public void method05(){
        Order order = orderMapper.selectById(1);
        System.out.println("05: " + order.toString());
        self().method051();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DS(DBConstants.DATASOURCE_USERS)
    public void method051(){
        User user = userMapper.selectById(1);
        System.out.println("051 :  " + user.toString());
    }

}
