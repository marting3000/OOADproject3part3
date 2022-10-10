//Including default package
package project2part2;

//Including needed files
import java.util.ArrayList;

//Abstract Adventurer class
//This is an example of abstraction because the entire class is just laying out the template for the other adventurer classes
public abstract class Adventurer implements Subject {
	private ArrayList<Observer> observers;
	protected String update;
	
	//Adventurer default constructor
	public Adventurer() {
		observers = new ArrayList<Observer>();
		update = "";
	}
	
	//Getter functions
	public abstract int getX();
	
	public abstract int getY();
	
	public abstract int getZ();
	
	public abstract String getName();
	
	public abstract int getTreasureCount();
	
	public abstract int getDamage();
	
	public abstract boolean getIsDead();
	
	public abstract Combat getCombat();
	
	public abstract ArrayList<Treasure> getHeldTreasures();
	
	public String getUpdate() {
		return update;
	}
	
	//Setter function
	public abstract void addDamage();
	
	public abstract void reduceDamage();
	
	public abstract void setNewPos(int x, int y, int z);
	
	public abstract void toggleIsDead();
	
	//Default movement and fight function
	public abstract ArrayList<Creature> moveFight(ArrayList<Creature> creatures);
	
	public abstract Room loot(Room r);
	
	public void celebrate(String name) {
		Celebration c = new Celebration();
		
		System.out.print(name + " celebrates");
		
		for (int i = 0; i < Utilities.generateRandomNumber(0, 2); i++) {
			c = new Shout(c);
		}
		
		for (int i = 0; i < Utilities.generateRandomNumber(0, 2); i++) {
			c = new Dance(c);
		}
		
		for (int i = 0; i < Utilities.generateRandomNumber(0, 2); i++) {
			c = new Jump(c);
		}
		
		for (int i = 0; i < Utilities.generateRandomNumber(0, 2); i++) {
			c = new Spin(c);
		}
		
		System.out.println(c.getCelebration());
	}
	
	//Finding the available moves given the rooms around them
	public ArrayList<ArrayList<Integer>> findAvailableMoves(int x, int y, int z) {
		/*
		 * ArrayList key
		 * ArrayList (
		 * 		ArrayList of possible x values to move
		 * 		ArrayList of possible y values to move
		 * 		ArrayList of possible z values to move
		 * )
		 */
		ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> xMoves = new ArrayList<Integer>();
		ArrayList<Integer> yMoves = new ArrayList<Integer>();
		ArrayList<Integer> zMoves = new ArrayList<Integer>();
		availableMoves.add(xMoves);
		availableMoves.add(yMoves);
		availableMoves.add(zMoves);
		
		//If they aren't in the starting room
		if (!(x == 1 && y == 1 && z == 0)) {
			//Checks if positive x is a possible move 
			if (x < 2) {
				xMoves.add(x + 1);
			}
			
			//Checks if negative x is a possible move
			if (x > 0) {
				xMoves.add(x - 1);
			}
			
			//Checks if positive y is a possible move
			if (y < 2) {
				yMoves.add(y + 1);
			}
			
			//Checks if negative y is a possible move
			if (y > 0) {
				yMoves.add(y - 1);
			}
			
			//Checks if creature is on 1, 1 (x, y) so they are able to move up and down
			if (x == 1 && y == 1) {
				//Checks if positive z is a possible move
				if (z > 1) {
					zMoves.add(z + 1);
				}
				
				//Checks if negative z is a possible move
				if (z < 4) {
					zMoves.add(z - 1);
				}
			}
		}
		else {
			zMoves.add(1);
		}
		return availableMoves;
	}
	
	public void registerObserver(Observer ob) {
		observers.add(ob);
	}
	
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}
	
	public void notifyObservers() {
		for (Observer ob: observers) {
			ob.update(this);
		}
	}
}
