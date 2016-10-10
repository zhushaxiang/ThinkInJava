//: generics/CuriouslyRecurringGeneric.java
package generics;

class GenericType<T> {}

public class CuriouslyRecurringGeneric
  extends GenericType<CuriouslyRecurringGeneric> {} ///:~
