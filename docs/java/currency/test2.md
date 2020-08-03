## 四、volatile、锁的内存语义

### 4.1 volatile的内存语义

当声明共享变量为volatile后，对这个变量的读/写会很特别。一个volatile变量的单个读/写操作，与一个普通变量的读/写操作都是使用同一个锁来同步，它们之间的执行效果相同。

锁的happens-before规则保证释放锁和获取锁的两个线程之间的内存可见性，这意味着对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。

锁的语义决定了临界区代码的执行具有原子性。这意味着，即使是64位的long型和double型变量，只要是volatile变量，对该变量的读/写就具有原子性。如果是多个volatile操作或类似于volatile++这种复合操作，这些操作整体上不具有原子性。

简而言之，一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：

　　1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。

　　2）禁止进行指令重排序。

*   可见性。对一个volatiole变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。
*   有序性。volatile关键字能禁止指令重排序，所以volatile能在一定程度上保证有序性。

volatile关键字禁止指令重排序有两层意思：

1）当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面的操作肯定还没有进行；

2）在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行。



可能上面说的比较绕，举个简单的例子：

```java
//x、y为非volatile变量 //flag为volatile变量
x = 2;        //语句1
y = 0;        //语句2
flag = true;  //语句3
x = 4;         //语句4
y = -1;       //语句5
```

由于flag变量为volatile变量，那么在进行指令重排序的过程的时候，不会将语句3放到语句1、语句2前面，也不会讲语句3放到语句4、语句5后面。但是要注意语句1和语句2的顺序、语句4和语句5的顺序是不作任何保证的。

*   原子性。对任意单个volatile变量的读、写具有原子性，但类似于volatile++这种复合操作不具有原子性。

volatile写的内存语义：当写一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量值刷新到主内存。

volatile读的内存语义：当读一个volatile变量时，JMM会把该线程对应的本地内存置位无效。线程接下来将从主内存中读取共享变量。（强制从主内存读取共享变量，把本地内存与主内存的共享变量的值变成一致）。

volatile写和读的内存语义总结总结：

*   线程A写一个volatile变量，实质上是线程A向接下来将要读这个volatile变量的某个线程发出了（其对变量所做修改的）消息。
*   线程B读一个volatile变量，实质上是线程B接收了之前某个线程发出的消息。
*   线程A写一个volatile变量，随后线程B读这个volatile变量，这个过程实质上是线程A通过主内存向线程B发送消息。(隐式通信)

### 4.2 volatile内存语义的实现

前面提到过编译器重排序和处理器重排序。为了实现volatile内存语义，JMM分别限制了这两种类型的重排序类型。

*   当第二个操作是volatile写时，不管第一个操作是什么，都不能重排序。这个规则确保volatile写之前的操作不会被编译器重排序到volatile写之后。
*   当第一个操作是volatile读时，不管第二个操作是什么，都不能重排序。这个规则确保volatile读之后的操作不会被编译器重排序到volatile读之前。
*   当第一个操作是volatile写时，第二个操作是volatile读时，不能重排序。

为了实现volatile的内存语义，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。对于编译器来说，发现一个最优布置来最小化插入屏障的总数几乎不可能。为此，JMM采取保守策略。下面是基于保守策略的JMM内存屏障插入策略：

*   在每个volatile写操作的前面插入一个StoreStore屏障。
*   在每个volatile写操作的后面插入一个StoreLoad屏障。
*   在每个volatile读操作的后面插入一个LoadLoad屏障。
*   在每个volatile读操作的后面插入一个LoadStore屏障。

上述内存屏障插入策略非常保守，但它可以保证在任意处理器平台，任意的程序中都能得到正确的volatile内存语义。

下面是保守策略下，volatile写插入内存屏障后生成的指令序列示意图：

