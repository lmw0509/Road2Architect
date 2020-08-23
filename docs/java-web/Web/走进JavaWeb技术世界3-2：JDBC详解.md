### 一、JDBC

JDBC（Java DataBase Connectivity）是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准，据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序，同时，JDBC也是个商标名。

### 二、为什么会出现JDBC

SUN公司提供的一种数据库访问规则、规范, 由于数据库种类较多，并且java语言使用比较广泛，sun公司就提供了一种规范，让其他的数据库提供商去实现底层的访问规则。 我们的java程序只要使用sun公司提供的jdbc驱动即可。

### 三、数据库驱动

我们安装好数据库之后，我们的应用程序也是不能直接使用数据库的，必须要通过相应的数据库驱动程序，通过驱动程序去和数据库打交道。其实也就是数据库厂商的JDBC接口实现，即对Connection等接口的实现类的jar文件。

![img](https://mmbiz.qpic.cn/mmbiz_png/YrLz7nDONjEibykemySKIEIlIicWYUDhvibVtvo4GGNIYhODFyQcspM6sMg49UlfIQib5KcWowVFQC5PiaFg7KntiaJw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 四、常用接口

**1.Driver接口**

Driver接口由数据库厂家提供，作为java开发人员，只需要使用Driver接口就可以了。在编程中要连接数据库，必须先装载特定厂商的数据库驱动程序，不同的数据库有不同的装载方法。如：

- 装载MySql驱动：Class.forName("com.mysql.jdbc.Driver");
- 装载Oracle驱动：Class.forName("oracle.jdbc.driver.OracleDriver");

**2.Connection接口**

Connection与特定数据库的连接（会话），在连接上下文中执行sql语句并返回结果。DriverManager.getConnection(url, user, password)方法建立在JDBC URL中定义的数据库Connection连接上。

- 连接MySql数据库：Connection conn = DriverManager.getConnection("jdbc:mysql://host:port/database", "user", "password");
- 连接Oracle数据库：Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@host:port:database", "user", "password");
- 连接SqlServer数据库：Connection conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://host:port; DatabaseName=database", "user", "password");

**常用方法：**

- createStatement()：创建向数据库发送sql的statement对象。
- prepareStatement(sql) ：创建向数据库发送预编译sql的PrepareSatement对象。
- prepareCall(sql)：创建执行存储过程的callableStatement对象。
- setAutoCommit(boolean autoCommit)：设置事务是否自动提交。
- commit() ：在链接上提交事务。
- rollback() ：在此链接上回滚事务。

**3.Statement接口**

用于执行静态SQL语句并返回它所生成结果的对象。

三种Statement类：

- Statement：由createStatement创建，用于发送简单的SQL语句（不带参数）。
- PreparedStatement ：继承自Statement接口，由preparedStatement创建，用于发送含有一个或多个参数的SQL语句。PreparedStatement对象比Statement对象的效率更高，并且可以防止SQL注入，所以我们一般都使用PreparedStatement。
- CallableStatement：继承自PreparedStatement接口，由方法prepareCall创建，用于调用存储过程。

**常用Statement方法：**

- execute(String sql):运行语句，返回是否有结果集
- executeQuery(String sql)：运行select语句，返回ResultSet结果集。
- executeUpdate(String sql)：运行insert/update/delete操作，返回更新的行数。
- addBatch(String sql) ：把多条sql语句放到一个批处理中。
- executeBatch()：向数据库发送一批sql语句执行。

**4.ResultSet接口**

ResultSet提供检索不同类型字段的方法，常用的有：

- getString(int index)、getString(String columnName)：获得在数据库里是varchar、char等类型的数据对象。
- getFloat(int index)、getFloat(String columnName)：获得在数据库里是Float类型的数据对象。
- getDate(int index)、getDate(String columnName)：获得在数据库里是Date类型的数据。
- getBoolean(int index)、getBoolean(String columnName)：获得在数据库里是Boolean类型的数据。
- getObject(int index)、getObject(String columnName)：获取在数据库里任意类型的数据。

ResultSet还提供了对结果集进行滚动的方法：

- next()：移动到下一行
- Previous()：移动到前一行
- absolute(int row)：移动到指定行
- beforeFirst()：移动resultSet的最前面。
- afterLast() ：移动到resultSet的最后面。

使用后依次关闭对象及连接：**ResultSet → Statement → Connection**

### 五、使用JDBC的基本步骤

##### 1. 注册驱动

```java
//1. 注册驱动
DriverManager.registerDriver(new com.mysql.jdbc.Driver());
```

##### 2. 建立连接

```java
//DriverManager.getConnection("jdbc:mysql://localhost/test?user=SIHAI&password=SIHAI");
//2. 建立连接 参数一： 协议 + 访问的数据库 ， 参数二： 用户名 ， 参数三： 密码。
 conn = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "root");
```

##### 3. 创建statement

```java
//3. 创建statement ， 跟数据库打交道，一定需要这个对象
st = conn.createStatement();
```

##### 4. 执行sql ，得到ResultSet

```java
//4. 执行查询 ， 得到结果集
String sql = "select * from t_stu";
rs = st.executeQuery(sql);
```

##### 5. 遍历结果集

```java
//5. 遍历查询每一条记录
while(rs.next()){
    int id = rs.getInt("id");
    String name = rs.getString("name");
    int age = rs.getInt("age");
    System.out.println("id="+id + "===name="+name+"==age="+age);

}
```

##### 6. 释放资源

```java
if (rs != null) {
    try {
        rs.close();
    } catch (SQLException sqlEx) { } // ignore 
    rs = null;
}
```

### 六、JDBC 工具类构建

##### 1. 资源释放工作的整

```java
/**
* 释放资源
* @param conn
* @param st
* @param rs
*/
public static void release(Connection conn , Statement st , ResultSet rs){
    closeRs(rs);
    closeSt(st);
    closeConn(conn);
}


private static void closeRs(ResultSet rs){
    try {
        if(rs != null){
            rs.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally{
        rs = null;
    }
}

private static void closeSt(Statement st){
    try {
        if(st != null){
            st.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally{
        st = null;
    }
}

private static void closeConn(Connection conn){
    try {
        if(conn != null){
            conn.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally{
        conn = null;
    }
}
```

##### 2. 驱动防二次注册

```java
/**
* 获取连接对象
* @return
*/
public static Connection getConn(){
    Connection conn = null;
    try {
        Class.forName(driverClass);
        //静态代码块 ---> 类加载了，就执行。 java.sql.DriverManager.registerDriver(new Driver());
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
        //2. 建立连接 参数一： 协议 + 访问的数据库 ， 参数二： 用户名 ， 参数三： 密码。
        conn = DriverManager.getConnection(url, name, password);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return conn;
}
```

##### 3. 使用properties配置文件

- 在src底下声明一个文件 xxx.properties ，里面的内容吐下：

```java
driverClass=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost/student
name=root
password=root
```

- 在工具类里面，使用静态代码块，读取属性

```java
static{
    try {
        //1. 创建一个属性配置对象
        Properties properties = new Properties();
        InputStream is = new FileInputStream("jdbc.properties"); //对应文件位于工程根目录

        //使用类加载器，去读取src底下的资源文件。 后面在servlet  //对应文件位于src目录底下
        //InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //导入输入流。
        properties.load(is);

        //读取属性
        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        name = properties.getProperty("name");
        password = properties.getProperty("password");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

**源代码如下：**

```java
public class JDBCUtil {

    static String driverClass = null;
    static String url = null;
    static String name = null;
    static String password= null;

    static{
        try {
            //1. 创建一个属性配置对象
            Properties properties = new Properties();
            InputStream is = new FileInputStream("jdbc.properties");

            //使用类加载器，去读取src底下的资源文件。 后面在servlet
//            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //导入输入流。
            properties.load(is);

            //读取属性
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(driverClass);
            //静态代码块 ---> 类加载了，就执行。 java.sql.DriverManager.registerDriver(new Driver());
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
            //2. 建立连接 参数一： 协议 + 访问的数据库 ， 参数二： 用户名 ， 参数三： 密码。
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn , Statement st , ResultSet rs){
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }


    private static void closeRs(ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            rs = null;
        }
    }

    private static void closeSt(Statement st){
        try {
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            st = null;
        }
    }

    private static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn = null;
        }
    }
}
```



### 七、数据库的CRUD

- insert操作

```java
INSERT INTO t_stu (NAME , age) VALUES ('wangqiang',28)

