本仓库为【Road2Architect】(中文名：架构师之路)力求打造最完整最实用的软件工程师学习指南！

这些文章和总结都是我近几年学习的总结，非常实用，应该是最全面最完整的技术仓库。

整理这些文章的初衷是做好个人知识管理，构建完整健康的知识体系，在持续输入的前提下保证有效输出。
契机是阅读了这篇文章 [求知成瘾，却无作品](https://www.jianshu.com/p/Daxrnq) 


## 目录

- [Java基础](#Java基础)
    - [基础知识](#基础知识)
    - [容器](#容器)
    - [设计模式](#设计模式)
- [JavaWeb](#JavaWeb)
    - [Srping](#Spring)
    - [SpringMVC](#SpringMVC)
    - [SpringBoot](#SpringBoot)
- [Java进阶](#Java进阶)
    - [并发](#并发)
    - [JVM](#JVM)
    - [Java网络编程](#Java网络编程)
- [计算机基础](#计算机基础)
    - [计算机网络](#计算机网络)
    - [操作系统](#操作系统)
        - [Linux相关](#linux相关)
    - [数据结构与算法](#数据结构与算法)
        - [数据结构](#数据结构)
        - [算法](#算法)
- [数据库](#数据库)
    - [MySQL](#MySQL)
- [缓存](#缓存)
    - [Redis](#Redis)
- [消息队列](#消息队列)
    - [Kafka](#Kafka)
- [大后端](#大后端)
- [分布式](#分布式)
    - [理论](#理论)
    - [实战](#实战)
- [面试指南](#面试指南)
    - [校招指南](#校招指南)
    - [面经](#面经)
- [工具](#工具)
    - [Git](#git)
- [资料](#资料)
    - [书单](#书单)
- [待办](#待办)
- [说明](#说明)
- [微信公众号](#微信公众号)

## Java基础

### 基础知识

* [面向对象基础](docs/java/basic/1、面向对象基础.md)
* [Java基本数据类型](docs/java/basic/2、Java基本数据类型.md)
* [string和包装类](docs/java/basic/3、string和包装类.md)
* [final关键字特性](docs/java/basic/4、final关键字特性.md)
* [Java类和包](docs/java/basic/5、Java类和包.md)
* [抽象类和接口](docs/java/basic/6、抽象类和接口.md)
* [代码块和代码执行顺序](docs/java/basic/7、代码块和代码执行顺序.md)
* [Java自动拆箱装箱里隐藏的秘密](docs/java/basic/8、Java自动拆箱装箱里隐藏的秘密.md)
* [Java中的Class类和Object类](docs/java/basic/9、Java中的Class类和Object类.md)
* [Java异常](docs/java/basic/10、Java异常.md)
* [解读Java中的回调](docs/java/basic/11、解读Java中的回调.md)
* [反射](docs/java/basic/12、反射.md)
* [泛型](docs/java/basic/13、泛型.md)
* [枚举类](docs/java/basic/14、枚举类.md)
* [Java注解和最佳实践](docs/java/basic/15、Java注解和最佳实践.md)
* [JavaIO流](docs/java/basic/16、JavaIO流.md)
* [多线程](docs/java/basic/17、多线程.md)
* [深入理解内部类](docs/java/basic/18、深入理解内部类.md)
* [javac和javap](docs/java/basic/19、Java集合框架梳理.md)
* [Java8新特性终极指南](docs/java/basic/20、javac和javap.md)
* [Java类和包](docs/java/basic/21、Java8新特性终极指南.md)
* [序列化和反序列化](docs/java/basic/22、序列化和反序列化.md)
* [继承、封装、多态的实现原理](docs/java/basic/23、继承、封装、多态的实现原理.md)

### 容器

* [Java集合类总结](docs/java/collection/Java集合类总结.md)
* [Java集合详解1：一文读懂ArrayList,Vector与Stack使用方法和实现原理](docs/java/collection/Java集合详解1：一文读懂ArrayList,Vector与Stack使用方法和实现原理.md)  
* [Java集合详解2：Queue和LinkedList](docs/java/collection/Java集合详解2：Queue和LinkedList.md)
* [Java集合详解3：Iterator，fail-fast机制与比较器](docs/java/collection/Java集合详解3：Iterator，fail-fast机制与比较器.md)
* [Java集合详解4：HashMap和HashTable](docs/java/collection/Java集合详解4：HashMap和HashTable.md)
* [Java集合详解5：深入理解LinkedHashMap和LRU缓存](docs/java/collection/Java集合详解5：深入理解LinkedHashMap和LRU缓存.md)
* [Java集合详解6：TreeMap和红黑树](docs/java/collection/Java集合详解6：TreeMap和红黑树.md)
* [Java集合详解7：HashSet，TreeSet与LinkedHashSet](docs/java/collection/Java集合详解7：HashSet，TreeSet与LinkedHashSet.md)
* [Java集合详解8：Java集合类细节精讲](docs/java/collection/Java集合详解8：Java集合类细节精讲.md)

### 设计模式

* [设计模式学习总结](docs/java/design-parttern/设计模式学习总结.md)
* [初探Java设计模式1：创建型模式（工厂，单例等）.md](docs/java/design-parttern/初探Java设计模式1：创建型模式（工厂，单例等）.md)
* [初探Java设计模式2：结构型模式（代理模式，适配器模式等）.md](docs/java/design-parttern/初探Java设计模式2：结构型模式（代理模式，适配器模式等）.md)
* [初探Java设计模式3：行为型模式（策略，观察者等）.md](docs/java/design-parttern/初探Java设计模式3：行为型模式（策略，观察者等）.md)
* [初探Java设计模式4：JDK中的设计模式.md](docs/java/design-parttern/初探Java设计模式4：JDK中的设计模式.md)
* [初探Java设计模式5：Spring涉及到的9种设计模式.md](docs/java/design-parttern/初探Java设计模式5：Spring涉及到的9种设计模式.md)

## JavaWeb

* [走进JavaWeb技术世界1：JavaWeb的由来和基础知识](docs/java-web/走进JavaWeb技术世界1：JavaWeb的由来和基础知识.md)
* [走进JavaWeb技术世界2：JSP与Servlet的曾经与现在](docs/java-web/走进JavaWeb技术世界2：JSP与Servlet的曾经与现在.md)
* [走进JavaWeb技术世界3：JDBC的进化与连接池技术](docs/java-web/走进JavaWeb技术世界3：JDBC的进化与连接池技术.md)
* [走进JavaWeb技术世界4：Servlet 工作原理详解](docs/java-web/走进JavaWeb技术世界4：Servlet%20工作原理详解.md)
* [走进JavaWeb技术世界5：初探Tomcat的HTTP请求过程](docs/java-web/走进JavaWeb技术世界5：初探Tomcat的HTTP请求过程.md)
* [走进JavaWeb技术世界6：Tomcat5总体架构剖析](docs/java-web/走进JavaWeb技术世界6：Tomcat5总体架构剖析.md)
* [走进JavaWeb技术世界7：Tomcat和其他WEB容器的区别](docs/java-web/走进JavaWeb技术世界7：Tomcat和其他WEB容器的区别.md)
* [走进JavaWeb技术世界8：浅析Tomcat9请求处理流程与启动部署过程](docs/java-web/走进JavaWeb技术世界8：浅析Tomcat9请求处理流程与启动部署过程.md)
* [走进JavaWeb技术世界9：Java日志系统的诞生与发展](docs/java-web/走进JavaWeb技术世界9：Java日志系统的诞生与发展.md)
* [走进JavaWeb技术世界10：从JavaBean讲到Spring](docs/java-web/走进JavaWeb技术世界10：从JavaBean讲到Spring.md)
* [走进JavaWeb技术世界11：单元测试框架Junit](docs/java-web/走进JavaWeb技术世界11：单元测试框架Junit.md)
* [走进JavaWeb技术世界12：从手动编译打包到项目构建工具Maven](docs/java-web/走进JavaWeb技术世界12：从手动编译打包到项目构建工具Maven.md)
* [走进JavaWeb技术世界13：Hibernate入门经典与注解式开发](docs/java-web/走进JavaWeb技术世界13：Hibernate入门经典与注解式开发.md)
* [走进JavaWeb技术世界14：Mybatis入门](docs/java-web/走进JavaWeb技术世界14：Mybatis入门.md)
* [深入JavaWeb技术世界15：深入浅出Mybatis基本原理](docs/java-web/深入JavaWeb技术世界15：深入浅出Mybatis基本原理.md)
* [走进JavaWeb技术世界16：极简配置的SpringBoot](docs/java-web/走进JavaWeb技术世界16：极简配置的SpringBoot.md)

### Spring

* [Spring源码剖析1：Spring概述](docs/java-web/Spring/Spring源码剖析1：Spring概述.md)
* [Spring源码剖析2：初探Spring IOC核心流程](docs/java-web/Spring/Spring源码剖析2：初探Spring%20IOC核心流程.md)
* [Spring源码剖析3：Spring IOC容器的加载过程 ](docs/java-web/Spring/Spring源码剖析3：Spring%20IOC容器的加载过程.md)
* [Spring源码剖析4：懒加载的单例Bean获取过程分析](docs/java-web/Spring/Spring源码剖析4：懒加载的单例Bean获取过程分析.md)
* [Spring源码剖析5：JDK和cglib动态代理原理详解 ](docs/java-web/Spring/Spring源码剖析5：JDK和cglib动态代理原理详解.md)
* [Spring源码剖析6：Spring AOP概述](docs/java-web/Spring/Spring源码剖析6：Spring%20AOP概述.md)
* [Spring源码剖析7：AOP实现原理详解 ](docs/java-web/Spring/Spring源码剖析7：AOP实现原理详解.md)
* [Spring源码剖析8：Spring事务概述](docs/java-web/Spring/Spring源码剖析8：Spring事务概述.md)
* [Spring源码剖析9：Spring事务源码剖析](docs/java-web/Spring/Spring源码剖析9：Spring事务源码剖析.md)

### SpringMVC

* [SpringMVC源码分析1：SpringMVC概述](docs/java-web/Spring/SSM/SpringMVC源码分析1：SpringMVC概述.md)
* [SpringMVC源码分析2：SpringMVC设计理念与DispatcherServlet](docs/java-web/Spring/SSM/SpringMVC源码分析2：SpringMVC设计理念与DispatcherServlet.md)
* [SpringMVC源码分析3：DispatcherServlet的初始化与请求转发 ](docs/java-web/Spring/SSM/SpringMVC源码分析3：DispatcherServlet的初始化与请求转发.md)
* [SpringMVC源码分析4：DispatcherServlet如何找到正确的Controller ](docs/java-web/Spring/SSM/SpringMVC源码分析4：DispatcherServlet如何找到正确的Controller.md)
* [SpringMVC源码剖析5：消息转换器HttpMessageConverter与@ResponseBody注解](docs/java-web/Spring/SSM/SpringMVC源码剖析5：消息转换器HttpMessageConverter与@ResponseBody注解.md)
* [SpringMVC源码分析6：SpringMVC的视图解析原理 ](docs/java-web/Spring/SSM/SpringMVC源码分析6：SpringMVC的视图解析原理.md)

### SpringBoot

todo

### SpringCloud

todo

## Java进阶

### 并发

* [Java并发指南1：并发基础与Java多线程](docs/java/currency/Java并发指南1：并发基础与Java多线程.md)
* [Java并发指南2：深入理解Java内存模型JMM](docs/java/currency/Java并发指南2：深入理解Java内存模型JMM.md)
* [Java并发指南3：并发三大问题与volatile关键字，CAS操作](docs/java/currency/Java并发指南3：并发三大问题与volatile关键字，CAS操作.md)
* [Java并发指南4：Java中的锁 Lock和synchronized](docs/java/currency/Java并发指南4：Java中的锁%20Lock和synchronized.md)
* [Java并发指南5：JMM中的final关键字解析](docs/java/currency/Java并发指南5：JMM中的final关键字解析.md)
* [Java并发指南6：Java内存模型JMM总结](docs/java/currency/Java并发指南6：Java内存模型JMM总结.md)
* [Java并发指南7：JUC的核心类AQS详解](docs/java/currency/Java并发指南7：JUC的核心类AQS详解.md)
* [Java并发指南8：AQS中的公平锁与非公平锁，Condtion](docs/java/currency/Java并发指南8：AQS中的公平锁与非公平锁，Condtion.md)
* [Java并发指南9：AQS共享模式与并发工具类的实现](docs/java/currency/Java并发指南9：AQS共享模式与并发工具类的实现.md)
* [Java并发指南10：Java 读写锁 ReentrantReadWriteLock 源码分析](docs/java/currency/Java并发指南10：Java%20读写锁%20ReentrantReadWriteLock%20源码分析.md)
* [Java并发指南11：解读 Java 阻塞队列 BlockingQueue](docs/java/currency/Java并发指南11：解读%20Java%20阻塞队列%20BlockingQueue.md)
* [Java并发指南12：深度解读 java 线程池设计思想及源码实现](docs/java/currency/Java并发指南12：深度解读%20java%20线程池设计思想及源码实现.md)
* [Java并发指南13：Java 中的 HashMap 和 ConcurrentHashMap 全解析](https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/currency/Java%E5%B9%B6%E5%8F%91%E6%8C%87%E5%8D%9713%EF%BC%9AJava%20%E4%B8%AD%E7%9A%84%20HashMap%20%E5%92%8C%20ConcurrentHashMap%20%E5%85%A8%E8%A7%A3%E6%9E%90.md)
* [Java并发指南14：JUC中常用的Unsafe和Locksupport](docs/java/currency/Java并发指南14：JUC中常用的Unsafe和Locksupport.md)
* [Java并发指南15：Fork join并发框架与工作窃取算法剖析](docs/java/currency/Java并发编程指南15：Fork%20join并发框架与工作窃取算法剖析.md)
* [Java并发编程学习总结](https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/currency/Java%E5%B9%B6%E5%8F%91%E6%80%BB%E7%BB%93.md)

### JVM

* [JVM总结](docs/java/jvm/JVM总结.md)
* [深入理解JVM虚拟机1：JVM内存的结构与消失的永久代](docs/java/jvm/深入理解JVM虚拟机1：JVM内存的结构与消失的永久代.md)
* [深入理解JVM虚拟机2：JVM垃圾回收基本原理和算法](docs/java/jvm/深入理解JVM虚拟机2：JVM垃圾回收基本原理和算法.md)
* [深入理解JVM虚拟机3：垃圾回收器详解](docs/java/jvm/深入理解JVM虚拟机3：垃圾回收器详解.md)
* [深入理解JVM虚拟机4：Javaclass介绍与解析实践](docs/java/jvm/深入理解JVM虚拟机4：Javaclass介绍与解析实践.md)
* [深入理解JVM虚拟机5：虚拟机字节码执行引擎](docs/java/jvm/深入理解JVM虚拟机5：虚拟机字节码执行引擎.md)
* [深入理解JVM虚拟机6：深入理解JVM类加载机制](docs/java/jvm/深入理解JVM虚拟机6：深入理解JVM类加载机制.md)
* [深入理解JVM虚拟机7：JNDI，OSGI，Tomcat类加载器实现](docs/java/jvm/深入理解JVM虚拟机7：JNDI，OSGI，Tomcat类加载器实现.md)
* [深入了解JVM虚拟机8：Java的编译期优化与运行期优化](docs/java/jvm/深入了解JVM虚拟机8：Java的编译期优化与运行期优化.md)
* [深入理解JVM虚拟机9：JVM监控工具与诊断实践](docs/java/jvm/深入理解JVM虚拟机9：JVM监控工具与诊断实践.md)
* [深入理解JVM虚拟机10：JVM常用参数以及调优实践](docs/java/jvm/深入理解JVM虚拟机10：JVM常用参数以及调优实践.md)
* [深入理解JVM虚拟机11：Java内存异常原理与实践](docs/java/jvm/深入理解JVM虚拟机11：Java内存异常原理与实践.md)
* [深入理解JVM虚拟机12：JVM性能管理神器VisualVM介绍与实战](docs/java/jvm/深入理解JVM虚拟机12：JVM性能管理神器VisualVM介绍与实战.md)
* [深入理解JVM虚拟机13：再谈四种引用及GC实践](docs/java/jvm/深入理解JVM虚拟机13：再谈四种引用及GC实践.md)
* [深入理解JVM虚拟机14：GC调优思路与常用工具](docs/java/jvm/深入理解JVM虚拟机：GC调优思路与常用工具.md)

### Java网络编程

* [Java网络编程和NIO详解1：JAVA 中原生的 socket 通信机制](docs/java/jvm/Java网络编程和NIO详解1：JAVA%20中原生的%20socket%20通信机制.md)
* [Java网络编程与NIO详解2：JAVA NIO 一步步构建IO多路复用的请求模型](docs/java/jvm/Java网络编程与NIO详解2：JAVA%20NIO%20一步步构建IO多路复用的请求模型.md) 
* [Java网络编程和NIO详解3：IO模型与Java网络编程模型](docs/java/jvm/Java网络编程和NIO详解3：IO模型与Java网络编程模型.md) 
* [Java网络编程与NIO详解4：浅析NIO包中的Buffer、Channel 和 Selector](https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/network-programming/Java%E7%BD%91%E7%BB%9C%E7%BC%96%E7%A8%8B%E4%B8%8ENIO%E8%AF%A6%E8%A7%A34%EF%BC%9A%E6%B5%85%E6%9E%90NIO%E5%8C%85%E4%B8%AD%E7%9A%84Buffer%E3%80%81Channel%20%E5%92%8C%20Selector.md) 
* [Java网络编程和NIO详解5：Java 非阻塞 IO 和异步 IO](https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/network-programming/Java%E7%BD%91%E7%BB%9C%E7%BC%96%E7%A8%8B%E5%92%8CNIO%E8%AF%A6%E8%A7%A35%EF%BC%9AJava%20%E9%9D%9E%E9%98%BB%E5%A1%9E%20IO%20%E5%92%8C%E5%BC%82%E6%AD%A5%20IO.md)
* [Java网络编程和NIO详解6：Linux epoll实现原理详解](docs/java/jvm/Java网络编程和NIO详解6：Linux%20epoll实现原理详解.md) 
* [Java网络编程和NIO详解7：浅谈 Linux 中NIO Selector 的实现原理](Java网络编程和NIO详解7：浅谈%20Linux%20中NIO%20Selector%20的实现原理.md)
* [Java网络编程与NIO详解8：浅析mmap和Direct Buffer](https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/network-programming/Java%E7%BD%91%E7%BB%9C%E7%BC%96%E7%A8%8B%E4%B8%8ENIO%E8%AF%A6%E8%A7%A38%EF%BC%9A%E6%B5%85%E6%9E%90mmap%E5%92%8CDirect%20Buffer.md)
* [Java网络编程和NIO详解9：基于NIO的网络编程框架Netty](docs/java/jvm/Java网络编程和NIO详解9：基于NIO的网络编程框架Netty.md)
* [Java网络编程与NIO详解10：深度解读Tomcat中的NIO模型](https://github.com/h2pl/Java-Tutorial/blob/master/docs/java/network-programming/Java%E7%BD%91%E7%BB%9C%E7%BC%96%E7%A8%8B%E4%B8%8ENIO%E8%AF%A6%E8%A7%A310%EF%BC%9A%E6%B7%B1%E5%BA%A6%E8%A7%A3%E8%AF%BBTomcat%E4%B8%AD%E7%9A%84NIO%E6%A8%A1%E5%9E%8B.md)
* [Java网络编程与NIO详解11：Tomcat中的Connector源码分析（NIO）](docs/java/jvm/Java网络编程与NIO详解11：Tomcat中的Connector源码分析（NIO）.md)

## 计算机基础

### 计算机网络
todo


### 操作系统
todo

#### Linux相关
todo


### 数据结构与算法
todo

#### 数据结构
todo

#### 算法
todo

## 数据库
todo

### MySQL
* [Mysql原理与实践总结](docs/database/Mysql原理与实践总结.md)
* [重新学习Mysql数据库1：无废话MySQL入门](docs/database/重新学习Mysql数据库1：无废话MySQL入门.md)
* [重新学习Mysql数据库2：『浅入浅出』MySQL 和 InnoDB](docs/database/重新学习Mysql数据库2：%20『浅入浅出』MySQL%20和%20InnoDB.md)
* [重新学习Mysql数据库3：Mysql存储引擎与数据存储原理](docs/database/重新学习Mysql数据库3：Mysql存储引擎与数据存储原理.md)
* [重新学习Mysql数据库4：Mysql索引实现原理和相关数据结构算法](docs/database/重新学习Mysql数据库4：Mysql索引实现原理和相关数据结构算法.md)
* [重新学习Mysql数据库5：根据MySQL索引原理进行分析与优化](docs/database/重新学习Mysql数据库5：根据MySQL索引原理进行分析与优化.md)
* [重新学习MySQL数据库6：浅谈MySQL的中事务与锁](docs/database/重新学习MySQL数据库6：浅谈MySQL的中事务与锁.md) 
* [重新学习Mysql数据库7：详解MyIsam与InnoDB引擎的锁实现](docs/database/重新学习Mysql数据库7：详解MyIsam与InnoDB引擎的锁实现.md) 
* [重新学习Mysql数据库8：MySQL的事务隔离级别实战](docs/database/重新学习Mysql数据库8：MySQL的事务隔离级别实战.md)
* [重新学习MySQL数据库9：Innodb中的事务隔离级别和锁的关系](docs/database/重新学习MySQL数据库9：Innodb中的事务隔离级别和锁的关系.md) 
* [重新学习MySQL数据库10：MySQL里的那些日志们](docs/database/重新学习MySQL数据库10：MySQL里的那些日志们.md) 
* [重新学习MySQL数据库11：以Java的视角来聊聊SQL注入](docs/database/重新学习MySQL数据库11：以Java的视角来聊聊SQL注入.md) 
* [重新学习MySQL数据库12：从实践sql语句优化开始](docs/database/重新学习MySQL数据库12：从实践sql语句优化开始.md) 
* [重新学习Mysql数据库13：Mysql主从复制，读写分离，分表分库策略与实践](docs/database/重新学习Mysql数据13：Mysql主从复制，读写分离，分表分库策略与实践.md)


## 缓存

### Redis
* [Redis原理与实践总结](docs/cache/Redis原理与实践总结.md)
* [探索Redis设计与实现开篇：什么是Redis](docs/cache/探索Redis设计与实现开篇：什么是Redis.md)
* [探索Redis设计与实现1：Redis 的基础数据结构概览](docs/cache/探索Redis设计与实现1：Redis%20的基础数据结构概览.md)
* [探索Redis设计与实现2：Redis内部数据结构详解——dict](docs/cache/探索Redis设计与实现2：Redis内部数据结构详解——dict.md)
* [探索Redis设计与实现3：Redis内部数据结构详解——sds](docs/cache/探索Redis设计与实现3：Redis内部数据结构详解——sds.md)
* [探索Redis设计与实现4：Redis内部数据结构详解——ziplist](docs/cache/探索Redis设计与实现4：Redis内部数据结构详解——ziplist.md)
* [探索Redis设计与实现5：Redis内部数据结构详解——quicklist](docs/cache/探索Redis设计与实现5：Redis内部数据结构详解——quicklist.md)
* [探索Redis设计与实现6：Redis内部数据结构详解——skiplist](docs/cache/探索Redis设计与实现6：Redis内部数据结构详解——skiplist.md)
* [探索Redis设计与实现7：Redis内部数据结构详解——intset](docs/cache/探索Redis设计与实现7：Redis内部数据结构详解——intset.md)
* [探索Redis设计与实现8：连接底层与表面的数据结构robj](docs/cache/探索Redis设计与实现8：连接底层与表面的数据结构robj.md)
* [探索Redis设计与实现9：数据库redisDb与键过期删除策略](docs/cache/探索Redis设计与实现9：数据库redisDb与键过期删除策略.md)
* [探索Redis设计与实现10：Redis的事件驱动模型与命令执行过程](docs/cache/探索Redis设计与实现10：Redis的事件驱动模型与命令执行过程.md)
* [探索Redis设计与实现11：使用快照和AOF将Redis数据持久化到硬盘中](docs/cache/探索Redis设计与实现11：使用快照和AOF将Redis数据持久化到硬盘中.md)
* [探索Redis设计与实现12：浅析Redis主从复制](docs/cache/探索Redis设计与实现12：浅析Redis主从复制.md)
* [探索Redis设计与实现13：Redis集群机制及一个Redis架构演进实例](docs/cache/探索Redis设计与实现13：Redis集群机制及一个Redis架构演进实例.md)
* [探索Redis设计与实现14：Redis事务浅析与ACID特性介绍](docs/cache/探索Redis设计与实现14：Redis事务浅析与ACID特性介绍.md)
* [探索Redis设计与实现15：Redis分布式锁进化史 ](docs/cache/探索Redis设计与实现15：Redis分布式锁进化史.md )

## 消息队列

### Kafka

## 大后端
* [后端技术杂谈开篇：云计算，大数据与AI的故事](docs/big-backEnd/后端技术杂谈开篇：云计算，大数据与AI的故事.md)
* [后端技术杂谈1：搜索引擎基础倒排索引](docs/big-backEnd/后端技术杂谈1：搜索引擎基础倒排索引.md)
* [后端技术杂谈2：搜索引擎工作原理](docs/big-backEnd/后端技术杂谈2：搜索引擎工作原理.md)
* [后端技术杂谈3：Lucene基础原理与实践](docs/big-backEnd/后端技术杂谈3：Lucene基础原理与实践.md)
* [后端技术杂谈4：Elasticsearch与solr入门实践](docs/big-backEnd/后端技术杂谈4：Elasticsearch与solr入门实践.md)
* [后端技术杂谈5：云计算的前世今生](docs/big-backEnd/后端技术杂谈5：云计算的前世今生.md)
* [后端技术杂谈6：白话虚拟化技术](docs/big-backEnd/后端技术杂谈6：白话虚拟化技术.md )
* [后端技术杂谈7：OpenStack的基石KVM](docs/big-backEnd/后端技术杂谈7：OpenStack的基石KVM.md)
* [后端技术杂谈8：OpenStack架构设计](docs/big-backEnd/后端技术杂谈8：OpenStack架构设计.md)
* [后端技术杂谈9：先搞懂Docker核心概念吧](docs/big-backEnd/后端技术杂谈9：先搞懂Docker核心概念吧.md)
* [后端技术杂谈10：Docker 核心技术与实现原理](docs/big-backEnd/后端技术杂谈10：Docker%20核心技术与实现原理.md)
* [后端技术杂谈11：十分钟理解Kubernetes核心概念](docs/big-backEnd/后端技术杂谈11：十分钟理解Kubernetes核心概念.md)
* [后端技术杂谈12：捋一捋大数据研发的基本概念](docs/big-backEnd/后端技术杂谈12：捋一捋大数据研发的基本概念.md)

## 分布式
### 理论
* [分布式系统理论基础开篇：从放弃到入门](docs/distrubuted/理论/分布式系统理论基础开篇：从放弃到入门.md)
* [分布式系统理论基础1： 一致性、2PC和3PC ](docs/distrubuted/理论/分布式系统理论基础1：%20一致性、2PC和3PC.md)
* [分布式系统理论基础2：CAP ](docs/distrubuted/理论/分布式系统理论基础2%20：CAP.md)
* [分布式系统理论基础3： 时间、时钟和事件顺序](docs/distrubuted/理论/分布式系统理论基础3：%20时间、时钟和事件顺序.md)
* [分布式系统理论基础4：Paxos](docs/distrubuted/理论/分布式系统理论基础4：Paxos.md)
* [分布式系统理论基础5：选举、多数派和租约](docs/distrubuted/理论/分布式系统理论基础5：选举、多数派和租约.md)
* [分布式系统理论基础6：Raft、Zab ](docs/distrubuted/理论/分布式系统理论基础6：Raft、Zab.md)
* [分布式系统理论进阶7：Paxos变种和优化 ](docs/distrubuted/理论/分布式系统理论进阶7：Paxos变种和优化.md)
* [分布式系统理论基础8：zookeeper分布式协调服务 ](docs/distrubuted/理论/分布式系统理论基础8：zookeeper分布式协调服务.md)

### 技术
* [搞懂分布式技术1：分布式系统的一些基本概念 ](docs/distrubuted/实战/搞懂分布式技术1：分布式系统的一些基本概念.md )
* [搞懂分布式技术2：分布式一致性协议与Paxos，Raft算法](docs/distrubuted/实战/搞懂分布式技术2：分布式一致性协议与Paxos，Raft算法.md)
* [搞懂分布式技术3：初探分布式协调服务zookeeper ](docs/distrubuted/实战/搞懂分布式技术3：初探分布式协调服务zookeeper.md )
* [搞懂分布式技术4：ZAB协议概述与选主流程详解 ](docs/distrubuted/实战/搞懂分布式技术4：ZAB协议概述与选主流程详解.md )
* [搞懂分布式技术5：Zookeeper的配置与集群管理实战](docs/distrubuted/实战/搞懂分布式技术5：Zookeeper的配置与集群管理实战.md)
* [搞懂分布式技术6：Zookeeper典型应用场景及实践 ](docs/distrubuted/实战/搞懂分布式技术6：Zookeeper典型应用场景及实践.md )
* [搞懂分布式技术7：负载均衡概念与主流方案](docs/distrubuted/实战/搞懂分布式技术7：负载均衡概念与主流方案.md)
* [搞懂分布式技术8：负载均衡原理剖析 ](docs/distrubuted/实战/搞懂分布式技术8：负载均衡原理剖析.md )
* [搞懂分布式技术9：Nginx负载均衡原理与实践 ](docs/distrubuted/实战/搞懂分布式技术9：Nginx负载均衡原理与实践.md)
* [搞懂分布式技术10：LVS实现负载均衡的原理与实践 ](docs/distrubuted/实战/搞懂分布式技术10：LVS实现负载均衡的原理与实践.md )
* [搞懂分布式技术11：分布式session解决方案与一致性hash](docs/distrubuted/实战/搞懂分布式技术11：分布式session解决方案与一致性hash.md)
* [搞懂分布式技术12：分布式ID生成方案 ](docs/distrubuted/实战/搞懂分布式技术12：分布式ID生成方案.md )
* [搞懂分布式技术13：缓存的那些事](docs/distrubuted/实战/搞懂分布式技术13：缓存的那些事.md)
* [搞懂分布式技术14：Spring Boot使用注解集成Redis缓存](docs/distrubuted/实战/搞懂分布式技术14：Spring%20Boot使用注解集成Redis缓存.md)
* [搞懂分布式技术15：缓存更新的套路 ](docs/distrubuted/实战/搞懂分布式技术15：缓存更新的套路.md )
* [搞懂分布式技术16：浅谈分布式锁的几种方案 ](docs/distrubuted/实战/搞懂分布式技术16：浅谈分布式锁的几种方案.md )
* [搞懂分布式技术17：浅析分布式事务 ](docs/distrubuted/实战/搞懂分布式技术17：浅析分布式事务.md )
* [搞懂分布式技术18：分布式事务常用解决方案 ](docs/distrubuted/实战/搞懂分布式技术18：分布式事务常用解决方案.md )
* [搞懂分布式技术19：使用RocketMQ事务消息解决分布式事务 ](docs/distrubuted/实战/搞懂分布式技术19：使用RocketMQ事务消息解决分布式事务.md )
* [搞懂分布式技术20：消息队列因何而生](docs/distrubuted/实战/搞懂分布式技术20：消息队列因何而生.md)
* [搞懂分布式技术21：浅谈分布式消息技术 Kafka ](docs/distrubuted/实战/搞懂分布式技术21：浅谈分布式消息技术%20Kafka.md )


## 面试指南

todo
### 校招指南
todo

### 面经
todo

## 工具
todo

## 资料
todo

### 书单
todo

## 待办
springboot和springcloud
Pretty Savage - BLACKPINK
空心 - 光泽
Coming Home - Dash Berlin,Bo Bruce
Call Me Maybe - Carly Rae Jepsen
大风吹 - 刘惜君,王赫野
넘버나인 (No.9) - T-ara
Speak Softly Love - The Original Movies Orchestra
Exodus - Maksim Mrvica
Croatian Rhapsody - Maksim Mrvica
The Mass - Era
韩妹带你摇 - 枫宇辰
EGM - 潮音病人
夜场顶级 - 林金煌
Thumbs Up (S2 & SJ Remix Ver.) - MOMOLAND
넘버나인 (No.9) - T-ara
둘이서 - 蔡妍
PLANET - Lambsey
Mr.Taxi - 少女时代
Gee - 少女时代
Miniskirt - AOA
사뿐사뿐 - AOA
마리오네트 - Stellar
위아래 - EXID
Trap（Feat.圭贤＆泰民） - 刘宪华,圭贤 (규현),泰民 (태민)
Jealousy - French Kiss
Read All About It - DJ成总
Every Breath you take - The Hitters
Way Back - Vicetone,Cozi Zuehlsdorff
Kkgm - 舞曲帝
耳朵怀孕 - 绪尘
泰语童声ให้เคอรี่มาส่งได้บ่ - 拼音师
Unstoppable - Sia
Burning - Maria Arredondo
Trouble Is a Friend(Album Version) - Lenka
Extreme Ways (Bourne's Legacy) - Moby
I Know You - Skylar Grey
Earned It(Fifty Shades Of Grey) - The Weeknd
I Don't Wanna Live Forever (Fifty Shades Darker) - ZAYN,Taylor Swift
Love Me Like You Do - Ellie Goulding
Walk Thru Fire - Vicetone,Meron Ryan
Dream It Possible - Delacey
Monsters - Katie Sky
I Am You - Kim Taylor
The Nights - Avicii
You Need To Calm Down - Taylor Swift
Welcome To New York - Taylor Swift
Blank Space - Taylor Swift
End Game - Taylor Swift,Ed Sheeran,Future
Perfect - Ed Sheeran
Shape of You - Ed Sheeran
Thinking Out Loud - Ed Sheeran
I'm Yours - Jason Mraz
Marry You - Bruno Mars
Sweet but Psycho - Ava Max
Move Your Body - Sia
The Cure - Lady Gaga
Poker Face - Lady Gaga
Bad Romance - Lady Gaga
bad guy - Billie Eilish
Uptown Funk - Mark Ronson,Bruno Mars
Someone Like You - Adele
Dance Monkey - Tones and I
Fight Song - Rachel Platten
Baby - Justin Bieber,Ludacris
Sugar - Maroon 5
Moves Like Jagger - Maroon 5,Christina Aguilera
Despacito - Luis Fonsi,Daddy Yankee
See You Again (feat. Charlie Puth) - Wiz Khalifa,Charlie Puth
Reality - Lost Frequencies,Janieck Devy
Nothin' on You - B.o.B,Bruno Mars
All Falls Down - Alan Walker,Noah Cyrus,Digital Farm Animals,Juliander
The Spectre - Alan Walker
Señorita - Shawn Mendes,Camila Cabello
Sold Out - Hawk Nelson
Dancing With Your Ghost - Sasha Sloan
Let Me Down Slowly - Alec Benjamin,Alessia Cara
Lost Control - Alan Walker,Sorana
Demons - Imagine Dragons
Diamonds - Rihanna
That Girl - Olly Murs
Faded - Alan Walker,Iselin Solheim
Ferrari - Bebe Rexha
Wild - Monogem
I Want You To Know - Pegato,Hella
Love You Like a Love Song - Selena Gomez & The Scene
As Long As You Love Me - Justin Bieber,Big Sean
MELANCHOLY - White Cherry
Lendo Calendo - 水冰月
Salt - Ava Max
还有多少个十年 - 沈宁
Alcastar - 69
太空旋律 - 十三叔
风的季节 - Soler
17岁 - 刘德华
Astronomia - 新旭
P.L.M.P - 小猫多鱼
用力活着 - 张茜
花心 - 周华健
云与海 - 阿YueYue
爆了 - 承利
下半生 - 郭燕
Come on sexy girl - 虞姬,圈妹
Come On Sexy Girl - DJRE
手放开 - 李圣杰
我还想她 - 林俊杰
寂寞沙洲冷 - 周传雄
曾经的你 - 许巍
让泪化作相思雨 - 南合文斗
爱就一个字 - 张信哲
咱们结婚吧 - 齐晨
没有理由 - DJYU
Take Me Higher - 六少飞
温柔很容易让人沉溺其中而无法自拔 - 沁沁
预谋 - DJ
他一定很爱你 - DJ龙总
EA7为你化蝶 - 南宫辞
等一分钟 - 徐誉滕
兄弟 - 任贤齐
心太软 - 任贤齐
虞兮叹（DJ沈念） - 闻人听書_
虞兮叹 - 闻人听書_
偏偏喜欢你 - 陈百强
燕无歇 - 蒋雪儿
飞鸟 - 任贤齐
红昭愿 - 音阙诗听
光明 - 赵梓婷
光明 - 谭艳
野子 - 苏运莹
平凡之路 - 朴树
董小姐 - 宋冬野
南山南 - 马頔
星辰大海 - 黄霄雲
渐冷 - 雪二
一曲相思 - 半阳
再见只是陌生人 - 庄心妍
你的酒馆对我打了烊 - 陈雪凝
天下有情人 - 周华健,齐豫
怒放的生命 - 汪峰
殇雪 - 云菲菲
野狼disco - 宝石Gem
像风一样自由 - 许巍
单车 - 陈奕迅
倔强 - 五月天
追梦人 - 凤飞飞
去大理 - 郝云
芒种 - 音阙诗听,赵方婧
一生回味一面 - DJ小鱼儿
你的万水千山（DJ片段） - 阿亮AI
探清水河 - 张云雷
思念成霜 - 阿悠悠
云与海 - 阿YueYue
醉红尘 - 魏新雨
不过人间 - 艺星,海来阿木
旧梦一场 - 阿悠悠
百花香 - 魏新雨,网友顽童
人为情叹 - 阿悠悠
桃花运 - 半吨兄弟
你还要我怎样 - 薛之谦
演员 - 薛之谦
人生何处不相逢 - 陈慧娴
心要让你听见 - 小阿枫
电灯胆 - 邓丽欣
漫步人生路 - 邓丽君
热血燃烧 - 郑伊健,陈小春
你怎么说 - 邓丽君
醒不来的梦 - 回小仙
不配怀念 - 小倩
爱情堡垒 - 杨小壮
一个人挺好 - 杨小壮
执迷不悟 - 小乐哥
酒醉的蝴蝶 - 孙艺琪
难念的经 - 周华健
套马杆 - 乌兰托娅
挪威的森林 - 伍佰
理想三旬 - 陈鸿宇
被遗忘的时光 - 蔡琴
千千阙歌 - 陈慧娴
爱情好无奈 - 六哲
白月光与朱砂痣 - 大籽
素颜 - 许嵩,何曼婷
有何不可 - 许嵩
单身情歌 - 林志炫
半壶纱 - 刘珂矣
忘川彼岸 - 零一九零贰
夜空中最亮的星 - 逃跑计划
辞九门回忆 - DJ阿超,T.G.A.阿航
爱上你是一个错 - 杨培安
爱情错觉 - 半吨兄弟
错错错 - 六哲,陈娟儿
迷失幻境 - IN-K,王忻辰
鬼迷心窍 - 李宗盛
游山恋 - 海伦
灰色轨迹 - BEYOND
感谢你曾来过 - Ayo97,周思涵
余情未了 - 魏新雨
风筝误 - 刘珂矣
一生所爱 - 卢冠廷,莫文蔚
伤心太平洋 - 任贤齐
告白气球 - 周杰伦
你是人间四月天 - 唐子臣
笑纳 - 花僮
我在景德镇等你 - 徐千雅
荣耀 - 王晓天
广东爱情故事 - 广东雨神
爱笑的眼睛 - 林俊杰
一千年以后 - 林俊杰
背对背拥抱 - 林俊杰
江南 - 林俊杰
我多喜欢你，你会知道 - 王俊琪
一笑倾城 - 汪苏泷
梦不落雨林 - 张艺兴
知否知否 - 胡夏,郁可唯
月夜 - 双笙 (陈元汐),妖扬
时光话 - 赵露思
海底旋律 - 9妹
后会无期 - 汪苏泷,徐良
爱情主演 - 寐加岛
