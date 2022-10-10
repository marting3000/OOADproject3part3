//Including default package
package project2part2;

//Including needed files
import java.util.ArrayList;

//Main Brawler class
//This is an example of inheritance
public class Brawler extends Adventurer {
	//Private variables only for the use of the Brawler class
	private int x;
	private int y;
	private int z;
	private String name;
	private int treasures;
	private int damage;
	private boolean isDead;
	private Combat combat;
	private Search search;
	private boolean monsterFlag;
	private ArrayList<Treasure> heldTreasures;
	
	//Default brawler constructor
	public Brawler() {
		x = 1;
		y = 1;
		z = 0;
		name = "Brawler";
		treasures = 0;
		damage = 0;
		isDead = false;
		combat = new Expert();
		search = new Careless();
		monsterFlag = false;
		heldTreasures = new ArrayList<Treasure>();
	}
	
	//Getter functions
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTreasureCount() {
		return treasures;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public Combat getCombat() {
		return combat;
	}
	
	public ArrayList<Treasure> getHeldTreasures() {
		return heldTreasures;
	}
	
	//Setter functions
	public void addDamage() {
		damage += 1;
	}
	
	public void reduceDamage() {
		damage -= 1;
	}
	
	public void setNewPos(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void toggleIsDead() {
		if (isDead) {
			isDead = false;
		}
		else {
			isDead = true;
		}
	}
	
	//Unique move/fight/loot function for the brawler
	public ArrayList<Creature> moveFight(ArrayList<Creature> creatures) {
		//Function Variables
		ArrayList<ArrayList<Integer>> availableMoves = findAvailableMoves(x, y, z);
		int xyz = 0;
		boolean validMove = false;
		int adventurerFightNum = 0;
		int creatureFightNum = 0;
		monsterFlag = false;
		
		//Moves adventurer
		do {
			xyz = Utilities.generateRandomNumber(1, 3);
			
			if (xyz == 1 && !(availableMoves.get(0).isEmpty())) {
				x = availableMoves.get(0).get(Utilities.generateRandomNumber(0, availableMoves.get(0).size() - 1));
				validMove = true;
			}
			else if (xyz == 2 && !(availableMoves.get(1).isEmpty())) {
				y = availableMoves.get(1).get(Utilities.generateRandomNumber(0, availableMoves.get(1).size() - 1));
				validMove = true;
			}
			else if (xyz == 3 && !(availableMoves.get(2).isEmpty())) {
				z = availableMoves.get(2).get(Utilities.generateRandomNumber(0, availableMoves.get(2).size() - 1));
				validMove = true;
			}
		} while(!validMove);
		update = "Moved " + name + " to " + x + "-" + y + "-" + z;
		notifyObservers();
		
		//Checks to see if there is a monster in the room
		for (int i = 0; i < creatures.size(); i++) {
			//Fights monster if a monster is in the same room
			if (creatures.get(i).getX() == x && creatures.get(i).getY() == y && creatures.get(i).getZ() == z) {
				monsterFlag = true;
				adventurerFightNum = combat.fight();
				creatureFightNum = Utilities.rollTwoDie();
				
				//If adventurer wins the fight
				if (adventurerFightNum > creatureFightNum) {
					System.out.println("A " + creatures.get(i).getName() + " has been killed by the " + name);
					update = name + " won a fight against " + creatures.get(i).getName();
					notifyObservers();
					
					celebrate(name);
					update = name + " celebrated.";
					notifyObservers();
					creatures.remove(i);
				}
				//If creature wins the fight
				else if (adventurerFightNum < creatureFightNum) {
					damage += 1;
					if (damage == 3) {
						System.out.println("The " + name + " has been killed by a " + creatures.get(i).getName());
						update = name + " was killed by " + creatures.get(i).getName();
						notifyObservers();
						isDead = true;
					}
					else {
						System.out.println("The " + name + " has taken damage from the " + creatures.get(i).getName() + ".");
						update = name + " has taken damage from " + creatures.get(i).getName();
						notifyObservers();
					}
				}
			}
		}
		
		//Looking for treasure instead of fighting monster
		if (!monsterFlag) {
			if (Utilities.rollTwoDie() >= 10) {
				System.out.println("The " + name + " has found a treasure!");
				update = name + " has found a treasure!";
				notifyObservers();
				treasures += 1;
			}
		}
		
		return creatures;
	}
	
	public Room loot(Room r) {
		if (monsterFlag) {
			return r;
		}
		
		int loot = search.search();
		
		switch (loot) {
		case 0:
			break;
		case 1:
			heldTreasures.add(r.getTreasure());
			r.setTreasure(null);
			break;
		case 2:
			if (r.getTreasure().getType() == "Trap") {
				if (Utilities.generateRandomNumber(1, 2) == 1) {
					damage += 1;
					if (damage == 3) {
						System.out.println("The " + name + " has been killed by a trap");
						isDead = true;
					}
					else {
						System.out.println("The " + name + " has taken damage from the trap.");
					}
				}
			}
			
			heldTreasures.add(r.getTreasure());
			r.setTreasure(null);
			break;
		}
		
		return r;
	}
}
