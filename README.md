# springcloudalibaba学习-网关项目
- 为什么引入网关

  前端请求->网关->各个微服务

  多了一层，可以做统一管理，权限校验、负载均衡等

- springcloudgateway介绍

  - 底层是Netty、Reactor、WebFlux
  - 优点
    - 性能是Zuul 1.x的1.6倍，[性能对比]()(https://www.imooc.com/article/285068)
    - 功能强大
    - 容易扩展
  - 缺点
    - 不是servlet编程模型，而是Netty+Webflux，有学习成本
    - 不能在servlet容器下工作，不能打成war包，不能部署到tomcat
    - 不支持SpringBoot1.x

- 网关项目创建

  1. 新建一个项目导入选择gateway
  2. 统一springboot、springcloud版本，整合整合springcloudalibaba和nacos
  3. 配置yml

- springcloudgateway转发规则

  访问 ${GATEWAY_URL}/{微服务X}/**

  会转发到微服务X的/**路径

  例子：localhost:8040/user-center/users/1

  会转发到user-center用户的/users/1接口
  
 - 概念
    - Route路由
        一条转发规则
    - 谓词
        控制请求是不是走转发规则的条件
    - 过滤器
        为路由添加业务逻辑，修改请求及相应
        
 - 源码
    - Handler Mapping: org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping
    - Web Handler: org.springframework.web.server.handler.FilteringWebHandler
 
 - [路由谓词](https://www.imooc.com/article/290804)
    - 时间相关
    - Cookie相关
    - Header相关
    - 请求相关
    
 - 自定义路由谓词工厂
    - 添加配置
    - 添加解析配置类，继承了抽象类，实现方法
