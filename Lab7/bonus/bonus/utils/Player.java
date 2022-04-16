package bonus.utils;

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
	@Getter(lombok.AccessLevel.NONE)
	@Setter(lombok.AccessLevel.NONE)
	private StringBuilder stringBuilder = new StringBuilder();
	
	@EqualsAndHashCode.Exclude
	private Player nextPlayer;
	
	public Player(String name) {
		this.name = name;
	}
	
	private List<Tile> genNewWord(Integer n, List<Tile> tiles, Integer len, Integer L) {
		List<Tile> word = new ArrayList<>();
		
		for (int i = 0; i < L; i++) {
			word.add(tiles.get(n % len));
			n /= len;
		}
		return word;
	}

	private List<Tile> getBestWord(List<Tile> tiles, Integer len, Integer L) {
		List<Tile> best = null;
		Integer currMax = 0;
		for (int i = 0; i < (int)Math.pow(len, L); i++) {
			List<Tile> res = genNewWord(i, tiles, len, L);
			Integer resScore = game.getWordScore(res);
			
			stringBuilder.delete(0, stringBuilder.capacity());
			for(Tile t : res) {
				stringBuilder.append(t.getLetter());
			}
			
			if(resScore > currMax && game.getGameBoard().getDictionary().containsWord(stringBuilder.toString())) {
				currMax = resScore;
				best = res;
			}
		}
		
		return best;
	}
	
	/**
	 * Gets a valid word if it can be created.
	 * @param chosenTiles
	 * @return
	 */
	private List<Tile> getWord(List<Tile> tiles) {
		List<Tile> chosenTiles = new ArrayList<>(tiles);
		List<Tile> word = null;
		
		for(int i=1;i <= 7;i++) {
			List<Tile> res = getBestWord(chosenTiles, chosenTiles.size(), i);
			if(res != null && (word == null || (word != null && game.getWordScore(res) > game.getWordScore(word)))) {
				word = res;
			}
		}

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
			game.getGameBoard().getTilesBag().addTiles(chosenTiles);
			System.out.println("[ " + this.name + " ] : no valid word found for tiles " + chosenTiles);
			return false;
		}
		System.out.println("[ " + this.name + " ] chose word " + word);
		game.submitWord(this, word);
		
		return true;
	}
	
	@Override
	public void run() {
		System.out.println("[ " + this.name + " ] started playing.");
		while(true) {
			try {
				synchronized(game) {
					game.signalWaiting();
					game.wait();
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
