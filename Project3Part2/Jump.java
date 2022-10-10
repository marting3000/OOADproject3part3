package project2part2;

public class Jump extends CelebrationDecorator{
	public Jump(Celebration celebration) {
		this.celebration = celebration;
	}
	
	public String getCelebration() {
		return celebration.getCelebration() + ", Jump";
	}
}
