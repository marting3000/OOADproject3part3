//Includes default packages
package project2part2;

//Includes needed files
import java.util.ArrayList;

//Blinker creature class
//This is an example of inheritance
public class Blinker extends Creature {
	//Class variables
	private int x;
	private int y;
	private int z;
	private String name;
	private boolean isDead;
	
	//Blinker constructor
	public Blinker() {
		name = "Blinker";
		x = Utilities.generateRandomNumber(0, 2);
		y = Utilities.generateRandomNumber(0, 2);
		z = 4;
		isDead = false;
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
	
	//Moves the blinker creature
	public ArrayList<Adventurer> moveFight(ArrayList<Adventurer> adventurers) {
		int playerInRoom = -1;
		
		//Finds if this creature is in the same room as an adventurer
		for (int i = 0; i < adventurers.size(); i++) {
			if (adventurers.get(i).getX() == x && adventurers.get(i).getY() == y && adventurers.get(i).getZ() == z && adventurers.get(i).getIsDead() != true) {
				playerInRoom = i;
			}
		}
		
		//If creature doesn't see an adventurer
		if (playerInRoom == -1) {
			//Randomly generating x, y, and z to move to
			x = Utilities.generateRandomNumber(0, 2);
			y = Utilities.generateRandomNumber(0, 2);
			z = Utilities.generateRandomNumber(1, 4);
			
		}
		//If the creature is in the same room as the adventurer
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
