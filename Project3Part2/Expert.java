package project2part2;

public class Expert implements Combat {
	public int fight() {
		return Utilities.rollTwoDie() + 2;
	}
}
