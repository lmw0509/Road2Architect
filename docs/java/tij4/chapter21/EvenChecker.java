package chapter21;
import java.util.concurrent.*;

public class EvenChecker implements Runnable {
  private IntGenerator generator;
  private final int id;
  public EvenChecker(IntGenerator g, int ident) {
    generator = g;
    id = ident;
  }
  @Override
  public void run() {
      //检查是不是偶数，如果不是，报告并退出程序
    while(!generator.isCanceled()) {
      int val = generator.next();
      if(val % 2 != 0) {
        System.out.println(val + " not even!");
        generator.cancel(); // Cancels all EvenCheckers
      }
    }
  }
  public static void test(IntGenerator gp, int count) {
    System.out.println("Press Control-C to exit");
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < count; i++) {
        exec.execute(new EvenChecker(gp, i));
    }
    exec.shutdown();
  }
  public static void test(IntGenerator gp) {
    test(gp, 10);
  }
}
