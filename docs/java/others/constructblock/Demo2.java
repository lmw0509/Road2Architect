package others.constructblock;

public class Demo2 {
    public static void main(String[] args) {
        new Test2();
        new Test2();
        new Test2(3);
        new Test2();
        new Test2(5);
        Test2.count();
    }
}

/**
 *构造代码块注意点：
 * 1：java编译器编译java类时，会先将成员属性的声明放到类的前端
 * 2：成员变量的初始化工作放到构造函数中
 * 3：如果类中有构造代码块，java编译器在编译时会先将构造代码块中的代码移到构造函数中执行，构造函数中原有的代码最后执行
 * 4：成员属性的初始化和构造代码块的执行顺序是根据原码中的位置执行　
 */
class Test2 {
    int id;
    String name;
    static int sum = 0;

    {
        sum += 1;
    }

    Test2() {

    }

    Test2(int id) {

    }

    static void count() {
        System.out.println(sum);
    }

}


