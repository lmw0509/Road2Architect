package chapter3;

public class EqualsMethod {
    public static void main(String[] args) {
        Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        //比较的是基本类型的包装类型
        System.out.println(n1.equals(n2));
    }
}
