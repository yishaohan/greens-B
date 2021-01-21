# Getting Started

### RESTful设计指南

* GET(SELECT): 从服务器取出资源(一项或多项)
* POST(CREATE): 在服务器新建一个资源
* PUT(UPDATE): 在服务器更新资源(客户端提供改变后的完整资源)
* PATCH(UPDATE): 在服务器更新资源(客户端提供改变的属性)
* DELETE(DELETE): 从服务器删除资源
* ================================
* HEAD: 获取资源的元数据
* OPTIONS: 获取信息, 关于资源的哪些属性是客户端可以改变的

### 例子

* GET /zoos: 列出所有动物园
* POST /zoos: 新建一个动物园
* GET /zoos/ID: 获取某个指定动物园的信息
* PUT /zoos/ID: 更新某个指定动物园的信息(提供该动物园的全部信息)
* PATCH /zoos/ID: 更新某个指定动物园的信息(提供该动物园的部分信息)
* DELETE /zoos/ID: 删除某个动物园

### 参数

* ?limit=10: 指定返回记录的数量
* ?offset=10: 指定返回记录的开始位置
* ?page=2&per_page=100: 指定第几页, 以及每页的记录数
* ?sortby=name&order=asc: 指定返回结果按照哪个属性排序, 以及排序顺序
* ?animal_type_id=1: 指定筛选条件

### 状态码

* 200 OK - [GET]: 服务器成功返回用户请求的数据, 该操作是幂等的(Idempotent)
* 201 CREATED - [POST/PUT/PATCH]: 用户新建或修改数据成功
* 202 Accepted - [*]: 表示一个请求已经进入后台排队(异步任务)
* 204 NO CONTENT - [DELETE]: 用户删除数据成功
* 400 INVALID REQUEST - [POST/PUT/PATCH]: 用户发出的请求有错误, 服务器没有进行新建或修改数据的操作, 该操作是幂等的
* 401 Unauthorized - [*]: 表示用户没有权限(令牌, 用户名, 密码错误)
* 403 Forbidden - [*]: 表示用户得到授权(与401错误相对), 但是访问是被禁止的
* 404 NOT FOUND - [*]: 用户发出的请求针对的是不存在的记录, 服务器没有进行操作, 该操作是幂等的
* 406 Not Acceptable - [GET]: 用户请求的格式不可得(比如用户请求JSON格式, 但是只有XML格式)
* 410 Gone -[GET]: 用户请求的资源被永久删除, 且不会再得到的
* 422 Unprocesable entity - [POST/PUT/PATCH]: 当创建一个对象时, 发生一个验证错误
* 500 INTERNAL SERVER ERROR - [*]: 服务器发生错误, 用户将无法判断发出的请求是否成功

### 返回结果

* GET /collection: 返回资源对象的列表(数组)
* GET /collection/resource: 返回单个资源对象
* POST /collection: 返回新生成的资源对象
* PUT /collection/resource: 返回完整的资源对象
* PATCH /collection/resource: 返回完整的资源对象
* DELETE /collection/resource: 返回一个空文档

### 注意

#### 避免多级URL

* GET /authors/12/categories/2 (错误示范)
* GET /authors/12?categories=2 (正确示范)
* GET /articles/published      (错误示范)
* GET /articles?published=true (正确示范)
* 发生错误时, 不要返回 200 状态码

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

### Guides

The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

# 问题

* 在注册页面,获取验证码
* 在登陆页面直接登录

* rememberMe登录无法记录
* session超时,更新LoginLogs

* 登陆失败不会发布Event