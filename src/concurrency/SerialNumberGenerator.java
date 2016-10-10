//: concurrency/SerialNumberGenerator.java
package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SerialNumberGenerator {
  private static AtomicInteger serialNumber = new AtomicInteger(0);
  public  static int nextSerialNumber() {
    return serialNumber.addAndGet(2); // Not thread-safe
  }
} ///:~