INSERT INTO t_stu VALUES (NULL,'wangqiang2',28)
// 1. 获取连接对象
conn = JDBCUtil.getConn();
// 2. 根据连接对象，得到statement
st = conn.createStatement();

//3. 执行添加
String sql = "insert into t_stu values(null , 'aobama' , 59)";
//影响的行数， ，如果大于0 表明操作成功。 否则失败
int result = st.executeUpdate(sql);

if(result >0 ){
    System.out.println("添加成功");
}else{
    System.out.println("添加失败");
}
```

- delete操作

```java
DELETE FROM t_stu WHERE id = 6
// 1. 获取连接对象
conn = JDBCUtil.getConn();
// 2. 根据连接对象，得到statement
st = conn.createStatement();

//3. 执行添加
String sql = "delete from t_stu where name='aobama'";
//影响的行数， ，如果大于0 表明操作成功。 否则失败
int result = st.executeUpdate(sql);

if(result >0 ){
    System.out.println("删除成功");
}else{
    System.out.println("删除失败");
}
```

- query操作

```java
SELECT * FROM t_stu

// 1. 获取连接对象
conn = JDBCUtil.getConn();

// 2. 根据连接对象，得到statement
st = conn.createStatement();

// 3. 执行sql语句，返回ResultSet
String sql = "select * from t_stu";
rs = st.executeQuery(sql);

