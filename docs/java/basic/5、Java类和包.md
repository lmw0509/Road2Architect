## Java中的包概念

### 包的作用

1、把功能相似或相关的类或接口组织在同一个包中，方便类的查找和使用。

2、如同文件夹一样，包也采用了树形目录的存储方式。同一个包中的类名字是不同的，不同的包中的类的名字是可以相同的，当同时调用两个不同包中相同类名的类时，应该加上包名加以区别。因此，包可以避免名字冲突。

3、包也限定了访问权限，拥有包访问权限的类才能访问某个包中的类。

Java 使用包（package）这种机制是为了防止命名冲突，访问控制，提供搜索和定位类（class）、接口、枚举（enumerations）和注释（annotation）等。

```java
// 包语句的语法格式为：
package  pkg1[．pkg2[．pkg3…]];

//例如,一个Something.java 文件它的内容
package  net.java.util; 

public  class  Something{
    ...
}
```

那么它的路径应该是 **net/java/util/Something.java** 这样保存的。 package(包) 的作用是把不同的 java 程序分类保存，更方便的被其他 java 程序调用。

一个包（package）可以定义为一组相互联系的类型（类、接口、枚举和注释），为这些类型提供访问保护和命名空间管理的功能。

以下是一些 Java 中的包：

*   **java.lang** 包基础的类
*   **java.io** 包含输入输出功能的函数

开发者可以自己把一组类和接口等打包，并定义自己的包。而且在实际开发中这样做是值得提倡的，当你自己完成类的实现之后，将相关的类分组，可以让其他的编程者更容易地确定哪些类、接口、枚举和注释等是相关的。

由于包创建了新的命名空间（namespace），所以不会跟其他包中的任何名字产生命名冲突。使用包这种机制，更容易实现访问控制，并且让定位相关类更加简单。

### package 的目录结构

类放在包中会有两种主要的结果：

*   包名成为类名的一部分，正如我们前面讨论的一样。
*   包名必须与相应的字节码所在的目录结构相吻合。

类目录的绝对路径叫做 **class path**。设置在系统变量 **CLASSPATH** 中。**编译器和 java 虚拟机通过将 package 名字加到 class path 后来构造 .class 文件的路径。**

一个 class path 可能会包含好几个路径，多路径应该用分隔符分开。默认情况下，编译器和 JVM 查找当前目录。JAR 文件按包含 Java 平台相关的类，所以他们的目录默认放在了 class path 中。



## 常用jar包

### java软件包的类型

软件包的类型有内置的软件包和用户定义的软件包内置软件包
这些软件包由大量的类组成，这些类是Java API的一部分。一些常用的内置软件包有：

1）java.lang：包含语言支持类（例如分类，用于定义基本数据类型，数学运算）。该软件包会自动导入。

2） java.io：包含分类以支持输入/输出操作。

3） java.util：包含实现像链接列表，字典和支持等数据结构的实用类; 用于日期/时间操作。

4） java.applet：包含用于创建Applets的类。

5） java.awt：包含用于实现图形用户界面组件的类（如按钮，菜单等）。

6） java.net：包含支持网络操作的类。

