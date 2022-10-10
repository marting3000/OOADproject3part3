package project2part2;

public class Stealth implements Combat {
	public int fight() {
		if (Utilities.generateRandomNumber(1, 2) == 1) {
			return Utilities.rollTwoDie();
		}
		
		return -1;
	}
}
