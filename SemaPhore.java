package Operating_System_Programs;
import java.util.concurrent. * ;
class SharedResource {
static int count = 0;
}
class MyThread extends Thread {
Semaphore semaphore;
String threadName;
public MyThread(Semaphore semaphore, String threadName) {
  super(threadName);
  this.semaphore = semaphore;
  this.threadName = threadName;
}@Override
public void run() {
  if (this.getName().equals("Thread1")) {
    System.out.println("Starting " + threadName);
    try {
      System.out.println(threadName + " is waiting for a permit.");
      semaphore.acquire();
      System.out.println(threadName + " gets a permit.");
      for (int i = 0; i < 5; i++) {
        SharedResource.count++;
        System.out.println(threadName + ": " + SharedResource.count);
        Thread.sleep(10);
      }
    }
    catch(InterruptedException exc) {
      System.out.println(exc);
    }
    System.out.println(threadName + " releases the permit.");
    semaphore.release();
  }
  else {
    System.out.println("Starting " + threadName);
    try {
      System.out.println(threadName + " is waiting for a permit.");
      semaphore.acquire();
      System.out.println(threadName + " gets a permit.");
      for (int i = 0; i < 5; i++) {
        SharedResource.count--;
        System.out.println(threadName + ": " + SharedResource.count);
        Thread.sleep(10);
      }
    }
    catch(InterruptedException exc) {
      System.out.println(exc);
    }
    System.out.println(threadName + " releases the permit.");
    semaphore.release();
  }
}
}
public class SemaPhore {
public static void main(String args[]) throws InterruptedException { 
  Semaphore semaphore = new Semaphore(1);
  MyThread t1 = new MyThread(semaphore, "Thread1");
  MyThread t2 = new MyThread(semaphore, "Thread2");
  t1.start();
  t2.start();
  t1.join();
  t2.join();
  System.out.println("count: " + SharedResource.count);
}
}