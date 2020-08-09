## 导读

线程池相关的知识点是面试中非常高频的问题，掌握线程及线程池相关的知识点也是程序员向高段位进阶的必由之路。由于线程池涉及**线程、并发、编程语言内存模型**等多方面的知识，历来也不是一块特别好掌握的内容。因此，小码哥决定好好梳理下这方面的知识，希望能够对你有所帮助。在本文中，作者将以JAVA语言中的线程池设计为基础，从原理分析及代码实践两个方面来进行梳理。

## 线程的概念

在了解线程池的相关的知识之前，我们有必要再次深入理解下线程的基本概念。在这里，也许会有很多同学质疑，线程的基本概念我们都懂，为什么还需要重复提起呢？

在回答这个问题之前，我们还是先回到实际的编程语言中来看看线程到底是什么？以JAVA为例，在JAVA中如何实现一个线程呢？

```java
public class ThreadDemo01 {
    public static void main(String args[]) {
        //通过匿名内部类的方式创建线程，并且重写其中的run方法
        new Thread() {
            public void run() {
                while (true) {
                    System.out.println("线程->" + Thread.currentThread().getName() + " 运行中！");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
```

通过上面的代码示例，我们知道在JAVA中要实现一个线程可以通过构造**Thread**类来实现。之后，通过重写**run()**方法来让线程执行我们想要让它执行的逻辑。然而，为了让线程生效，我们还需要通过调用**start()**方法来启动它。那么为什么我们重写了run()方法，但是却还需要调用start()方法呢？run()方法和start()方法有什么关系？到底那个方法才是真正代表了线程这个存在呢？

要搞清楚这个问题，需要我们明确**“线程的执行单元”**与**“线程”**是两个不同的概念。在**JAVA中通过Thread类重写的run()方法是线程的执行单元，而通过调用start()方法才是真正启动了一个线程**。这一点对后面我们理解线程池的作用会比较有用，因为只有从概念上剥离**线程的执行单元与线程本身**才能更深入的理解线程池存在的意义。

为了更加深入的说明这一点，我们可以来具体分析下上面例子中start()方法在JDK中的源码：

```java
public synchronized void start() {
    group.add(this);
    boolean started = false;
    try {
        start0();
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
        }
    }
}
```

在start()方法的源码中，最核心的部分其实就是start0()这个JNI本地方法：

```java
private native void start0();
```

也就是说在start方法中会调用**start0**这个本地方法，但是从源码上这么看又看不出start0的具体逻辑。为此，作者特地翻了下JDK的官方文档，其中关于start方法的说明如下：

> Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.

上面这句话的意思是：在开始执行这个线程的时候，JVM将会调用该线程的run方法，而实际上run方法是被本地方法start0()调用的。也就是说，在JAVA中由于语言的约定，我们需要在使用线程时重写线程中的执行单元方法来实现业务逻辑，而真正开启线程资源的则是start方法。

在不少关于JAVA线程的软文或者书籍中，经常会提到，**创建线程有两种方式：第一种是构造一个Thread；第二种是实现Runnable接口。通过上面的分析，这种说法其实是不严谨的****。**在JDK中代表线程的只有Thread类，而Runnable接口只是简单定义了一个无参数返回值的run方法。而我们知道run方法只是定义了线程的执行单元，而并非直接开启了线程资源，只有Thread方法的start()方法才可以启动一个线程。

所以，如果面试中有人问你在JAVA中实现线程的方式有哪些？应该告诉他准确答案：“**在JAVA中创建线程只有一种方式，那就是构造Thread类。而实现线程的执行单元则有两种方式，第一种是重写Thread类的run方法；第二种是实现Runnable接口的run方法，并且将Runnable实例用作构造Thread的参数**”。

接下来让我们再来回顾下线程的定义：“**线程是一种轻量级的进程，是由进程派生出来的子任务，它是程序执行的一个路径；每个线程都有自己的局部变量表、程序计数器（指向真正执行的指令指针）以及各自的生命周期**”。例如，当启动了一个JVM时，从操作系统开始就会创建一个新的JVM进程，之后JVM进程中将会派生或者创建很多线程。

线程知识涉及编程语言特性的面非常广泛，以JAVA语言为例，作者梳理了一份有关线程的知识图谱，如下：



