package project2part2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tracker {
	private FileWriter outputFile;
	private Game game;
	
	public Tracker(Game game) {
		try {
			outputFile = new FileWriter("Tracker.txt");
		} catch (IOException e) {
	      	System.out.println("An error occurred.");
	      	e.printStackTrace();
	    }
		this.game = game;
	}
	
	public void update(ArrayList<Adventurer> adventurers, ArrayList<Creature> creatures) {
		game.setAdventurers(adventurers);
		game.setCreatures(creatures);
	}

	public void update(Adventurer adventurer) {
		
	}
	
	protected void finalize() {
		try {
			int activeAdventurers = 0;
			int activeCreatures = 0;
			String treasures = "";
			
			outputFile.write("Turn number: " + game.getTurnCounter());
			outputFile.write("");
			
			for (Adventurer ad: game.getAdventurers()) {
				if (ad.getIsDead() == false) {
					activeAdventurers++;
				}
			}
			outputFile.write("Total Active Adventurers: " + activeAdventurers);
			outputFile.write("Adventurers      Room      Damage      Treasure");
			for (Adventurer ad: game.getAdventurers()) {
				treasures = "";
				for (Treasure t: ad.getHeldTreasures()) {
					treasures += t.getType();
					treasures += ", ";
				}
				treasures.substring(0, treasures.length() - 2);  
				outputFile.write(ad.getName() + "    " + ad.getX() + "-" + ad.getY() + "-" + ad.getZ() + "     " + ad.getDamage() + "      " + treasures);
			}
			outputFile.write("");
			
			for (Creature cr: game.getCreatures()) {
				if (cr.getIsDead() == false) {
					activeCreatures++;
				}
			}
			
			outputFile.write("Total Active Creatures: " + activeCreatures);
			outputFile.write("Creatures      Room");
			for (Creature cr: game.getCreatures()) {
				outputFile.write(cr.getName() + "     " + cr.getX() + "-" + cr.getY() + "-" + cr.getZ());
			}
			
			outputFile.close();
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
}
