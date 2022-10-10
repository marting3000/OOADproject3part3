//Includes default package
package project2part2;

//Includes needed files
import java.util.ArrayList;

//Seeker creature object
//This is an example of inheritance
public class Seeker extends Creature {
	//Class variables
	private int x;
	private int y;
	private int z;
	private String name;
	private boolean isDead;
	
	//Seeker constructor
	public Seeker() {
		name = "Seeker";
		x = Utilities.generateRandomNumber(0, 2);
		y = Utilities.generateRandomNumber(0, 2);
		z = Utilities.generateRandomNumber(1, 4);
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
	
	//Moves the seeker creature
	public ArrayList<Adventurer> moveFight(ArrayList<Adventurer> adventurers) {
		int playerInRoom = -1;
		
		//Loops through each adventurer to see if a seeker is around them
		for (int i = 0; i < adventurers.size(); i++) {
			//Makes sure that the seeker is on the same level as the adventurer
			if (adventurers.get(i).getZ() == z) {
				//Sees if an adventurer is one room away from this seeker
				if (adventurers.get(i).getX() + 1 == x && adventurers.get(i).getIsDead() != true) {
					x += 1;
					playerInRoom = i;
					i = adventurers.size();
				}
				else if (adventurers.get(i).getX() - 1 == x && adventurers.get(i).getIsDead() != true) {
					x -= 1;
					playerInRoom = i;
					i = adventurers.size();
				}
				else if (adventurers.get(i).getY() + 1 == y && adventurers.get(i).getIsDead() != true) {
					y += 1;
					playerInRoom = i;
					i = adventurers.size();
				}
				else if (adventurers.get(i).getY() - 1 == y && adventurers.get(i).getIsDead() != true) {
					y -= 1;
					playerInRoom = i;
					i = adventurers.size();
				}
			}
		}
		
		//Fighting an adventurer if they're in the same room
		if (playerInRoom > -1) {
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
