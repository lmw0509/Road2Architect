package chapter21;

public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        /**
         * 这个任务的run()不是由单独的线程驱动的，它是在main()中直接调用的(实际上这里仍旧使用了线程，即总是分配给main()的那个线程)
         */
        launch.run();
    }
}
