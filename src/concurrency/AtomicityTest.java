//: concurrency/AtomicityTest.java
package concurrency;

import java.util.concurrent.*;

public class AtomicityTest implements Runnable {
	private static int i = 0;

	public synchronized int getValue() {
		return i;
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	public void run() {
		while (true)
			evenIncrement();
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
} /*
	 * Output: (Sample) 191583767
	 */// :~
