//: enumerated/SpaceShip.java
package enumerated;

public enum SpaceShip {
	SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;
	public String toString() {
		String id = name();
		String lower = id.substring(1).toLowerCase();
		return id.charAt(0) + lower;
	}
	public void testsss(){
		System.out.println("a");
	}

	public static void main(String[] args) {
		for (SpaceShip s : values()) {
			System.out.println(s);
		}
		SpaceShip spaceShip = SpaceShip.BATTLESHIP;
		spaceShip.testsss();
	}
} /*
	 * Output: Scout Cargo Transport Cruiser Battleship Mothership
	 */// :~  18186537257?
