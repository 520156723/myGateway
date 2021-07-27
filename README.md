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
    
 - [内置路由工厂](https://www.imooc.com/article/290816)
 
 - 自定义过滤工厂
    - 生命周期
        - pre: Gateway转发请求之前
        - post: Gateway转发请求之后
    - 方式
        - 1继承AbstractGatewayFilterFactory
            - 参考RequestSizeGatewayFilterFactory编写
            - 配置格式
                ```
              filters:
                   - name: RequestSize
                     args:
                        maxSize: 50000
                ```
        - 2继承AbstractNameValueGatewayFilterFactory
            - 参考AddRequestHeaderGatewayFilterFactory编写
            - 配置格式
                ```
                filters:
                     - AddRequestHeader=X-Request-Foo, Bar
                ```
    - 核心API
        - 修改request
            exchange.getRequest().mutate().xxx
            其中的exchange指的是继承了ServerWebExchange的类
        - 修改exchange
            exchange.mutate().xxx
        - 传递给下一个过滤器处理
            chain.filter(exchange)
            chain指继承了GatewayFilterChain的类DefaultGatewayFilterChain
        - 拿到响应
            exchange.getResponse()