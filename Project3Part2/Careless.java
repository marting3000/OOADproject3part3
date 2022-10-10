package project2part2;

public class Careless implements Search{
	public int search() {
		if (Utilities.rollTwoDie() >= 10) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
