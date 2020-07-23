package others.constructblock;

public class Demo1 {
    public static void main(String[] args) {
        //构造代码块会在构造函数被调用时执行， 且在这个例子中比"this.id=id;"语句先执行，作用是给对象统一初始化数据;
        Test1 test = new Test1(3);
        System.out.println(test);
    }
}

/**
 * 直接在类中定义且没有加static关键字的代码块称为{}构造代码;
 * 作用：给对象统一初始化数据
 * */
class Test1 {
    int id;
    String name;

    {
        this.id = 5;
        this.name = "测试";
        System.out.println("这是构造代码块");
    }

    Test1(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "name: " + this.name + "  ,   " + "id: " + this.id;
    }
}

