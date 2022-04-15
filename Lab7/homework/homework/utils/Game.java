package homework.utils;

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
	private Integer playersWaiting = 0;
	private Timekeeper timeKeeper = new Timekeeper(60, this);
	
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
		
		new Thread(() -> {
		    while(true) {
		    	if(isGameOver()) {
		    		
		    		break;
		    	}
		    	if(playersWaiting >= players.size()) {
					synchronized(this) {
						this.notifyAll();
						System.out.println("Remaining tiles: " + gameBoard.getTilesBag().getTiles().size());
						playersWaiting = 0;
					}
				}
		    }
		}).start();
		
		currentStandings.clear();
		currentNoOfPlayers = players.size();
		timeKeeper.resetTimekeeper();
		
		new Thread(timeKeeper).start();
		
		for(int i=0;i<players.size();i++) {
			Player p = players.get(i);
			if(!currentStandings.contains(p)) {
				currentStandings.put(p, 0);
			}
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
		
		return worth * tiles.size();
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
		Player winner = null;
		Integer currMax = 0;
		
		System.out.println("Final standings:");
		for(ConcurrentHashMap.Entry<Player, Integer> entry : currentStandings.entrySet()) {
			System.out.println("[ " + entry.getKey().getName() + " ] : " + entry.getValue() + " points");
			if(winner == null || entry.getValue() > currMax) {
				currMax = entry.getValue();
				winner = entry.getKey();
			}
		}
		
		List<Player> winners = new ArrayList<>();
		for(ConcurrentHashMap.Entry<Player, Integer> entry : currentStandings.entrySet()) {
			if(winner != null && entry.getValue() == currMax) {
				winners.add(entry.getKey());
			}
		}
		
		System.out.print("Winners:");
		for(Player p : winners) {
			System.out.print(" [ " + p.getName() + " ] ");
		}
	}
	
	/**
	 * Checks if the game is over.
	 * @return whether the game is over or not.
	 */
	public boolean isGameOver() {
		if(gameBoard.getTilesBag().getTiles().size() == 0 || timeKeeper.getIsTimeUp()) {
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
	
	public Integer getWordScore(List<Tile> tiles) {
		return calculateWordPoints(tiles);
	}
	
	public void signalWaiting() {
		playersWaiting++;
	}
}
