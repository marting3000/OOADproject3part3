//Including default package
package project2part2;

//Including needed files
import java.util.ArrayList;

//Main game class that runs the game and holds all variables
public class Game implements Subject {
	//Private variables needed for game class
	private Board board;
	private ArrayList<Creature> creatures = new ArrayList<Creature>();
	private ArrayList<Adventurer> adventurers = new ArrayList<Adventurer>();
	private int turnCounter;
	private ArrayList<Observer> observers;
	
	//Game default constructor
	public Game() {
		board = new Board();
		turnCounter = 1;
		
		//Creating all creatures
		//This is an example of polymorphism because this arrayList is made for generic creatures
		creatures.add(new Blinker());
		creatures.add(new Blinker());
		creatures.add(new Blinker());
		creatures.add(new Blinker());
		creatures.add(new Seeker());
		creatures.add(new Seeker());
		creatures.add(new Seeker());
		creatures.add(new Seeker());
		creatures.add(new Orbiter());
		creatures.add(new Orbiter());
		creatures.add(new Orbiter());
		creatures.add(new Orbiter());
		
		//Creating all adventurers
		//This is an example of polymorphism because this arrayList is made for generic adventurers
		adventurers.add(new Brawler());
		adventurers.add(new Sneaker());
		adventurers.add(new Thief());
		adventurers.add(new Runner());
		
		observers = new ArrayList<Observer>();
	}
	
	//Setter functions
	public void setAdventurers(ArrayList<Adventurer> adventurers) {
		this.adventurers = adventurers;
	}
	
	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}
	
	//Getter functions
	public ArrayList<Adventurer> getAdventurers() {
		return adventurers;
	}
	
	public ArrayList<Creature> getCreatures() {
		return creatures;
	}
	
	public int getTurnCounter() {
		return turnCounter;
	}
	
	//Starts game and is the main function that runs the game
	public void runGame() {
		//Negative number is valid game to keep going, positive number means the game will stop
		int validGame = 1;
		
		int tempX = 0;
		int tempY = 0;
		int tempZ = 0;
		
		//Start of game announcements
		System.out.println("Welcome to RotLA!");
		System.out.println("Starting game...");
		System.out.println("Starting board:");
		renderBoard();
		System.out.println();
		
		//Starting turns
		do {
			//prints name of game and turn counter
			System.out.println("RotLA Turn " + turnCounter + ":");
			
			for (int i = 0; i < adventurers.size(); i++) {
				if (adventurers.get(i).getIsDead() == false) {
					creatures = adventurers.get(i).moveFight(creatures);
					tempX = adventurers.get(i).getX();
					tempY = adventurers.get(i).getY();
					tempZ = adventurers.get(i).getZ();
					if (!(tempX == 1 && tempY == 1 && tempZ == 0)) {
						//When an Adventurer loots, sends the room they're in and then returns it so changes move back to the board object
						board.setRoom(tempX, tempY, tempZ, adventurers.get(i).loot(board.getRoom(tempX, tempY, tempZ)));
					}
				}
			}
			
			for (int i = 0; i < creatures.size(); i++) {
				if (creatures.get(i).getIsDead() == false) {
					adventurers = creatures.get(i).moveFight(adventurers);
				}
			}
			
			//End of turn stat check
			renderBoard();
			turnCounter++;
			
			//Checks if the game is over and returns code if it is
			/*
			 * returns 1 if the adventurers found all treasures
			 * returns 2 if all of the adventurers died
			 * returns 3 if all of the creatures died
			 * returns -1 if the game is going to keep going
			 */
			validGame = checkIfGameDone();
			
		} while (validGame < 0);
		
		//Ending the game
		System.out.println("Ending the game:");
		switch (validGame) {
			case(1): 
				System.out.println("The game ended because the adventurers found all the treasures!");
				break;
			case(2):
				System.out.println("The game ended because all of the adventurers died.");
				break;
			case(3):
				System.out.println("The game ended because the adventurers killed off all of the creatures.");
		}
	}
	
	//Displays board, creatures, and adventurers in a formatted way
	public void renderBoard() {
		//Prints board state
		board.printBoard(adventurers, creatures);
		System.out.println();
		
		//Prints adventurers
		System.out.println("Brawler - " + countTreasures("Brawler") + " Treasure(s) - " + countDamage("Brawler") + " damage");
		System.out.println("Sneaker - " + countTreasures("Sneaker") + " Treasure(s) - " + countDamage("Sneaker") + " damage");
		System.out.println("Runner - " + countTreasures("Runner") + " Treasure(s) - " + countDamage("Runner") + " damage");
		System.out.println("Thief - " + countTreasures("Thief") + " Treasure(s) - " + countDamage("Thief") + " damage");
		System.out.println();
		
		//Prints creatures
		System.out.println("Orbiters - " + countCreatures("Orbiter") + " Remaining");
		System.out.println("Seekers - " + countCreatures("Seeker") + " Remaining");
		System.out.println("Blinkers - " + countCreatures("Blinker") + " Remaining");
		System.out.println();
		System.out.println();
	}
	
	//Searches the list of adventurers for the correct one and returns the treasure count
	//This is an example of strong cohesion because this function does only one job
	public int countTreasures(String name) {
		for (int i = 0; i < adventurers.size(); i++) {
			if (adventurers.get(i).getName() == name) {
				return adventurers.get(i).getTreasureCount();
			}
		}
		
		return 0;
	}
	
	//Searches the list of adventurers for the correct one and returns the damage taken
	//This is an example of strong cohesion because this function does only one job
	public int countDamage(String name) {
		for (int i = 0; i < adventurers.size(); i++) {
			if (adventurers.get(i).getName() == name) {
				return adventurers.get(i).getDamage();
			}
		}
		
		return -1;
	}
	
	//Iterates through the creatures list and counts the number of creatures left with the given name
	//This is an example of strong cohesion because this function does only one job
	public int countCreatures(String name) {
		int count = 0;
		
		for (int i = 0; i < creatures.size(); i++) {
			if (creatures.get(i).getName() == name) {
				count++;
			}
		}
		
		return count;
	}
	
	//Checks if the game is over.  See above for the return variable key
	//This is an example of strong cohesion because this function does only one job
	public int checkIfGameDone() {
		//Counts treasures and adventurers dead
		int totalTreasureCount = 0;
		int totalDeadAdventurers = 0;
		
		//Runs through the adventurers arrayList to gather the treasure count and how many adventurers are dead
		for (int i = 0; i < adventurers.size(); i++) {
			totalTreasureCount += adventurers.get(i).getTreasureCount();
			if (adventurers.get(i).getIsDead()) {
				totalDeadAdventurers++;
			}
		}
		
		//Checks if the game is over beacuse the adventurers found all the treasures
		if (totalTreasureCount >= 10) {
			return 1;
		}
		
		//Checks if all of the adventurers are dead
		if (totalDeadAdventurers == adventurers.size()) {
			return 2;
		}
		
		int totalDeadCreatures = 0;
		
		//Checks if all of the creatures are dead
		for (int i = 0; i < creatures.size(); i++) {
			if (creatures.get(i).getIsDead()) {
				totalDeadCreatures++;
			}
		}
		
		if (totalDeadCreatures == creatures.size()) {
			return 3;
		}
		
		return -1;
	}
	
	public void registerObserver(Observer ob) {
		observers.add(ob);
	}
	
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}
	
	public void notifyObservers() {
		for (Observer ob: observers) {
			ob.update(adventurers, creatures);
		}
	}
}
