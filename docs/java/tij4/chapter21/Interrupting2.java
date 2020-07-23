package chapter21;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import static util.Print.print;

class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        //获取所创建对象上自身的Lock,并且从不释放这个锁
        // Acquire it right away, to demonstrate interruption
        // of a task blocked on a ReentrantLock:
        lock.lock();
    }

    public void f() {
        try {
            // This will never be available to a second task
            lock.lockInterruptibly(); // Special call
            print("lock acquired in f()");
        } catch (InterruptedException e) {
            print("Interrupted from lock acquisition in f()");
        }
    }
}

class Blocked2 implements Runnable {
    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        print("Waiting for f() in BlockedMutex");
        //互斥量不可获得而阻塞
        blocked.f();
        print("Broken out of blocked call");
    }
}

public class Interrupting2 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        //打断被互斥所阻塞的调用
        t.interrupt();
    }
}
