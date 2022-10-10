//Including default package
package project2part2;

//Including needed files
import java.util.ArrayList;

//Orbiter creature class
//This is an example of inheritance
public class Orbiter extends Creature {
	//Class variables
	private int x;
	private int y;
	private int z;
	private String name;
	private boolean isDead;
	
	//Orbiter constructor
	public Orbiter() {
		name = "Orbiter";
		isDead = false;
		
		do {
			x = Utilities.generateRandomNumber(0, 2);
			y = Utilities.generateRandomNumber(0, 2);
			z = Utilities.generateRandomNumber(1, 4);
		} while (x == 1 && y == 1);
	}
	
	//Getter functions
	public String getName() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	//Moves the orbiter creature
	public ArrayList<Adventurer> moveFight(ArrayList<Adventurer> adventurers) {
		int playerInRoom = -1;
		
		//Makes sure the orbiter isn't already in a room with an adventurer
		for (int i = 0; i < adventurers.size(); i++) {
			if (adventurers.get(i).getX() == x && adventurers.get(i).getY() == y && adventurers.get(i).getIsDead() != true) {
				playerInRoom = i;
				i = adventurers.size();
			}
		}
		
		//Moving if there isn't a player in the room
		if (playerInRoom == -1) {
			if ((x == 0 && y == 0) || (x == 0 && y == 1)) {
				y += 1;
			}
			else if ((x == 0 && y == 2) || (x == 1 && y == 2)) {
				x += 1;
			}
			else if ((x == 2 && y == 2) || (x == 2 && y == 1)) {
				y -= 1;
			}
			else if ((x == 2 && y == 0) || (x == 1 && y == 0)) {
				x -= 1;
			}
		}
		//Fighting the adventurer if there is one in the same room
		else {
			int adventurerFightNum = adventurers.get(playerInRoom).getCombat().fight();
			int creatureFightNum = Utilities.rollTwoDie();
			
			//If adventurer wins the fight
			if (adventurerFightNum > creatureFightNum) {
				System.out.println("A " + name + " has been killed by the " + adventurers.get(playerInRoom).getName());
				adventurers.get(playerInRoom).celebrate(adventurers.get(playerInRoom).getName());
				isDead = true;
			}
			//If creature wins the fight
			else if (adventurerFightNum < creatureFightNum) {
				if (adventurers.get(playerInRoom).getDamage() == 2) {
					System.out.println("The " + adventurers.get(playerInRoom).getName() + " has been killed by a " + name);
				}
				else {
					adventurers.get(playerInRoom).addDamage();
					System.out.println("The " + adventurers.get(playerInRoom).getName() + " has taken damage from the " + name + ".");
				}
			}
		}
		
		return adventurers;
	}
}
