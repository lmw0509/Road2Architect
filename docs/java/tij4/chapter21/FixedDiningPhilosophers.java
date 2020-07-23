package chapter21;

import java.util.concurrent.*;

public class FixedDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            //最后一个哲学家初始化为先拿左边的，后拿右边的，那么这个哲学家将永远不会阻止其右边的哲学家拿起他们的筷子。
            if (i < (size - 1)) {
                exec.execute(new Philosopher(
                        sticks[i], sticks[i + 1], i, ponder));
            } else {
                exec.execute(new Philosopher(
                        sticks[0], sticks[i], i, ponder));
            }
        }
        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
