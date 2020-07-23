package chapter21;
import java.util.concurrent.*;

public class SleepingTask extends LiftOff {
  @Override
  public void run() {
    try {
      while(countDown-- > 0) {
        System.out.print(status());
        TimeUnit.MILLISECONDS.sleep(1000);
      }
    } catch(InterruptedException e) {
      System.err.println("Interrupted");
    }
  }
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++){
        exec.execute(new SleepingTask());
    }
    exec.shutdown();
  }
}