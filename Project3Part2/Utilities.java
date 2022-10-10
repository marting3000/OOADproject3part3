//Includes default package
package project2part2;

//Includes needed files
import java.util.Random;

//Utilities class for extra functions
public class Utilities {
	//Default constructor
	public Utilities() {
		
	}
	
	//Generates a random number from a to b inclusively
	public static int generateRandomNumber(int a, int b) {	
		Random rand = new Random();
		
		if (a == 0 && b == 0) {
			return 0;
		}
		
		int randomNum = rand.nextInt(b - a + 1) + a;
		return randomNum;
	}
	
	//Rolls 1 d6
	public static int rollOneDie() {
		return generateRandomNumber(1, 6);
	}
	
	//Rolls 2 d6
	public static int rollTwoDie() {
		return generateRandomNumber(1, 6) + generateRandomNumber(1, 6);
	}
}
