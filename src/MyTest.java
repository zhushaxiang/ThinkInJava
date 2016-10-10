
public class MyTest {
	public static void main(String[] args) {
		A a = new A();
		A a2 = new A();
		B b = new B();
		a.setStr("A");
		System.out.println(a2.getStr());
		System.out.println(b.getStr());
	}

}
class A{
	private static String str = "a";
	public void setStr(String s){
		str = s;
	}
	public String getStr(){
		return str;
	}
}
class B{
	public static String str = "b";
	public void setStr(String s){
		str = s;
	}
	public String getStr(){
		return str;
	}
}
