//Includes default package
package project2part2;

//Includes needed files
import java.util.ArrayList;

//Main board class to hold all things board
public class Board {
	//Keeps the rooms in a 3D arrayList
	/*
	 * rooms arrayList key
	 * ArrayList = floors (z)
	 * ArrayList(ArrayList) = rows (x)
	 * ArrayList(ArrayList(ArrayList)) = columns (y)
	 */
	private ArrayList<ArrayList<ArrayList<Room>>> rooms = new ArrayList<ArrayList<ArrayList<Room>>>();
	
	//Public default constructor
	public Board() {
		//Adding entrance room
		rooms.add(new ArrayList<ArrayList<Room>>());
		rooms.get(0).add(new ArrayList<Room>());
		rooms.get(0).add(null);
		
		//Adding rooms to rooms ArrayList
		for (int z = 1; z < 5; z++) {
			rooms.add(new ArrayList<ArrayList<Room>>());
			
			for (int x = 0; x < 3; x++) {
				rooms.get(z).add(new ArrayList<Room>());
				
				for (int y = 0; y < 3; y++) {
					rooms.get(z).get(x).add(new Room());
				}
			}
		}
	}
	
	public Room getRoom(int x, int y, int z) {
		return rooms.get(z).get(x).get(y);
	}
	
	public void setRoom(int x, int y, int z, Room r) {
		rooms.get(z).get(x).set(y, r);
		
	}
	
	//Print the list of rooms
	public void printBoard(ArrayList<Adventurer> adventurers, ArrayList<Creature> creatures) {
		int extraX = 0;
		int extraY = 0;
		int extraZ = 0;
		
		System.out.print("    0-1-1: ");
		
		for (int i = 0; i < adventurers.size(); i++) {
			extraX = adventurers.get(i).getX();
			extraY = adventurers.get(i).getY();
			extraZ = adventurers.get(i).getZ();
			
			if (extraX == 1 && extraY == 1 && extraZ == 0) {
				System.out.print(adventurers.get(i).getName().substring(0, 1));
			}
		}
		System.out.println();
		
		//Printing out each room
		for (int z = 1; z < 5; z++) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					System.out.print("    " + z + "-" + x + "-" + y + ":  ");
					
					//Prints the first letter of the name of each adventurer in their respective room
					for (int i = 0; i < adventurers.size(); i++) {
						extraX = adventurers.get(i).getX();
						extraY = adventurers.get(i).getY();
						extraZ = adventurers.get(i).getZ();
						
						if (extraX == x && extraY == y && extraZ == z) {
							System.out.print(adventurers.get(i).getName().substring(0, 1));
						}
					}
					
					System.out.print(" : ");
					
					//Prints the first letter of the name of each creature in their respective room
					for (int i = 0; i < creatures.size(); i++) {
						extraX = creatures.get(i).getX();
						extraY = creatures.get(i).getY();
						extraZ = creatures.get(i).getZ();
						
						if (extraX == x && extraY == y && extraZ == z) {
							System.out.print(creatures.get(i).getName().substring(0, 1));
						}
					}
				}
				System.out.println();
			}
		}
	}
 }