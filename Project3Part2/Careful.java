package project2part2;

public class Careful implements Search{
	public int search() {
		if (Utilities.rollTwoDie() >= 7) {
			return 2;
		}
		else {
			return 0;
		}
	}
}
