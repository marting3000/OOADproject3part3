//Includes default package
package project2part2;

//Includes needed files
import java.util.ArrayList;

//Abstract creature class
//This is an example of abstraction because the entire class is just laying out the template for the other creature classes
public abstract class Creature {
	
	//Default creature constructor
	public Creature() {
		
	}
	
	//Getter functions
	public abstract String getName();
	
	public abstract int getX();
	
	public abstract int getY();
	
	public abstract int getZ();
	
	public abstract boolean getIsDead(); 
	
	//This is an example of encapsulation because it's a usable function but it's hiding the implementation.
	public abstract ArrayList<Adventurer> moveFight(ArrayList<Adventurer> adventurers);
}