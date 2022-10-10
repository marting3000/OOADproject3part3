package project2part2;

//Including needed files
import java.util.ArrayList;

public interface Observer {
	public void update(ArrayList<Adventurer> adventurers, ArrayList<Creature> creatures);
	public void update(Adventurer adventurer);
}
