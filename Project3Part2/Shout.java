package project2part2;

public class Shout extends CelebrationDecorator {
	public Shout(Celebration celebration) {
		this.celebration = celebration;
	}
	
	public String getCelebration() {
		return celebration.getCelebration() + ", Shout";
	}
}
