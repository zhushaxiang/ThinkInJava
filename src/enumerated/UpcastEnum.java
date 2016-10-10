//: enumerated/UpcastEnum.java
// No values() method if you upcast an enum
package enumerated;

enum Search { HITHER, YON }

public class UpcastEnum {
  public static void main(String[] args) {
    Search[] vals = Search.values();
    for(Search s : vals){
    	System.out.println(s.ordinal());
    }
    Enum<?> e = Search.HITHER; // Upcast
    // e.values(); // No values() in Enum
    for(Enum<?> en : e.getClass().getEnumConstants())
      System.out.println(en);
  }
} /* Output:
HITHER
YON
*///:~