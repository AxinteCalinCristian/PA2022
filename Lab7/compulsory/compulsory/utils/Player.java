package compulsory.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * Represents a player.
 * @author Calin Axinte
 */
@Log
@Getter
@Setter
@EqualsAndHashCode
public class Player implements Runnable {
	
	@EqualsAndHashCode.Exclude
	private Game game;
	private String name;
	
	@EqualsAndHashCode.Exclude
	private Player nextPlayer;
	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Gets a valid word if it can be created.
	 * @param chosenTiles
	 * @return
	 */
	private List<Tile> getWord(List<Tile> chosenTiles) {
		List<Tile> word = new ArrayList<>(chosenTiles);
		word.sort((t1, t2) -> t1.getLetter().compareTo(t2.getLetter()));
		
		String wordStr = "";
		for(Tile t : word) {
			wordStr += t.getLetter();
		}
		
		if(game.getGameBoard().getDictionary().containsWord(wordStr)) {
			return word;
		}
		//return null;
		return word;
	}
	
	/**
	 * Chooses a word if possible and signals the game if a move has been made.
	 * @return whether there has been a word chosen or not.
	 */
	private boolean chooseWord() {
		if(game == null) {
			return false;
		}
		
		List<Tile> chosenTiles = game.getGameBoard().getTilesBag().extractTiles(game.getTilesPerPlayer());
		if(chosenTiles.size() == 0) {
			return false;
		}
		
		List<Tile> word = getWord(chosenTiles);
		if(word == null || word.size() == 0) {
			//game.getGameBoard().getTilesBag().addTiles(chosenTiles);
			return false;
		}
		System.out.println(this.name + " chose word " + word);
		game.submitWord(this, word);
		
		return true;
	}
	
	@Override
	public void run() {
		System.out.println(this.name + " started playing");
		while(true) {
			try {
				synchronized(this) {
					if(chooseWord()) {
						Thread.sleep(50);
					}
					if(game.isGameOver()) {
						break;
					}
				}
			} catch (InterruptedException e) {
				log.severe("Error running thread for player " + this.name);
				e.printStackTrace();
				break;
			}
		}
		game.removePlayer();
	}
}
