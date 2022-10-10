package project2part2;

public class Treasure {
	private String name;
	
	public Treasure() {
		name = "";
	}
	
	public Treasure(String name) {
		this.name = name;
	}
	
	public String getType() {
		return name;
	}
	
	public int fightCheck() {
		//If the adventurer has a gem, they get -1 combat roll (equivalent to a +1 for the creature)
		if (name =="Gem") {
			return -1;
		}
		//If the adventurer has an Armor or Sword, they get +1 combat roll (equivalent to -1 for the creature)
		else {
			return 1;
		}
	}
	
	public Adventurer endOfTurnCheck(Adventurer adventurer) {
		if (name == "Portal") {
			System.out.println("The " + adventurer.getName() + " has found a portal and has been transported to a new place.");
			adventurer.setNewPos(Utilities.generateRandomNumber(0, 2), Utilities.generateRandomNumber(0, 2), Utilities.generateRandomNumber(0, 2));
		}
		else if (name == "Trap") {
			adventurer.addDamage();
			System.out.println("The " + adventurer.getName() + " has taken one damage from a trap.");
			if (adventurer.getDamage() == 3) {
				System.out.println("The " + adventurer.getName() + " has died to a trap.");
				adventurer.toggleIsDead();
			}
		}
		else if (name == "Potion") {
			adventurer.reduceDamage();
		}
		
		return adventurer;
	}
}
