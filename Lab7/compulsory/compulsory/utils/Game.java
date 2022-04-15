package compulsory.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a game instance.
 * @author Calin Axinte
 *
 */
@Getter
@Setter
public class Game {
	private Board gameBoard;
	private List<Player> players = Collections.synchronizedList(new ArrayList<>());
	private Integer tilesPerPlayer = 7;
	private ConcurrentHashMap<Player, Integer> currentStandings = new ConcurrentHashMap<>();
	private Integer currentNoOfPlayers = 0;
	
	public Game(String pathToDictFile) {
		gameBoard = new Board(pathToDictFile);
	}
	
	public boolean addPlayer(Player player) {
		if(players.contains(player)) {
			return false;
		}
		players.add(player);
		player.setGame(this);
		return true;
	}
	
	/**
	 * Starts the game.
	 */
	public void play() {
		if(players.size() == 0) {
			System.out.println("No players registered");
			return;
		}
		
		currentStandings.clear();
		currentNoOfPlayers = players.size();
		
		for(int i=0;i<players.size();i++) {
			Player p = players.get(i);
			p.setNextPlayer(players.get((i+1) % players.size()));
			Thread thread = new Thread(p);
			thread.start();
		}
		
		synchronized(players.get(0)) {
			players.get(0).notify();
		}
	}
	
	private Integer calculateWordPoints(List<Tile> tiles) {
		Integer worth = 0;
		
		for(Tile t : tiles) {
			worth += t.getPoints();
		}
		
		return worth;
	}
	
	/**
	 * Submits a word from a player and adds the corresponding points to the player's standings.
	 * @param p
	 * @param tiles
	 */
	public void submitWord(Player p, List<Tile> tiles) {
		if(!currentStandings.contains(p)) {
			currentStandings.put(p, 0);
		}
		
		currentStandings.put(p, currentStandings.get(p) + calculateWordPoints(tiles));
	}
	
	/**
	 * Prints the player standings.
	 */
	public void printFinalStandings() {
		for(ConcurrentHashMap.Entry<Player, Integer> entry : currentStandings.entrySet()) {
			System.out.println("[ " + entry.getKey().getName() + " ] : " + entry.getValue() + " points");
		}
	}
	
	/**
	 * Checks if the game is over.
	 * @return whether the game is over or not.
	 */
	public boolean isGameOver() {
		if(gameBoard.getTilesBag().getTiles().size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Decreases the player count.
	 */
	public void removePlayer() {
		currentNoOfPlayers--;
		if(currentNoOfPlayers <= 0) {
			printFinalStandings();
		}
	}
}