// 4. 遍历结果集
while (rs.next()) {
    String name = rs.getString("name");
    int age = rs.getInt("age");

    System.out.println(name + "   " + age);
}
```

- update操作

```java
UPDATE t_stu SET age = 38 WHERE id = 1;
// 1. 获取连接对象
conn = JDBCUtil.getConn();
// 2. 根据连接对象，得到statement
st = conn.createStatement();

//3. 执行添加
String sql = "update t_stu set age = 26 where name ='qyq'";
//影响的行数， ，如果大于0 表明操作成功。 否则失败
int result = st.executeUpdate(sql);

if(result >0 ){
    System.out.println("更新成功");
}else{
    System.out.println("更新失败");
}
```

### 八、使用单元测试，测试代码

##### 1. 定义一个类， TestXXX , 里面定义方法 testXXX.

这个命名不一定需要这样，但是这样的命名更容易懂得测试的意思，所以建议命名见名知意。

##### 2. 添加junit的支持。

右键工程 --- add Library --- Junit --- Junit4

##### 3. 在方法的上面加上注解 ， 其实就是一个标记。

```java
    /**
 * 使用junit执行单元测试
 */
public class TestDemo {

    @Test
    public void testQuery() {
        // 查询
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            // 3. 执行sql语句，返回ResultSet
            String sql = "select * from t_stu";
            rs = st.executeQuery(sql);

            // 4. 遍历结果集
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println(name + "   " + age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, st, rs);
        }

    }


    @Test
    public void testInsert(){

        // 查询
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            //3. 执行添加
            String sql = "insert into t_stu values(null , 'aobama' , 59)";
            //影响的行数， ，如果大于0 表明操作成功。 否则失败
            int result = st.executeUpdate(sql);

            if(result >0 ){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.release(conn, st);
        }

    }

    @Test
    public void testDelete(){

        // 查询
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            //3. 执行添加
            String sql = "delete from t_stu where name='aobama'";
            //影响的行数， ，如果大于0 表明操作成功。 否则失败
            int result = st.executeUpdate(sql);

            if(result >0 ){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.release(conn, st);
        }

    }
    @Test
    public void testUpdate(){

        // 查询
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 获取连接对象
            conn = JDBCUtil.getConn();
            // 2. 根据连接对象，得到statement
            st = conn.createStatement();

            //3. 执行添加
            String sql = "update t_stu set age = 26 where name ='qyq'";
            //影响的行数， ，如果大于0 表明操作成功。 否则失败
            int result = st.executeUpdate(sql);

            if(result >0 ){
                System.out.println("更新成功");
            }else{
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.release(conn, st);
        }

    }
}
```

##### 4. 光标选中方法名字，然后右键执行单元测试。  或者是打开outline视图， 然后选择方法右键执行。

### 九、Dao模式

Data Access Object 数据访问对象

DAO(Data Access Object) 数据访问对象是一个面向对象的数据库接口，它显露了 Microsoft Jet 数据库引擎（由 Microsoft Access 所使用），并允许 Visual Basic 开发者通过 ODBC 像直接连接到其他数据库一样，直接连接到 Access 表。DAO 最适用于单系统应用程序或小范围本地分布使用。

##### 1. 新建一个dao的接口， 里面声明数据库访问规则

```java
/**
* 定义操作数据库的方法
*/
public interface UserDao {

