

> 在mybatis的基础知识中我们已经可以对mybatis的工作方式窥斑见豹。但是，为什么还要要学习mybatis的工作原理？因为，随着mybatis框架的不断发展，如今已经越来越趋于自动化，从代码生成，到基本使用，我们甚至不需要动手写一句SQL就可以完成一个简单应用的全部CRUD操作。
>
> 从原生mybatis到mybatis-spring，到mybatis-plus再到mybatis-plus-spring-boot-starter。spring在发展，mybatis同样在随之发展。万变的外表终将迷惑人们的双眼，只要抓住核心我们永远不会迷茫！
>

## 工作原理原型图

![image](https://img-blog.csdn.net/20180624002302854?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTQ3NDUwNjk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## 工作原理解析
mybatis应用程序通过SqlSessionFactoryBuilder从mybatis-config.xml配置文件（也可以用Java文件配置的方式，需要添加@Configuration）中构建出SqlSessionFactory（线程安全）；

SqlSessionFactory的实例直接开启一个SqlSession，再通过SqlSession实例获得Mapper对象并运行Mapper映射的SQL语句，完成对数据库的CRUD和事务提交，之后关闭SqlSession。说明：SqlSession是单线程对象，因为它是非线程安全的，是持久化操作的独享对象，类似jdbc中的Connection，底层就封装了jdbc连接。

详细流程如下：

1、加载mybatis全局配置文件（数据源、mapper映射文件等），解析配置文件，MyBatis基于XML配置文件生成Configuration，和一个个MappedStatement（包括了参数映射配置、动态SQL语句、结果映射配置），其对应着<select | update | delete | insert>标签项。

2、SqlSessionFactoryBuilder通过Configuration对象生成SqlSessionFactory，用来开启SqlSession。

3、SqlSession对象完成和数据库的交互：

- 用户程序调用mybatis接口层api（即Mapper接口中的方法）
- SqlSession通过调用api的Statement ID找到对应的MappedStatement对象
- 通过Executor（负责动态SQL的生成和查询缓存的维护）将MappedStatement对象进行解析，sql参数转化、动态sql拼接，生成jdbc Statement对象
- JDBC执行sql。
- 借助MappedStatement中的结果映射关系，将返回结果转化成HashMap、JavaBean等存储结构并返回。

## MyBatis层次图
![image](https://img-blog.csdn.net/20180625095624918?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTQ3NDUwNjk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架，其主要就完成2件事情：

1.  封装JDBC操作
2.  利用反射打通Java类与SQL语句之间的相互转换

**MyBatis的主要设计目的就是让我们对执行SQL语句时对输入输出的数据管理更加方便，所以方便地写出SQL和方便地获取SQL的执行结果才是MyBatis的核心竞争力。**

## MyBatis的配置

MyBatis框架和其他绝大部分框架一样，需要一个配置文件，其配置文件大致如下：


```xml
<configuration>
    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="false"/>
        <!--<setting name="logImpl" value="STDOUT_LOGGING"/> &lt;!&ndash; 打印日志信息 &ndash;&gt;-->
    </settings>

    <typeAliases>
        <typeAlias type="com.luo.dao.UserDao" alias="User"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/> <!--事务管理类型-->
            <dataSource type="POOLED">
                <property name="username" value="luoxn28"/>
                <property name="password" value="123456"/>
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://192.168.1.150/ssh_study"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="userMapper.xml"/>
    </mappers>
</configuration>
```


以上配置中，最重要的是数据库参数的配置，比如用户名密码等，如果配置了数据表对应的mapper文件，则需要将其加入到<mappers>节点下。 

## MyBatis的主要成员

*   Configuration        MyBatis所有的配置信息都保存在Configuration对象之中，配置文件中的大部分配置都会存储到该类中
*   SqlSession            作为MyBatis工作的主要顶层API，表示和数据库交互时的会话，完成必要数据库增删改查功能
*   Executor               MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
*   StatementHandler 封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数等
*   ParameterHandler  负责对用户传递的参数转换成JDBC Statement 所对应的数据类型
*   ResultSetHandler   负责将JDBC返回的ResultSet结果集对象转换成List类型的集合
*   TypeHandler          负责java数据类型和jdbc数据类型(也可以说是数据表列类型)之间的映射和转换
*   MappedStatement  MappedStatement维护一条<select|update|delete|insert>节点的封装
*   SqlSource              负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回
*   BoundSql              表示动态生成的SQL语句以及相应的参数信息

以上主要成员在一次数据库操作中基本都会涉及，在SQL操作中重点需要关注的是SQL参数什么时候被设置和结果集怎么转换为JavaBean对象的，这两个过程正好对应StatementHandler和ResultSetHandler类中的处理逻辑。

![](http://img.blog.csdn.net/20141028140852531?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



## 参考文章

[MyBatis框架及原理分析](https://www.cnblogs.com/adolfmc/p/8997281.html)

[《深入理解mybatis原理》 MyBatis的架构设计以及实例分析](http://blog.csdn.net/luanlouis/article/details/40422941)

https://www.jianshu.com/p/e398435fc1c4

https://segmentfault.com/a/1190000015117926?utm_source=tag-newest#articleHeader4

https://blog.csdn.net/u014745069/article/details/80788127

https://blog.csdn.net/u014297148/article/details/78696096

https://blog.csdn.net/weixin_43184769/article/details/91126687               
