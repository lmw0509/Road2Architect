##  1 File类 

既能代表一个特定文件的名称，又能代表一个目录下一组文件的名称。

FilePath（文件路径）对于这个类来说是个更好的名字。

### 1.1 目录列表器

FilenameFilter，策略模式，回调，提供了代码行为的灵活性。

匿名内部类通过创建特定的，一次性的类来解决问题。此方法的一个优点就是将解决问题的特定代码隔离，聚拢于一点。但是不易阅读。

### 1.2 目录实用工具

File.list()->listFile()

### 1.3 目录检查与创建

File对象也能用来创建新的目录或尚不存在的整个目录路径。

renameTo()，把一个文件重命名（或移动）到由参数所指示的另一个完全不同的新路径（也就是另一个File对象）下面。

## 2 输入和输出

流屏蔽了实际的io设备中处理数据的细节。

很少使用单一的类来创建流对象，而是通过叠加多个流对象来提供所期望的功能（装饰器模式）。流类库让人疑惑的主要原因是：创建单一的结果流，却需要创建多个对象。

与输入有关的类都从InputStream继承，而与输出有关的所有类都应该从OutputStream继承。

### 2.1 InputStream类型

数据源：

- 字节数组
- String对象
- 文件
- 管道，从一端输入，从另一端输出
- 一个从其他种类的流组成的序列，以便我们可以将他们收集合并到一个流内
- 其他数据源，Internet连接

每一种数据源都有相应的InputStream子类。

FilterInputStream也是一种InputStream，为装饰器类提供基类，其中，装饰器类可以把属性或有用的接口与输入流连接在一起。

### 2.2 OutputStream类型

输出目标：

- 字节数组
- 文件
- 管道

FilterOutputStream同上。

## 3 添加属性和有用的接口

Java I/O类库需要多种不同功能的组合，这正是使用装饰器模式的理由所在。这也是Java I/O类库里存在filter（过滤器）类的原因所在，抽象类filter是所有装饰器的基类。装饰器必须具有和它所装饰的对象相同的接口，但它也可以扩展接口，而这种情况只发生在个别filter类中。

灵活性--复杂性

Java I/O类库操作不便的原因在于：我们必须创建多个类---核心I/O类型加上所有的装饰器，才能得到我们所希望的单个I/O对象。

FilterInputStream和FilterOutputStream是用来提供装饰器类接口以控制输入流和输出流的两个类。FilterInputStream和FilterOutputStream分别自I/O类库中的基类InputStream和OutputStream派生而来，这两个类是装饰器的必要条件，以便能够为所有正在被修饰的对象提供通用接口。

### 3.1 通过FilterInputStream从InputStream读取数据

- DataInputStream 基本数据类型和String对象
- 内部修改InputStream的行为方式

对输入进行缓冲

### 3.2 通过FilterOutputStream从OutputStream写入

## 4 Reader和Writer

InputStream和OutputStream在面向字节形式的IO中仍可以提供极有价值的功能，Reader和Writer则提供兼容Unicode和面向字符的I/O功能。

适配器类：

- InputStreamReader，InputStream转换为Reader
- OutputStreamWriter，OutputStream转换为Writer

设计Reader和Writer继承层次结构主要是为了国际化。老的I/O继承层次结构仅支持8位字节流，并且不能很好地处理16位的Unicode字符。由于Unicode用于字符国际化（Java本身的char也是16位的Unicode），所以添加Reader和Writer继承层次结构就是为了在所有的I/O操作中都支持Unicode。另外，新类库的设计使得它的操作比旧类库更快。

### 4.1 数据的来源和去处

几乎所有原始的Java I/O流类都有相应的Reader和Writer类来提供天然的Unicode操作。然而在某些场合，面向字节的InputStream和OutputStream才是正确的解决方案，特别是，java.util.zip类库就是面向字节的而不是面向字符的。因此，最明智的做法是尽量尝试使用Reader和Writer，一旦程序代码无法编译，我们就会发现自己不得不使用面向字节的类库。

### 4.2 更改流的行为

### 4.3 未发生变化的类

## 5 自我独立的类：RandomAccessFile

RandomAccessFile适用于由大小已知的记录组成的文件，所以我们可以使用seek()将记录从一处转移到另一处，然后读取或者修改记录。文件中记录的大小不一定都相同，只要我们能够确定那些记录有多大以及它们在文件中的位置即可。

## 6 I/O流的典型使用方式

### 6.1 缓冲输入文件

为了提高速度，我们希望对那个文件进行缓冲，那么我们将所产生的引用传给一个BufferedReader构造器。

### 6.2 从内存输入

### 6.3 格式化的内存输入

### 6.4 基本的文件输出