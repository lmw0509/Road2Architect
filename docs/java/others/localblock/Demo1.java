package others.localblock;

public class Demo1 {
    public static void main(String[] args) {
        Test test = new Test();
        test.run();
    }
}

/**
 * 局部代码块：在函数中的代码块。
 * 作用：在方法中，如果要缩短变量的寿命，可以使用方法中，
 * 某段代码之后，都不再使用某个变量（这个变量有可能是一个很大的Map集合，很占内存），
 * 则可以将其定义到局部代码块中，及时结束其生命周期，释放空间！
 *
 * */
class Test {
    int id;
    String name;

    void run() {
        {
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
            }
            System.out.println("我是局部代码块,变量i出了局部代码块就没有任何作用");
        }
        System.out.println("我是普通函数");
    }
}
