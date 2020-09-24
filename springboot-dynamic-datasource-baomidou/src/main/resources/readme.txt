测试springboot利用dynamic-datasource-spring-boot-starter多数据源的实现
以及不同场景下的测试覆盖
1. 未添加事务的多数据源测试 -- 正常
2. 添加事务的多数据源测试 -- 报错
3.
    3.1 同一个线程内的不同事务的查询,因为没有选择数据源错误,报错
        public void method03(){
           self().method031();
           self().method032();
       }
      此相当于没有事务, 那么正常执行
       public void method033(){
           this.method031();
           this.method032();
       }

其他场景看一下  OrderServer 中的测试