![img](https://mmbiz.qpic.cn/mmbiz_jpg/l89kosVutomNlJzLDay9qaHmOQqUicm4NRdQKkJ2QcGI2slVBXoQjtIibao3KVJddn0TevAZBibWltXqX6DcpSxzA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

要掌握JAVA中的线程，需要我们理解线程的生命周期、Thread类提供的方法细节、线程安全问题等多方面的知识点。而其中线程安全相关的问题又涉及JVM的内存模型、线程同步及锁相关的知识。由于篇幅的关系，这里作者也只能给出一个大致的提纲，更细节的内容在后面有时间再和大家一起细化同步。

以上就是在具体讲述线程池之前有关线程知识的回顾了，接下来就让我们进入本篇文章的主题“线程池”相关的内容吧！

## 线程池原理

在上节关于线程知识的回顾中，我们知道创建一个线程Thread其实是比较耗费操作系统资源的，况且系统中可创建的线程数量也是有限的，如果创建的线程资源数量不能够很好的加以限制，反而会导致系统性能的下降。因此我们在进行多线程编程时，对线程资源的重复利将是一种非常好的程序设计习惯。

**那么我们在编程时如何才能实现线程资源的重复利用呢？答案就是使用线程池！**所谓的**线程池，**通俗的理解就是有一个池子，里面存放着已经创建好的线程资源，当有任务提交给线程池执行时，池中的某个线程就会主动执行该任务，执行完任务后该线程就会继续回到池子中等待下次任务的执行。下面我们就来看一下线程池的基本原理图，如下：



![img](https://mmbiz.qpic.cn/mmbiz_jpg/l89kosVutomNlJzLDay9qaHmOQqUicm4NlicO0ib17qSLWLHGo3cSkn1uPt6iaR2FfZqBibPIQYILSLYerpNpAMaVxQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



线程池中的线程资源是Thread类代表的，而具体的执行任务是由实现Runnable接口的线程执行单元类组成。线程的执行单元逻辑随业务的变化而有所不同，而线程则是一个公共资源，所以可以复用，这一点也是我们在前面内容中特别强调的，因为如果我们将线程的执行单元中的逻辑与线程本身混在一起理解的话就很容易产生疑惑。

**那么如何实现一个线程池呢？**一个完整的线程池应该具备如下要素：

- 任务队列：用于缓存提交的任务。
- 线程数量管理功能：一个线程池必须能够很好地管理和控制线程的数量。大致会有三个参数，创建线程池时的初始线程数量init；自动扩充时的最大线程数量max；在线程池空闲时需要释放资源但是也要维持一定数量的核心线程数量core。通过这三个基本参数维持好线程池中数量的合理范围，一般来说它们之间的关系是“**init<=core<=max**”。
- 任务拒绝策略：如果线程数量已达到上限且任务队列已满，则需要有相应的拒绝策略来通知任务的提交者。
- 线程工厂：主要用于个性化定制线程，如设置线程的名称或者将线程设置为守护线程等。
- QueueSize：任务队列主要存放提交的Runnable,但是为了防止内存溢出，需要有limit数量对其进行限制。
- Keepedalive时间：该时间主要决定线程各个重要参数自动维护的时间间隔。

通过上面对线程池组成部分及原理的分析，为了更加深刻地理解下线程池，下面我们手工实现一个线程池！UML类图如下：

![img](https://mmbiz.qpic.cn/mmbiz_jpg/l89kosVutokCG5iaiauiaia92zvfOpA0FO7xZKic0HQ3OglgttkvlePvaufBJKP5YuMwnRRXwFtCSiaVBzqsPhlergLw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



- ThreadPool（接口）：主要定义一个线程池应该具备的基本操作和方法。
- RunnableQueue（接口）：定义存放提交的线程执行单元Runnable的队列。
- ThreadFactory（接口）：定义创建线程的接口，便于个性化地定制Thread。
- DenyPolicy（接口）：拒绝策略接口，主要用于Queue中当runnable达到limit上限后所采用的拒绝策略。
- Internaltask（类）：Runnable的实现，用于线程池内部，该类通过沦陷RunnableQueue队列，不断从队列中取出任务进行执行。
- LinkedRunnableQueue（类）：队列接口的具体实现。
- BasicThreadPool（类）：线程池的核心实现类。

手工编写完线程池后，我们看看怎么使用：

```java
public class ThreadPoolTest 
    public static void main(String args[]) throws InterruptedException {
        //定义线程池，初始化线程数为2，核心线程数为4，最大线程数为6，任务队列最多允许1000个任务
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        //定义20个任务并提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
```

以上测试代码，我们通过初始化2个线程、核心线程数为4，最大为6，然后向该线程池提交20个任务，执行结果如下：

```java
thread-pool-0 is running and done.
thread-pool--1 is running and done.
thread-pool--2 is running and done.
thread-pool--3 is running and done.
thread-pool-0 is running and done.
thread-pool--1 is running and done.
thread-pool--2 is running and done.
thread-pool--3 is running and done.
thread-pool--1 is running and done.
thread-pool-0 is running and done.
thread-pool--3 is running and done.
thread-pool--2 is running and done.
thread-pool--1 is running and done.
thread-pool-0 is running and done.
thread-pool--3 is running and done.
thread-pool--2 is running and done.
thread-pool-0 is running and done.
thread-pool--1 is running and done.
thread-pool--2 is running and done.
thread-pool--3 is running and done.
```

从运行结果看，由于提交速度比较快，线程池扩容到了其核心线程的数量，总共4个线程，然后这些线程逐步完成了20个任务的执行，从而实现了线程的重复使用。

以上代码的github地址如下：

> https://github.com/manongwudi/java-thread.git。

通过手工编写线程池的目的只是为了让大家更好地理解线程池的实现原理，实际上在JDK1.5以后在"**java.util.concurrent(简称JUC)**"中已经提供了多种版本的线程池实现，所以在JAVA中使用线程池时，我们只需要选择合适的线程池类型即可，而这些线程池的实现也基本上与我们手工编写的线程池原理类似。

## Java自带线程池

在Java中通过Executor框架提供线程池支持，通过该框架我们可以创建出如下几类线程池：

![img](https://mmbiz.qpic.cn/mmbiz_jpg/l89kosVutokCG5iaiauiaia92zvfOpA0FO7xswcnQdZAibOBQUfXZg9w64AjPhWggbicgsn9178yXysfnD9E6OQ1N3og/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

按照线程池的核心实现类的不同派生，Java中共提供了5种现成的线程池。

### newSingleThreadExecutor

是单个工作线程的Executor，它的corePoolSize和maximumPoolSize被设置为1。采用的是无界队列LinkedBlockingQueue作为线程池的工作队列（队列的容量为Interger.MAX_VALUE）。由于使用了无界队列，如果请求过多会导致OOM，在并发请求量比较大的系统中，使用此线程池需要注意。

```java
public class SingleThreadExecutorDemo {

    public static void main(String args[]) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i <= 20; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + "[running done]"));
        }
    }
}
```

### newFixedThreadPool

被称为可重用固定线程数线程池。与SingleThreadExecutor一样它也使用了无界队列作为工作队列，如果没有执行方法shutdown()的话也是不会拒绝任务的。

```java
public class FixThreadPoolDemo {
    public static void main(String args[]) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i <= 100; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[runing done]");
            });
        }
    }
}
```

### newCachedThreadPool

是一个会根据需要创建新线程的线程池。它的corePoolSize被设置为0，即corePool为空；maximumPoolSize被设置为Integer.MAX_VALUE,即maximumPool是无界的，正因为如此，如果主线程提交任务的速度高于线程池中线程处理任务的速度的话，线程池就会不断创建新的线程，极端情况下就可能导致线程创建过多而耗尽CPU和内存资源。

```java
public class CacheThreadPoolDemo {
    public static void main(String args[]) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i <= 20; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "[runing done]");
            });
        }
    }
}
```

### newScheduledThreadPool

用于实现多个线程的周期性任务，它会把待调度的任务放到延迟队列DelayQueue中。与CacheThreadPool一样，它允许创建的最大线程数也是Interger.MAX_VALUE。

```java
public class ScheduledThreadPoolExecutorDemo {
    public static void main(String args[]) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        for (int i = 0; i <= 20; i++) {
            pool.schedule(() -> {
                System.out.println(Thread.currentThread().getName() + "[runing done]");
            }, 10, TimeUnit.SECONDS);
        }
    }
}
```

以上逻辑实现的是：延迟10秒后开始执行任务。

### newSingleThreadScheduledExecutor

只包含一个线程的ThreadScheduleExecutor。

```java
public class SingleThreadScheduledExecutorDemo {
    public static void main(String args[]) {
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i <= 20; i++) {
            pool.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName() + "[runing done]");
            }, 1, 1, TimeUnit.SECONDS);
        }
    }
}
```

以上逻辑实现的是：每一秒钟执行一次任务。

### newWorkStealingPool

该线程池是jdk1.8以后新增的，底层采用ForkJoinPool来实现，类似于Fork-Join框架所支持的功能。

```java
public class WorkStealingPoolDemo {
    public static void main(String args[]) {
        ExecutorService pool = Executors.newWorkStealingPool(10);
        for (int i = 0; i <= 20; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "[running done]");
            });
        }
    }
}
```

JDK自带的线程池大家可以根据场景选用，在阿里的开发手册中要求在实现线程池时明确的通过ThreadPoolExecutor去自行创建，并要求使用有界队列作为线程池的工作队列，同时对线程池允许创建的最大线程数也要限制，因为以上几个线程池都存在对资源使用没有限制的问题，所以大家还是根据实际情况来判断吧！