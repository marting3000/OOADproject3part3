package project2part2;

public class Quick implements Search {
	public int search() {
		//Has a 33% chance to not search
		if (Utilities.generateRandomNumber(1, 3) == 3) {
			return 0;
		}
		else {
			if (Utilities.rollTwoDie() >= 9) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}
