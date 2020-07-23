package chapter21;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.Print.print;
import static util.Print.printnb;

class Sender implements Runnable {
  private Random rand = new Random(47);
  private PipedWriter out = new PipedWriter();
  public PipedWriter getPipedWriter() { return out; }
  @Override
  public void run() {
    try {
      while(true) {
          for(char c = 'A'; c <= 'z'; c++) {
            out.write(c);
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
          }
      }
    } catch(IOException e) {
      print(e + " Sender write exception");
    } catch(InterruptedException e) {
      print(e + " Sender sleep interrupted");
    }
  }
}

/**
 * 没有sleep()和wait()
 */
class Receiver implements Runnable {
  private PipedReader in;
  //与一个PipedWriter相关联
  public Receiver(Sender sender) throws IOException {
    in = new PipedReader(sender.getPipedWriter());
  }
  @Override
  public void run() {
    try {
      while(true) {
          //调用read()时，如果没有更多的数据，管道将自动阻塞。
        // Blocks until characters are there:
          //修改为System.in.read(),那么interrupt()将不能打断read()的调用。
        printnb("Read: " + (char)in.read() + ", ");
      }
    } catch(IOException e) {
      print(e + " Receiver read exception");
    }
  }
}

public class PipedIO {
  public static void main(String[] args) throws Exception {
    Sender sender = new Sender();
    Receiver receiver = new Receiver(sender);
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(sender);
    exec.execute(receiver);
    TimeUnit.SECONDS.sleep(4);
    exec.shutdownNow();
  }
}
