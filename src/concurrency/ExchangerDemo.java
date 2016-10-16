//: concurrency/ExchangerDemo.java
package concurrency;

import java.util.concurrent.*;
import java.util.*;

import net.mindview.util.*;

class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    ExchangerProducer(Exchanger<List<T>> exchg,
                      Generator<T> gen, List<T> holder) {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++){
                    holder.add(generator.next());
                }
                System.out.println("======================");
                TimeUnit.SECONDS.sleep(1);
                // Exchange full for empty:
                holder = exchanger.exchange(holder);
                System.out.println(ExchangerDemo.tmp++);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    private String name;

    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder,String name) {
        exchanger = ex;
        this.holder = holder;
        this.name = name;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                System.out.println(this);
                for (T x : holder) {
                    System.out.println(x);
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList
                }
                System.out.println(ExchangerDemo.tmp--);
            }
        } catch (Exception e) {
            // OK to terminate this way.
        }
        System.out.println("Final value: " + value);
    }

    @Override
    public String toString() {
        return "ExchangerConsumer{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class ExchangerDemo {
    static int tmp = 0;
    static int size = 10;
    static int delay = 2000; // Seconds

    public static void main(String[] args) throws Exception {
        if (args.length > 0)
            size = new Integer(args[0]);
        if (args.length > 1)
            delay = new Integer(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
        List<Fat>
                producerList = new CopyOnWriteArrayList<Fat>(),
                consumerList = new CopyOnWriteArrayList<Fat>();
        exec.execute(new ExchangerProducer<Fat>(xc,
                BasicGenerator.create(Fat.class), producerList));
        exec.execute(
                new ExchangerConsumer<Fat>(xc, consumerList,"A"));

        TimeUnit.MILLISECONDS.sleep(delay);
        exec.shutdownNow();
    }
} /* Output: (Sample)
Final value: Fat id: 29999
*///:~