### dt.jar
> SUN对于dt.jar的定义：Also includes dt.jar, the DesignTime archive of BeanInfo files that tell interactive development environments (IDE's) how to display the Java components and how to let the developer customize them for the application。

中文翻译过来就是：dt.jar是BeanInfo文件的DesignTime归档，BeanInfo文件用来告诉集成开发环境（IDE）如何显示Java组件还有如何让开发人员根据应用程序自定义它们。这段文字中提到了几个关键字：DesignTime,BeanInfo,IDE，Java components。其实dt.jar就是DesignTime Archive的缩写。那么何为DesignTime。

何为DesignTime?翻译过来就是设计时。其实了解JavaBean的人都知道design time和runtime（运行时）这两个术语的含义。**设计时（DesignTIme）是指在开发环境中通过添加控件，设置控件或窗体属性等方法，建立应用程序的时间。与此相对应的运行时（RunTIme）是指可以象用户那样与应用程序交互作用的时间。**那么现在再理解一下上面的翻译，其实dt.jar包含了swing控件中的BeanInfo，而IDE的GUI Designer需要这些信息。那让我们看一下dt.jar中到底有什么？下面是一张dt.jar下面的内容截图：

![image](http://www.blogjava.net/images/blogjava_net/landon/dt-jar.jpg)从上面的截图可以看出，dt.jar中全部是Swing组件的BeanInfo。那么到底

什么是BeanInfo呢？

何为BeanInfo?JavaBean和BeanInfo有很大的关系。Sun所制定的JavaBean规范，很大程度上是为IDE准备的——它让IDE能够以可视化的方式设置JavaBean的属性。如果在IDE中开发一个可视化应用程序，我们需要通过属性设置的方式对组成应用的各种组件进行定制，IDE通过属性编辑器让开发人员使用可视化的方式设置组件的属性。

一般的IDE都支持JavaBean规范所定义的属性编辑器，当组件开发商发布一个组件时，它往往将组件对应的属性编辑器捆绑发行，这样开发者就可以在IDE环境下方便地利用属性编辑器对组件进行定制工作。JavaBean规范通过java.beans.PropertyEditor定义了设置JavaBean属性的方法，通过BeanInfo描述了JavaBean哪些属性是可定制的，此外还描述了可定制属性与PropertyEditor的对应关系。

BeanInfo与JavaBean之间的对应关系，通过两者之间规范的命名确立：对应JavaBean的BeanInfo采用如下的命名规范：BeanInfo。当JavaBean连同其属性编辑器相同的组件注册到IDE中后，当在开发界面中对JavaBean进行定制时，IDE就会根据JavaBean规范找到对应的BeanInfo，再根据BeanInfo中的描述信息找到JavaBean属性描述（是否开放、使用哪个属性编辑器），进而为JavaBean生成特定开发编辑界面。

dt.jar里面主要是swing组件的BeanInfo。IDE根据这些BeanInfo显示这些组件以及开发人员如何定制他们。

### rt.jar
rt.jar是runtime的归档。**Java基础类库，也就是Java doc里面看到的所有的类的class文件。**

![image](https://img-blog.csdnimg.cn/20181115130130739.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2Z1aGFuZ2hhbmc=,size_16,color_FFFFFF,t_70)

rt.jar 默认就在Root Classloader的加载路径里面的，而在Claspath配置该变量是不需要的；同时jre/lib目录下的其他jar:jce.jar、jsse.jar、charsets.jar、resources.jar都在Root Classloader中。



## *.java文件的奥秘

### *.java文件简介

.java文件你可以认为只是一个文本文件， 这个文件即是用java语言写成的程序，或者说任务的代码块。

.class文件本质上是一种二进制文件， 它一般是由.java文件通过 javac这个命令（jdk本身提供的工具）生成的一个文件， 而这个文件可以由jvm(java虚拟机)装载（类装载），然后进java解释执行， 这也就是运行你的程序。

你也可以这样比较一下：
.java与 .c , .cpp, .asm等等文件，本质 上一样的， 只是用一种 语言来描述你要怎么去完成一件事（一个任务）， 而这种语言 计算机本身 是没有办法知道是什么含义的， 它面向的只是程序员本身， 程序员可以通过 语言本身（语法） 来描述或组织这个任务，这也就 是所谓的编程。

最后你当然是需要计算机按照你的意图来运行你的程序， 这时候就先得有一个翻译（编译， 汇编， 链接等等复杂的过程）把它变成机器可理解的指令（这就是大家说的机器语言，机器语言本身也是一种编程语言，只是程序很难写，很难读懂，基本上没有办法维护）。


这里的.class文件在计算的体系结构中本质上对应的是一种机器语言（而这里的机器叫作JVM），所以JVM本身是可以直接运行这里的.class文件。所以 你可以进一步地认为，.java与.class与其它的编程语法一样，它们都是程序员用来描述自己的任务的一种语言，只是它们面向的对象不一样，而计算机本身只能识别它自已定义的那些指令什么的（再次强调，这里的计算机本身没有那么严格的定义）

> In short：
> 
> .java是Java的源文件后缀，里面存放程序员编写的功能代码。
> 
> .class文件是字节码文件，由.java源文件通过javac命令编译后生成的文件。是可以运行在任何支持Java虚拟机的硬件平台和操作系统上的二进制文件。
> 
> .class文件并不本地的可执行程序。Java虚拟机就是去运行.class文件从而实现程序的运行。




### 为什么一个java源文件中只能有一个public类？

在java编程思想（第四版）一书中有这样3段话（6.4 类的访问权限）：

> 　　1.每个编译单元（文件）都只能有一个public类，这表示，每个编译单元都有单一的公共接口，用public类来表现。该接口可以按要求包含众多的支持包访问权限的类。如果在某个编译单元内有一个以上的public类，编译器就会给出错误信息。
> 
> 　　2.public类的名称必须完全与含有该编译单元的文件名相同，包含大小写。如果不匹配，同样将得到编译错误。
> 
> 　　3.虽然不是很常用，但编译单元内完全不带public类也是可能的。在这种情况下，可以随意对文件命名。

总结相关的几个问题：

1、一个”.java”源文件中是否可以包括多个类（不是内部类）？有什么限制？

>   答：可以有多个类，但只能有一个public的类，并且public的类名必须与文件名相一致。

2、为什么一个文件中只能有一个public的类

>   答：编译器在编译时，针对一个java源代码文件（也称为“编译单元”）只会接受一个public类。否则报错。

3、在java文件中是否可以没有public类

>   答：public类不是必须的，java文件中可以没有public类。

4、为什么这个public的类的类名必须和文件名相同

>   答： 是为了方便虚拟机在相应的路径中找到相应的类所对应的字节码文件。

### Main方法

主函数：是一个特殊的函数，作为程序的入口，可以被JVM调用

主函数的定义：
> public：代表着该函数访问权限是最大的

> static：代表主函数随着类的加载就已经存在了

> void：主函数没有具体的返回值

> main：不是关键字，但是一个特殊的单词，能够被JVM识别

> （String[] args）：函数的参数，参数类型是一个数组，该数组中的元素师字符串，字符串数组。main(String[] args) 字符串数组的 此时空数组的长度是0，但也可以在 运行的时候向其中传入参数。

主函数是固定格式的，主函数可以被重载，但是JVM只识别main（String[] args），其他都是作为一般函数。这里面的args知识数组变量可以更改，其他都不能更改。

一个java文件中可以包含很多个类，每个类中有且仅有一个主函数，但是每个java文件中可以包含多个主函数，在运行时，需要指定JVM入口是哪个。例如一个类的主函数可以调用另一个类的主函数。不一定会使用public类的主函数。

### 外部类的访问权限

外部类只能用public和default修饰。

为什么要对外部类或类做修饰呢？

> 1.存在包概念：public 和 default 能区分这个外部类能对不同包作一个划分（default修饰的类，其他包中引入不了这个类，public修饰的类才能被import）   
> 
> 2.protected是包内可见并且子类可见，但是当一个外部类想要继承一个protected修饰的非同包类时，压根找不到这个类，更别提几层了
> 
> 3.private修饰的外部类，其他任何外部类都无法导入它。


```java
//Java中的文件名要和public修饰的类名相同，否则会报错
//如果没有public修饰的类，则文件可以随意命名
public class Java中的类文件 {

}

//非公共开类的访问权限默认是包访问权限，不能用private和protected
//一个外部类的访问权限只有两种，一种是包内可见，一种是包外可见。
//如果用private修饰，其他类根本无法看到这个类，也就没有意义了。
//如果用protected，虽然也是包内可见，但是如果有子类想要继承该类但是不同包时，
//压根找不到这个类，也不可能继承它了，所以干脆用default代替。
class A{

}
```

### Java包的命名规则

> **以 java.* 开头的是Java的核心包**，所有程序都会使用这些包中的类；

> **以 javax.* 开头的是扩展包**，x 是 extension 的意思，也就是扩展。虽然 javax.* 是对 java.* 的优化和扩展，但是由于 javax.* 使用的越来越多，很多程序都依赖于 javax.*，所以 javax.* 也是核心的一部分了，也随JDK一起发布。

> **以 org.* 开头的是各个机构或组织发布的包，因为这些组织很有影响力，它们的代码质量很高，所以也将它们开发的部分常用的类随JDK一起发布。**

> 在包的命名方面，为了防止重名，有一个惯例：大家都以自己域名的倒写形式作为开头来为自己开发的包命名，例如百度发布的包会以 com.baidu.* 开头，w3c组织发布的包会以 org.w3c.* 开头，微学苑发布的包会以 net.weixueyuan.* 开头……

> **组织机构的域名后缀一般为 org，公司的域名后缀一般为 com**，可以认为 org.* 开头的包为非盈利组织机构发布的包，它们一般是开源的，可以免费使用在自己的产品中，不用考虑侵权问题，而以 com.* 开头的包往往由盈利性的公司发布，可能会有版权问题，使用时要注意。



## 参考文章

https://www.cnblogs.com/ryanzheng/p/8465701.html
https://blog.csdn.net/fuhanghang/article/details/84102404
https://www.runoob.com/java/java-package.html
https://www.breakyizhan.com/java/4260.html
https://blog.csdn.net/qq_36626914/article/details/80627454