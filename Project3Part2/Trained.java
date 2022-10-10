package project2part2;

public class Trained implements Combat {
	public int fight() {
		return Utilities.rollTwoDie() + 1;
	}
}
