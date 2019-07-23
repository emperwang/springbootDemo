把配置文件中的数据库密码进行加密，加强项目的安全性。

加密方案有两种：

1， 使用加密工具把密码进行加密，把密文放在配置文件中，然后再具体使用的时候，在把密码进行解密

2，使用druid内置的加密方式进行加密。

第二种方式有些麻烦，看一下步骤吧。

1. 找到druid连接池的jar包，然后在此目录下打开命令行运行命令：

   ```shell
   java -cp druid-1.0.10.jar com.alibaba.druid.filter.config.ConfigTools your_password
   ```

   ```shell
   $ java -cp druid-1.1.10.jar com.alibaba.druid.filter.config.ConfigTools admin
   privateKey:MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAinU+uKNXr+cBFSmg5KeTTG/U6twmZXWyeaYcKYFfJsp8MUzpC2UWVevdJ9OqD7S5wj6evm+vg7dsICcTxk8k8QIDAQABAkBdfWVojwK6h6S3gXBedLiFOE3wd86FW7+zXHJPpEd3jOrhtZ4b2DF6oux4TuFxpTZjtucKU1DEnkLhxAhazO/xAiEAy4A8p2MIav0feL9+VFVEho17sCYwzSe5hIJb0jGwbg8CIQCuLWSUlWuEhj5RCyy2tK8kIu+feKVI6sW/ExkQe9o8/wIgNG7oMeDMbHSnxT7h1+zYaSrPMcXFUTbpEAFekzwGT3UCIQCcU1lmkgUB2Iqtql3jTZ+zyzdN5bZTD4OTzsw8IyzoeQIgVxoTE3Q5RG9U6sYRwrTJeLLQBEIJjwMJbDiCpoiHNaM=
   publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIp1PrijV6/nARUpoOSnk0xv1OrcJmV1snmmHCmBXybKfDFM6QtlFlXr3SfTqg+0ucI+nr5vr4O3bCAnE8ZPJPECAwEAAQ==
   password:NxEOf5eWuCFYMEjMwbwcH8QayunurgWsPVdKqPPFuvVg//hjPp/lJOLcWSoaWtMLvpxkfy5gOAJKvflCxUhGLw==
   ```

   

2. 把生成的password放到spring.datasource.password，把publicKey放到spring.datasource.druid.config.key

在配置文件中配置;

```yml
public-key: 【your public key】
spring:
  application:
    name: flow-effects-system
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      dbType: 【your db type】
      driverClassName: 【your db driver】
      url: 【your db url】
      username: 【your db user name】
      password: 【your encrypted Password】
      #配置初始化大小
      initialSize: 5
      #最小空闲连接
      minIdle: 5
      #最大连接数量
      maxActive: 20
      #配置获取连接等待超时的时间
      maxWait: 60000
      #配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      timeBetweenEvictionRunsMillis: 2000
      #配置一个连接在池中最小、最大生存的时间，单位是毫秒
      minEvictableIdleTimeMillis: 600000
      maxEvictableIdleTimeMillis: 900000
      #是否自动回收超时连接
      removeAbandoned: true
      #指定连接应该被废弃的时间 秒
      removeAbandonedTimeout: 1800
      #测试sql
      validationQuery: SELECT 1 FROM DUAL
      #建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
      testWhileIdle: true
      #申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
      testOnBorrow: false
      #归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
      testOnReturn: false
      #通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connectionProperties: config.decrypt=true;config.decrypt.key=${public-key};druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
      #是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
      poolPreparedStatements: true
      #要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
      maxPoolPreparedStatementPerConnectionSize: 50
      #属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat 日志用的filter:log4j 防御sql注入的filter:wall
  filters: stat,wall,log4j,config
  web-stat-filter:
    enabled: true
    exclusions: '*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*'
  stat-view-servlet:
    enabled: true
    reset-enable: false
    url-pattern: /druid/*
    login-username: admin
    login-password: admin
  filter:
    slf4j:
      enabled: true
      statement-create-after-log-enabled: false
      statement-close-after-log-enabled: false
      result-set-open-after-log-enabled: false
      result-set-close-after-log-enabled: false

```

javaBean配置看本工程中的配置.

加密方案有两种：

，直接使用加密工具把密码加密后，把密文放在配置文件中，当使用密码时，在把密文进行解密。

2.

