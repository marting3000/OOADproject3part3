package project2part2;

public class Spin extends CelebrationDecorator {
	public Spin(Celebration celebration) {
		this.celebration = celebration;
	}
	
	public String getCelebration() {
		return celebration.getCelebration() + ", Spin";
	}
}
