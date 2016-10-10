//: concurrency/NaiveExceptionHandling.java
// {ThrowsException}
package concurrency;

import java.util.concurrent.*;

public class NaiveExceptionHandling {
	public static void main(String[] args) {
		try {
			/*ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());*/
			new Thread(new ExceptionThread()).start();
		} catch (RuntimeException ue) {
			// This statement will NOT execute!
			System.out.println("Exception has been handled!");
		}
	}
} /// :~
