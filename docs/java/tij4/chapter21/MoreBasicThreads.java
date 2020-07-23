package chapter21;

public class MoreBasicThreads {
    public static void main(String[] args) {
        /**
         * 较早的JDK不会频繁对时间进行切片，因此线程1可能会首先循环到尽头，然后线程2，等等。
         */
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