![](https://images2018.cnblogs.com/blog/1332556/201806/1332556-20180627155958330-1026129500.png)

上图中的StoreStore屏障可以保证在volatile写之前，其前面的所有普通写操作已经对任意处理器可见了。这是因为StoreStore屏障将保障上面所有的普通写在volatile写之前刷新到主内存。

这里比较有意思的是volatile写后面的StoreLoad屏障。这个屏障的作用是避免volatile写与后面可能有的volatile读/写操作重排序。因为编译器常常无法准确判断在一个volatile写的后面，是否需要插入一个StoreLoad屏障（比如，一个volatile写之后方法立即return）。为了保证能正确实现volatile的内存语义，JMM在这里采取了保守策略：在每个volatile写的后面或在每个volatile读的前面插入一个StoreLoad屏障。从整体执行效率的角度考虑，JMM选择了在每个volatile写的后面插入一个StoreLoad屏障。因为volatile写-读内存语义的常见使用模式是：一个写线程写volatile变量，多个读线程读同一个volatile变量。当读线程的数量大大超过写线程时，选择在volatile写之后插入StoreLoad屏障将带来可观的执行效率的提升。从这里我们可以看到JMM在实现上的一个特点：首先确保正确性，然后再去追求执行效率。下面是在保守策略下，volatile读插入内存屏障后生成的指令序列示意图：

![](https://images2018.cnblogs.com/blog/1332556/201806/1332556-20180627155942083-844958043.png)

上图中的LoadLoad屏障用来禁止处理器把上面的volatile读与下面的普通读重排序。LoadStore屏障用来禁止处理器把上面的volatile读与下面的普通写重排序。

上述volatile写和volatile读的内存屏障插入策略非常保守。在实际执行时，只要不改变volatile写-读的内存语义，编译器可以根据具体情况省略不必要的屏障。下面我们通过具体的示例代码来说明：

```java
class VolatileBarrierExample { 
    int a; 
    volatile int v1 = 1; 
    volatile int v2 = 2; 
    void readAndWrite() { 
        int i = v1;      //第一个volatile读
        int j = v2;      // 第二个volatile读
        a = i + j;      //普通写
        v1 = i + 1;     // 第一个volatile写
        v2 = j * 2;     //第二个 volatile写
    }
  	
    …//其他方法
}
```


针对readAndWrite()方法，编译器在生成字节码时可以做如下的优化：

![](https://images2018.cnblogs.com/blog/1332556/201806/1332556-20180627160549655-1808405069.png)

注意，最后的StoreLoad屏障不能省略。因为第二个volatile写之后，方法立即return。此时编译器可能无法准确断定后面是否会有volatile读或写，为了安全起见，编译器常常会在这里插入一个StoreLoad屏障。

上面的优化是针对任意处理器平台，由于不同的处理器有不同“松紧度”的处理器内存模型，内存屏障的插入还可以根据具体的处理器内存模型继续优化。以x86处理器为例，上图中除最后的StoreLoad屏障外，其它的屏障都会被省略。

为了提供一种比锁更轻量级的线程之间通信的机制，JSR-133专家组决定增强volatile的内存语义：严格限制编译器和处理器对volatile变量与普通变量的重排序，确保volatile的写-读和锁的释放-获取具有相同的内存语义。

由于volatile仅仅保证对单个volatile变量的读/写具有原子性，而锁的互斥执行的特性可以确保对整个临界区代码的执行具有原子性。在功能上，锁比volatile更强大；在可伸缩性和执行性能上，volatile更有优势。

当一个变量被定义为volatile之后，就可以保证此变量对所有线程的可见性，即当一个线程修改了此变量的值的时候，变量新的值对于其他线程来说是可以立即得知的。可以理解成：对volatile变量所有的写操作都能立刻被其他线程得知。但是这并不代表基于volatile变量的运算在并发下是安全的，因为volatile只能保证内存可见性，却没有保证对变量操作的原子性。比如下面的代码：

```java
/ ** 
  * 发起20个线程，每个线程对race变量进行10000次自增操作，如果代码能够正确并发，
  * 则最终race的结果应为200000，但实际的运行结果却小于200000。
  * 
  * @author Colin Wang
  */
public class Test { 
    public static volatile int race = 0;
    public static void increase() {
        race++;
    } 
    private static final int THREADS_COUNT = 20;
    public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT]; 
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override public void run() { for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });

            threads[i].start();
    	} 
        while (Thread.activeCount() > 1)
        Thread.yield();
    	System.out.println(race);
	}
}
```


按道理来说结果是10000，但是运行下很可能是个小于10000的值。有人可能会说volatile不是保证了可见性啊，一个线程对race的修改，另外一个线程应该立刻看到啊！可是这里的操作race++是个复合操作啊，包括读取race的值，对其自增，然后再写回主存。

假设线程A，读取了race的值为10，这时候被阻塞了，因为没有对变量进行修改，触发不了volatile规则。

线程B此时也读读race的值，主存里race的值依旧为10，做自增，然后立刻就被写回主存了，为11。

此时又轮到线程A执行，由于工作内存里保存的是10，所以继续做自增，再写回主存，11又被写了一遍。所以虽然两个线程执行了两次increase()，结果却只加了一次。

有人说，volatile不是会使缓存行无效的吗？但是这里线程A读取到线程B也进行操作之前，并没有修改inc值，所以线程B读取的时候，还是读的10。

又有人说，线程B将11写回主存，不会把线程A的缓存行设为无效吗？但是线程A的读取操作已经做过了啊，只有在做读取操作时，发现自己缓存行无效，才会去读主存的值，所以这里线程A只能继续做自增了。

综上所述，在这种复合操作的情景下，原子性的功能是维持不了了。但是volatile在上面那种设置flag值的例子里，由于对flag的读/写操作都是单步的，所以还是能保证原子性的。

要想保证原子性，只能借助于synchronized,Lock以及并发包下的atomic的原子操作类了，即对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），减法操作（减一个数）进行了封装，保证这些操作是原子性操作。

[Java 理论与实践: 正确使用 Volatile 变量](http://www.ibm.com/developerworks/cn/java/j-jtp06197.html) 总结了volatile关键的使用场景，

只能在有限的一些情形下使用 volatile 变量替代锁。要使 volatile 变量提供理想的线程安全，必须同时满足下面两个条件：

*   对变量的写操作不依赖于当前值。
*   该变量没有包含在具有其他变量的不变式中。

实际上，这些条件表明，可以被写入 volatile 变量的这些有效值独立于任何程序的状态，包括变量的当前状态。

第一个条件的限制使 volatile 变量不能用作线程安全计数器。虽然增量操作（`x++`）看上去类似一个单独操作，实际上它是一个由读取－修改－写入操作序列组成的组合操作，必须以原子方式执行，而 volatile 不能提供必须的原子特性。实现正确的操作需要使`x` 的值在操作期间保持不变，而 volatile 变量无法实现这点。（然而，如果将值调整为只从单个线程写入，那么可以忽略第一个条件。） 

 volatile一个使用场景是状态位；还有只有一个线程写，其余线程读的场景

### 4.3 锁的内存语义

锁可以让临界区互斥执行。锁的释放-获取的内存语义与volatile变量写-读的内存语义很像。

当线程释放锁时，JMM会把该线程对应的本地内存中的共享变量刷新到主内存中。

当线程获取锁时，JMM会把该线程对应的本地内存置位无效，从而使得被监视器保护的临界区代码必须从主内存中读取共享变量。

不难发现：锁释放与volatile写有相同的内存语音；锁获取与volatile读有相同的内存语义。 

下面对锁释放和锁获取的内存语义做个总结。

*   线程A释放一个锁，实质上是线程A向接下来将要获取这个锁的某个线程发出了（线程A对共享变量所做修改的）消息。
*   线程B获取一个锁，实质上是线程B接收了之前某个线程发出的（在释放这个锁之前对共享变量所做修改）的消息。
*   线程A释放锁，随后线程B获取这个锁，这个过程实质上是线程A通过主内存向线程B发送消息。

### 4.4 final域的内存语义

与前面介绍的锁和volatile想比，对final域的读和写更像是普通的变量访问。

对于final域，编译器和处理器要遵循两个重排序规则：

1.在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。

2.初次读一个包含final域的对象的应用，与随后初次读这个final域，这两个操作之间不能重排序

下面通过一个示例来分别说明这两个规则：

```java
public class FinalTest { int i;//普通变量
    final int j; 
    static FinalExample obj; 
    
    public FinalExample(){
        i = 1;
        j = 2;
    } 
   
    public static void writer(){
        obj = new FinalExample();
    } 
                        
    public static void reader(){
        FinalExample object = obj;//读对象引用
        int a = object.i; int b = object.j;
    }
}
```

这里假设一个线程A执行writer()方法，随后另一个线程B执行reader()方法。下面我们通过这两个线程的交互来说明这两个规则。

写final域的重排序规则禁止把final域的写重排序到构造函数之外。这个规则的实现包含下面两个方面。

1）JMM禁止编译器把final域的写重排序到构造函数之外。

2）编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障。这个屏障禁止处理器把final域的写重排序到构造函数之外。

现在让我们分析writer方法，writer方法只包含一行代码obj = new FinalTest();这行代码包含两个步骤：

1）构造一个FinalTest类型的对象

2）把这个对象的引用赋值给obj

假设线程B的读对象引用与读对象的成员域之间没有重排序，下图是一种可能的执行时序

![](https://images2018.cnblogs.com/blog/1332556/201807/1332556-20180702161158925-1636540001.png)

在上图中，写普通域的操作被编译器重排序到了构造函数之外，读线程B错误的读取到了普通变量i初始化之前的值。而写final域的操作被写final域重排序的规则限定在了构造函数之内，读线程B正确的读取到了final变量初始化之后的值。

写final域的重排序规则可以确保：在对象引用为任意线程可见之前，对象的final域已经被初始化了，而普通变量不具有这个保证。以上图为例，读线程B看到对象obj的时候，很可能obj对象还没有构造完成（对普通域i的写操作被重排序到构造函数外，此时初始值1还没有写入普通域i）

读final域的重排序规则是：在一个线程中，初次读对象的引用与初次读这个对象包含的final域，JMM禁止重排序这两个操作(该规则仅仅针对处理器)。编译器会在读final域的操作前面加一个LoadLoad屏障。

初次读对象引用与初次读该对象包含的final域，这两个操作之间存在间接依赖关系。由于编译器遵守间接依赖关系，因此编译器不会重排序这两个操作。大多数处理器也会遵守间接依赖，也不会重排序这两个操作。但有少数处理器允许对存在间接依赖关系的操作做重排序（比如alpha处理器），这个规则就是专门用来针对这种处理器的。

上面的例子中，reader方法包含三个操作

1）初次读引用变量obj

2）初次读引用变量指向对象的普通域

3）初次读引用变量指向对象的final域

现在假设写线程A没有发生任何重排序，同时程序在不遵守间接依赖的处理器上执行，下图是一种可能的执行时序：

![](https://images2018.cnblogs.com/blog/1332556/201807/1332556-20180702162017295-865800268.png)

在上图中，读对象的普通域操作被处理器重排序到读对象引用之前。在读普通域时，该域还没有被写线程写入，这是一个错误的读取操作，而读final域的重排序规则会把读对象final域的操作“限定”在读对象引用之后，此时该final域已经被A线程初始化过了，这是一个正确的读取操作。

读final域的重排序规则可以确保：在读一个对象的final域之前，一定会先读包含这个final域的对象的引用。在这个示例程序中，如果该引用不为null，那么引用对象的final域一定已经被A线程初始化过了。

final域为引用类型，上面我们看到的final域是基础的数据类型，如果final域是引用类型呢？


```java
public class FinalReferenceTest { 
    final int[] arrs; //final引用
    
    static FinalReferenceTest obj; 
    
    public FinalReferenceTest(){
        arrs = new int[1];//1
        arrs[0] = 1;//2
    } 
    
    public static void write0(){//A线程
    	obj = new FinalReferenceTest();//3
    }
    
    public static void write1(){//线程B
    	obj.arrs[0] = 2;//4
    } 
    
    public static void reader(){//C线程
        if(obj!=null){//5
           int temp =obj.arrs[0];//6
        }
    }
}
```



JMM可以确保读线程C至少能看到写线程A在构造函数中对final引用对象的成员域的写入。即C至少能看到数组下标0的值为1。而写线程B对数组元素的写入，读线程C可能看得到，也可能看不到。JMM不保证线程B的写入对读线程C可见，因为写线程B和读线程C之间存在数据竞争，此时的执行结果不可预知。

如果想要确保读线程C看到写线程B对数组元素的写入，写线程B和读线程C之间需要使用同步原语（lock或volatile）来确保内存可见性。

前面我们提到过，写final域的重排序规则可以确保：在引用变量为任意线程可见之前，该引用变量指向的对象的final域已经在构造函数中被正确初始化过了。其实，要得到这个效果，还需要一个保证：在构造函数内部，不能让这个被构造对象的引用为其他线程所见，也就是对象引用不能在构造函数中“逸出”。

```java
public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;
    
    public FinalReferenceEscapeExample () {
　　　　i = 1; // 1写final域
　　　　obj = this; // 2 this引用在此"逸出"
　　 }
　　
    public static void writer() {
        new FinalReferenceEscapeExample ();
　　 }
    
    public static void reader() {
        if (obj != null) { // 3
　　　　　　int temp = obj.i; // 4
　　　　 }
　　 }
}
```

假设一个线程A执行writer()方法，另一个线程B执行reader()方法。这里的操作2使得对象还未完成构造前就为线程B可见。即使这里的操作2是构造函数的最后一步，且在程序中操作2排在操作1后面，执行read()方法的线程仍然可能无法看到final域被初始化后的值，因为这里的操作1和操作2之间可能被重排序。

JSR-133为什么要增强final的语义：

通过为final域增加写和读重排序规则，可以为Java程序员提供初始化安全保证：只要对象是正确构造的（被构造对象的引用在构造函数中没有“逸出”），那么不需要使用同步（指lock和volatile的使用）就可以保证任意线程都能看到这个final域在构造函数中被初始化之后的值。

## 五、JMM是如何处理并发过程中的三大特性

 JMM是围绕这在并发过程中如何处理原子性、可见性和有序性这3个特性来建立的。

*   原子性：

    Java中，对基本数据类型的读取和赋值操作是原子性操作，所谓原子性操作就是指这些操作是不可中断的，要做一定做完，要么就没有执行。比如：

    i = 2;j = i;i++;i = i + 1；

    上面4个操作中，i=2是读取操作，必定是原子性操作，j=i你以为是原子性操作，其实吧，分为两步，一是读取i的值，然后再赋值给j,这就是2步操作了，称不上原子操作，i++和i = i + 1其实是等效的，读取i的值，加1，再写回主存，那就是3步操作了。所以上面的举例中，最后的值可能出现多种情况，就是因为满足不了原子性。
    
    JMM只能保证对单个volatile变量的读/写具有原子性，但类似于volatile++这种符合操作不具有原子性，这时候就必须借助于synchronized和Lock来保证整块代码的原子性了。线程在释放锁之前，必然会把i的值刷回到主存的。

*   可见性：可见性指当一个线程修改了共享变量的值，其他线程能够立即得知这个修改。Java内存模型是通过在变量修改后将新值同步回主内存，在变量读取前从主内存刷新变量值这种依赖主内存作为传递媒介的方式来实现可见性。
  
*   无论是普通变量还是volatile变量，它们的区别是：volatile的特殊规则保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。因为，可以说volatile保证了多线程操作时变量的可见性，而普通变量不能保证这一点。

*   除了volatile之外，java中还有2个关键字能实现可见性，即synchronized和final（final修饰的变量，线程安全级别最高）。同步块的可见性是由“对一个变量执行unlock操作之前，必须先把此变量同步回主内存中（执行store，write操作）”这条规则获得；而final关键字的可见性是指：被final修饰的字段在构造器中一旦初始化完成，并且构造器没有把“this”的引用传递出去（this引用逃逸是一件很危险的事，其他线程有可能通过这个引用访问到“初始化了一半”的对象），那么在其他线程中就能看到final字段的值。

*   有序性：JMM的有序性在讲解volatile时详细的讨论过，java程序中天然的有序性可以总结为一句话：如果在本线程内观察，所有操作都是有序的；如果在一个线程中观察另一个线程，所有操作都是无序的。前半句是指“线程内表现为串行的语义”，后半句指的是“指令重排”现象和“工作内存与主内存同步延迟”现象。

*   前半句可以用JMM规定的as-if-serial语义来解决，后半句可以用JMM规定的happens-before原则来解决。Java语义提供了volatile和synchronized两个关键字来保证线程之间操作的有序性，volatile关键字本身就包含了禁止指令重排的语义，而synchronized则是由“一个变量在同一个时刻只允许一条线程对其进行lock操作”这条规则获取的。这个规则决定了持有同一个锁的两个同步块只能串行的进入。

## 参考链接：

[https://blog.csdn.net/javazejian/article/details/72772461#volatile%E7%A6%81%E6%AD%A2%E9%87%8D%E6%8E%92%E4%BC%98%E5%8C%96](https://blog.csdn.net/javazejian/article/details/72772461#volatile%E7%A6%81%E6%AD%A2%E9%87%8D%E6%8E%92%E4%BC%98%E5%8C%96)

[https://www.cnblogs.com/_popc/p/6096517.html](https://www.cnblogs.com/_popc/p/6096517.html)

[https://blog.csdn.net/liu_dong_liang/article/details/80391040 ](https://blog.csdn.net/liu_dong_liang/article/details/80391040)

[https://www.jb51.net/article/76006.htm](https://www.jb51.net/article/76006.htm)

[https://blog.csdn.net/x_i_y_u_e/article/details/50728602](https://blog.csdn.net/x_i_y_u_e/article/details/50728602)

[https://blog.csdn.net/FYGu18/article/details/79001688](https://blog.csdn.net/FYGu18/article/details/79001688)

[https://blog.csdn.net/soongp/article/details/80292796](https://blog.csdn.net/soongp/article/details/80292796)
