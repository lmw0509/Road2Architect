class F22 {
    public static final int i = 2;

    F22() {
        // 该方法不会调用
        System.out.println("调用构造函数");
    }

    public static void main(final String[] args) {
        System.out.println(F22.i);
    }
}
