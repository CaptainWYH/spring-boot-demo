### application.yml 需要添加一下配置

~~~yml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: delete_flag
      logic-delete-value: 1
      logic-not-delete-value: 0
    banner: false
  mapper-locations: classpath:mapper/*.xml


spring:
  datasource:
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://***:3306/***?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      initial-size: 20
      min-idle: 20
      max-active: 100
      max-wait: 60000
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        login-username: admin
        login-password: 123456
      filter:
        stat:
          enabled: true
          log-slow-sql: true
          slow-sql-millis: 2000
        wall:
          enabled: true
~~~
