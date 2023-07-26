# ext-springboot-test [![green](https://github.com/github/Sally1005/ext-springboot-test/actions/green.yml/badge.svg?event=push)]
(https://github.com/github/Sally1005/ext-springboot-test/actions/green.yml)

# 项目介绍

该工程的主要目的是学会如何进行单元测试和集成测试。所做的注册功能只包含后端，没有使用浏览器。陆续会实现登录功能，以便搭建权限管理页面。
会和仓库中前后端分离项目进行整合。

# 开发环境

其后端开发环境为IDEA + MySQL +SpringBoot + MyBatis。
 

# 目录结构

```                
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─lonton
│  │  │          └─ext
│  │  │              └─springboot
│  │  │                  └─test
│  │  │                      │ ExtSpringbootTestApplication.java              启动类
│  │  │                      ├─controller                                     控制层
│  │  │                      ├─entity                                         实体类  
│  │  │                      ├─ex                                             异常类
│  │  │                      ├─mapper                                         持久层
│  │  │                      ├─pojo                                           实体的封装
│  │  │                      ├─service                                        业务层
│  │  │                      └─validation                                     校验类
│  │  └─resources                                                             静态资源：包含xml文件、属性配置等
│  └─test
│      ├─java
│      │  └─com
│      │      └─lonton
│      │          └─ext
│      │              └─springboot
│      │                  └─test
│      │                      │ ExtSpringbootTestApplicationTests.java       数据库连接测试
│      │                      │  
│      │                      ├─controller
│      │                      │      UserControllerTests.java                 控制层测试
│      │                      │      
│      │                      ├─mapper
│      │                      │      UserMapperTests.java                     持久层测试
│      │                      │      
│      │                      ├─service
│      │                      │      UserServiceTests.java                    业务层测试
│      │                      │      
│      │                      └─validation
│      │                              RegExpressionTests.java                 正则表达式校验
       └─ resources                                                           sql脚本
                    
```


# 项目使用（开发环境）

1. 该工程是在 JDK1.8 环境下运行的，建议运行前先自行配置好环境变量；
2. 首先克隆项目的地址，打开Intellij IDEA后，分别点击File > New > Project from Version Control，再将克隆下来的地址粘贴到URL框中；
3. 展开src下的目录，运行ExtSpringbootTestApplication，在终端查看是否能够正常运行；
4. 能够正常启动项目后，将resources下的t_sql文件拿到，连接好数据库后，在数据库中建表；
5. 可对test包下每个方法进行逐一测试。



