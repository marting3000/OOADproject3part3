package project2part2;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer {
	private FileWriter outputFile;
	
	public Logger(int turnNum) {
		try {
			outputFile = new FileWriter("Logger-" + turnNum + ".txt");
		} catch (IOException e) {
	      	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
	}

	public void update(ArrayList<Adventurer> adventurers, ArrayList<Creature> creatures) {

	}

	public void update(Adventurer adventurer) {
		write(adventurer.getUpdate());
	}
	
	public void write(String update) {
		try {
			
			outputFile.write(update);
			
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			outputFile.close();
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
}