    /**
     * 查询所有
     */ 
    void findAll();
}
```

##### 2. 新建一个dao的实现类，具体实现早前定义的规则

```java
public class UserDaoImpl implements UserDao{
    @Override
    public void findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //1. 获取连接对象
            conn = JDBCUtil.getConn();
            //2. 创建statement对象
            st = conn.createStatement();
            String sql = "select * from t_user";
            rs = st.executeQuery(sql);

            while(rs.next()){
                String userName = rs.getString("username");
                String password = rs.getString("password");

                System.out.println(userName+"="+password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn, st, rs);
        }
    }
}
```

##### 3. 直接使用实现

```java
@Test
public void testFindAll(){
    UserDao dao = new UserDaoImpl();
    dao.findAll();
}
```

### 十、Statement安全问题

##### 1. Statement执行 ，其实是拼接sql语句的。  先拼接sql语句，然后在一起执行。

```java
String sql = "select * from t_user where username='"+ username  +"' and password='"+ password +"'";

UserDao dao = new UserDaoImpl();
dao.login("admin", "100234khsdf88' or '1=1");

SELECT * FROM t_user WHERE username='admin' AND PASSWORD='100234khsdf88' or '1=1' 

//前面先拼接sql语句， 如果变量里面带有了 数据库的关键字，那么一并认为是关键字。 不认为是普通的字符串。 
rs = st.executeQuery(sql);
```

### PrepareStatement

该对象就是替换前面的statement对象。

1. 相比较以前的statement， 预先处理给定的sql语句，对其执行语法检查。 在sql语句里面使用 ? 占位符来替代后续要传递进来的变量。 后面进来的变量值，将会被看成是字符串，不会产生任何的关键字。

```java
String sql = "insert into t_user values(null , ? , ?)";
ps = conn.prepareStatement(sql);

