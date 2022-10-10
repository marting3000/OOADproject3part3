package project2part2;

public class Dance extends CelebrationDecorator {
	public Dance(Celebration celebration) {
		this.celebration = celebration;
	}
	
	public String getCelebration() {
		return celebration.getCelebration() + ", Dance";
	}
}
