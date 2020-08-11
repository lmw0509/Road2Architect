import org.junit.Test;

public class F35 {
    public static final int i = 2;

    public F35() {
        // 该方法不会调用
        System.out.println("调用构造函数");
    }

    @Test
    public void main() {
        System.out.println(F35.i);
    }
}