//给占位符赋值 从左到右数过来，1 代表第一个问号， 永远你是1开始。
ps.setString(1, userName);
ps.setString(2, password);
```

### PreparedStatement与Statement比较

(1) 使用PreparedStatement，代码的可读性和可维护性比Statement高。

(2) PreparedStatement 能最大可能提高性能。

DBServer会对预编译语句提供性能优化。因为预编译语句有可能被重复调用，所以语句在被DBServer的编译器编译后的执行代码被缓存下来，那么下次调用时只要是相同的预编译语句就不需要编译，只要将参数直接传入编译过的语句执行代码中就会得到执行。

在statement语句中,即使是相同操作但因为数据内容不一样，所以整个语句本身不能匹配，没有缓存语句的意义。事实是没有数据库会对普通语句编译后的执行代码缓存。这样每执行一次都要对传入的语句编译一次。

(3) PreparedStatement能保证安全性，但 Statement有sql注入等安全问题。

### 十一、数据库事务

**1. 概述**

在数据库中,所谓事务是指一组逻辑操作单元,使数据从一种状态变换到另一种状态。

为确保数据库中数据的一致性,数据的操纵应当是离散的成组的逻辑单元:当它全部完成时,数据的一致性可以保持,而当这个单元中的一部分操作失败,整个事务应全部视为错误,所有从起始点以后的操作应全部回退到开始状态。

事务的操作:先定义开始一个事务,然后对数据作修改操作,这时如果提交(COMMIT),这些修改就永久地保存下来,如果回退(ROLLBACK),数据库管理系统将放弃您所作的所有修改而回到开始事务时的状态。

**2. 事务的ACID属性**

2.1 原子性（Atomicity）

原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。

2.2 一致性（Consistency）

事务必须使数据库从一个一致性状态变换到另外一个一致性状态。(数据不被破坏)

2.3 隔离性（Isolation）

事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。

2.4 持久性（Durability）

持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响。

**3. JDBC 事务处理**

在JDBC中，事务默认是自动提交的，每次执行一个 SQL 语句时，如果执行成功，就会向数据库自动提交，而不能回滚。

为了让多个 SQL 语句作为一个事务执行，需调用 Connection 对象的 setAutoCommit(false); 以取消自动提交事务：

```java
conn.setAutoCommit(false);
```

在所有的 SQL 语句都成功执行后，调用 commit(); 方法提交事务

```java
conn.commit();
```

在出现异常时，调用 rollback(); 方法回滚事务，一般再catch模块中执行回滚操作。

```java
conn.rollback();
```

可以通过Connection的getAutoCommit()方法来获得当前事务的提交方式。

**注意：在MySQL中的数据库存储引擎InnoDB支持事务，MyISAM不支持事务。**

### 十二、批量处理JDBC语句

**1. 概述**

当需要批量插入或者更新记录时。可以采用Java的批量更新机制，这一机制允许多条语句一次性提交给数据库批量处理。通常情况下比单独提交处理更有效率。

JDBC的批量处理语句包括下面两个方法：

- addBatch(String)：添加需要批量处理的SQL语句或是参数；
- executeBatch（）；执行批量处理语句；

通常我们会遇到两种批量执行SQL语句的情况：

- 多条SQL语句的批量处理；
- 一个SQL语句的批量传参；  

**2. Statement批量处理**

```java
Statement sm = conn.createStatement();
sm.addBatch(sql1);
sm.addBatch(sql2);
...
//批量处理
sm.executeBatch()
//清除sm中积攒的参数列表
sm.clearBatch();
```

**3. PreparedStatement批量传参**

```java
preparedStatement ps = conn.preparedStatement(sql);
for(int i=1;i<100000;i++){
    ps.setInt(1, i);
    ps.setString(2, "name"+i);
    ps.setString(3, "email"+i);
    ps.addBatch();
    if((i+1)%1000==0){
        //批量处理
        ps.executeBatch();
        //清空ps中积攒的sql
        ps.clearBatch();
    }
}
```

**注意：MySQL不支持批量处理。**

批量处理应该设置一个上限，当批量处理列表中的sql累积到一定数量后，就应该执行，并在执行完成后，清空批量列表。

一般在excel导入数据的时候会用到批处理。

### 十三、使用 JDBC 处理元数据

**1. 概述**

Java 通过JDBC获得连接以后，得到一个Connection 对象，可以从这个对象获得有关数据库管理系统的各种信息，包括数据库中的各个表，表中的各个列，数据类型，触发器，存储过程等各方面的信息。根据这些信息，JDBC可以访问一个实现事先并不了解的数据库。

获取这些信息的方法都是在DatabaseMetaData类的对象上实现的，而DataBaseMetaData对象是在Connection对象上获得的。

**2. 获取数据库元数据**

DatabaseMetaData 类中提供了许多方法用于获得数据源的各种信息，通过这些方法可以非常详细的了解数据库的信息：

- getURL()：返回一个String类对象，代表数据库的URL。
- getUserName()：返回连接当前数据库管理系统的用户名。
- isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作。
- getDatabaseProductName()：返回数据库的产品名称。
- getDatabaseProductVersion()：返回数据库的版本号。
- getDriverName()：返回驱动驱动程序的名称。
- getDriverVersion()：返回驱动程序的版本号。

**3. ResultSetMetaData**

可用于获取关于 ResultSet 对象中列的类型和属性信息的对象：

- getColumnName(int column)：获取指定列的名称
- getColumnCount()：返回当前 ResultSet 对象中的列数。  
- getColumnTypeName(int column)：检索指定列的数据库特定的类型名称。  
- getColumnDisplaySize(int column)：指示指定列的最大标准宽度，以字符为单位。  
- isNullable(int column)：指示指定列中的值是否可以为 null。  
- isAutoIncrement(int column)：指示是否自动为指定列进行编号，这样这些列仍然是只读的。

### 十四、创建可滚动、更新的记录集

**1. Statement**

```java
Statement stmt = conn.createStatement(type,concurrency);
```

**2. PreparedStatement**

```java
PreparedStatement stmt = conn.prepareStatement(sql,type,concurrency);
```

**type说明：**

| ResultSet的Type         | 说明                                                         |
| :---------------------- | :----------------------------------------------------------- |
| TYPE_FORWARD_ONLY       | 结果集不能滚动,只可向前滚动                                  |
| TYPE_SCROLL_INSENSITIVE | 双向滚动，但不及时更新，就是如果数据库里的数据修改过，并不在ResultSet中反应出来 |
| TYPE_SCROLL_SENSITIVE   | 双向滚动，并及时跟踪数据库的更新,以便更改ResultSet中的数据   |

**Concurrency(并发类型)说明：**

| ResultSet的Concurrency(并发类型) | 说明                     |
| :------------------------------- | :----------------------- |
| CONCUR_READ_ONLY                 | 结果集不可用于更新数据库 |
| CONCUR_UPDATABLE                 | 结果集可以用于更新数据库 |

**3. ResultSet滚动的结果集使用**

**First:** 将指针移动到此 ResultSet 对象的第一行  
**Last:** 将指针移动到此 ResultSet 对象的最后一行  
**beforeFirst:** 将指针移动到此 ResultSet 对象的开头，正好位于第一行之前  
**afterLast:** 将指针移动到此 ResultSet 对象的末尾，正好位于最后一行之后  
**isFirst:** 检索指针是否位于此 ResultSet 对象的第一行  
**isLast:** 检索指针是否位于此 ResultSet 对象的最后一行  
**isBeforeFirst:** 检索指针是否位于此 ResultSet 对象的第一行之前  
**isAfterLast:** 检索指针是否位于此 ResultSet 对象的最后一行之后  
**Relative:** 按相对行数（或正或负）移动指针  
**Next:** 将指针从当前位置下移一行  
**Previous:** 将指针移动到此 ResultSet 对象的上一行  
**Absolute:** 将指针移动到此 ResultSet 对象的给定行编号

如：

```java
rs.absolute(80); //将指针移动到ResultSet 对象的第80行记录。
```

**注意：**该特性对Oralce数据有效。但是在Mysql数据库中无效，Mysql只支持`TYPE_SCROLL_INSENSITIVE,CONCUR_READ_ONLY`。

### 十五、JDBC连接池

**1. 为什么要使用JDBC连接池**

普通的JDBC数据库连接使用 DriverManager 来获取，每次向数据库建立连接的时候都要将 Connection 加载到内存中，再验证用户名和密码。需要数据库连接的时候，就向数据库要求一个，执行完成后再断开连接。这样的方式将会消耗大量的资源和时间。数据库的连接资源并没有得到很好的重复利用.若同时有几百人甚至几千人在线，频繁的进行数据库连接操作将占用很多的系统资源，严重的甚至会造成服务器的崩溃。

对于每一次数据库连接，使用完后都得断开。否则，如果程序出现异常而未能关闭，将会导致数据库系统中的内存泄漏，最终将导致重启数据库。

这种开发不能控制被创建的连接对象数，系统资源会被毫无顾及的分配出去，如连接过多，也可能导致内存泄漏，服务器崩溃。

为解决传统开发中的数据库连接问题，可以采用数据库连接池技术。

**2. 数据库连接池（connection pool）**

数据库连接池的基本思想就是为数据库连接建立一个“缓冲池”。预先在缓冲池中放入一定数量的连接，当需要建立数据库连接时，只需从“缓冲池”中取出一个，使用完毕之后再放回去。

数据库连接池负责分配、管理和释放数据库连接，它允许应用程序重复使用一个现有的数据库连接，而不是重新建立一个。

数据库连接池在初始化时将创建一定数量的数据库连接放到连接池中，这些数据库连接的数量是由最小数据库连接数来设定的。无论这些数据库连接是否被使用，连接池都将一直保证至少拥有这么多的连接数量。连接池的最大数据库连接数量限定了这个连接池能占有的最大连接数，当应用程序向连接池请求的连接数超过最大连接数量时，这些请求将被加入到等待队列中。

**3. 数据库连接池工作原理**

![img](https://mmbiz.qpic.cn/mmbiz_png/YrLz7nDONjEibykemySKIEIlIicWYUDhvibhObh83dKXleywlicyNjM1udtKmGX11Ueoq6nowDPQHYpqa8GDGSZicVg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

**4. 使用数据库连接池的优点**

(1)资源重用：

由于数据库连接得以重用，避免了频繁创建，释放连接引起的大量性能开销。在减少系统消耗的基础上，另一方面也增加了系统运行环境的平稳性。

(2)更快的系统反应速度

数据库连接池在初始化过程中，往往已经创建了若干数据库连接置于连接池中备用。此时连接的初始化工作均已完成。对于业务请求处理而言，直接利用现有可用连接，避免了数据库连接初始化和释放过程的时间开销，从而减少了系统的响应时间。

(3)新的资源分配手段

对于多应用共享同一数据库的系统而言，可在应用层通过数据库连接池的配置，实现某一应用最大可用数据库连接数的限制，避免某一应用独占所有的数据库资源。

(4)统一的连接管理，避免数据库连接泄露
在较为完善的数据库连接池实现中，可根据预先的占用超时设定，强制回收被占用连接，从而避免了常规数据库连接操作中可能出现的资源泄露。

**5. 常用数据库连接池介绍**

JDBC 的数据库连接池使用 javax.sql.DataSource 来表示，DataSource 只是一个接口，该接口通常由服务器(Weblogic, WebSphere, Tomcat)提供实现，也有一些开源组织提供实现，如：

- DBCP 数据库连接池
- C3P0 数据库连接池
- Proxpool 数据库连接池

其中，DBCP和C3P0用得比较多。

Tomcat 在 7.0 以前的版本都是使用 commons-dbcp 做为连接池的实现。

数据源和数据库连接不同，数据源无需创建多个，它是产生数据库连接的工厂，因此整个应用只需要一个数据源即可。

当数据库访问结束后，程序还是像以前一样关闭数据库连接：`conn.close();` 但它并没有关闭数据库的物理连接，它仅仅把数据库连接释放，归还给了数据库连接池。

大概基本的就是这么多了，希望能够帮助到大家，有问题可以交流沟通。