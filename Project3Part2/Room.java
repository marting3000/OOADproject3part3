//Includes default package
package project2part2;

//Room class to hold room information
public class Room {
	//Private variables for each room
	private int x;
	private int y;
	private int z;
	private Treasure treasure;

	//Room default constructor
	public Room() {
		x = 0;
		y = 0;
		z = 0;
		treasure = null;
	}
	
	//Room overloaded constructor
	public Room(int x_, int y_, int z_) {
		x = x_;
		y = y_;
		z = z_;
		treasure = null;
	}
	
	//Getter functions
	public int getRoomX() {
		return x;
	}
	
	public int getRoomY() {
		return y;
	}
	
	public int getRoomZ() {
		return z;
	}
	
	public Treasure getTreasure() {
		if (treasure == null) {
			Treasure t = new Treasure();
			return t;
		}
		
		return treasure;
	}
	
	//Setter functions
	public void setRoomX(int x_) {
		x = x_;
	}
	
	public void setRoomY(int y_) {
		y = y_;
	}
	
	public void setRoomZ(int z_) {
		z = z_;
	}
	
	public void setRoomCoords(int x_, int y_, int z_) {
		x = x_;
		y = y_;
		z = z_;
	}
	
	public void setTreasure(Treasure treasure) {
		this.treasure = treasure;
	}
}
