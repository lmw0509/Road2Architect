package chapter21;

public class BasicThreads {
    public static void main(String[] args) {
        /**
         * LiftOff.run()是由不同的线程执行的，因此你仍旧可以执行main()线程中的其他操作。所以同时执行。
         */
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
