package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;
import static util.Print.print;

public class DaemonFromFactory implements Runnable {
  @Override
  public void run() {
    try {
      while(true) {
        TimeUnit.MILLISECONDS.sleep(100);
        print(Thread.currentThread() + " " + this);
      }
    } catch(InterruptedException e) {
      print("Interrupted");
    }
  }
  public static void main(String[] args) throws Exception {
    ExecutorService exec = newCachedThreadPool(new DaemonThreadFactory());
    for(int i = 0; i < 10; i++){
        exec.execute(new DaemonFromFactory());
    }
    print("All daemons started");
    TimeUnit.MILLISECONDS.sleep(500);
  }
}
