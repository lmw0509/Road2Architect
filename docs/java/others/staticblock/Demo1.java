package others.staticblock;

public class Demo1 {
    public static void main(String[] args) {
        new Test();
    }
}

/**
 * 静态代码块：在java中使用static关键字声明的代码块。
 * 静态块用于初始化类，为类的属性初始化。
 * 每个静态代码块只会执行一次。由于JVM在加载类时会执行静态代码块，所以静态代码块先于主方法执行。
 * 注意：
 * 1 静态代码块不能存在于任何方法体内。
 * 2 静态代码块不能直接访问静态实例变量和实例方法，需要通过类的实例对象来访问。
 * */
class Test {
    int id;
    String name;

    static {
        System.out.println("我是静态代码块");
    }

}