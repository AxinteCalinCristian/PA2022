package compulsory.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a bag of tiles.
 * @author Calin Axinte
 */
@Getter
@Setter
public class Bag {
	private List<Tile> tiles = Collections.synchronizedList(new ArrayList<>());
	
	@Getter(lombok.AccessLevel.NONE)
	@Setter(lombok.AccessLevel.NONE)
	private Random rand = new Random();
	
	public Bag() {
		initializeBag();
	}
	
	private void initializeBag() {
		
		for(char c = 'A'; c <= 'Z'; c++) {
			for(int i=0;i<10;i++) {
				addTile(new Tile(c, Math.abs(rand.nextInt()) % 26 + 1));
			}
		}
	}
	
	/**
	 * Adds the tile to the bag
	 * @param tile
	 * @return true if it has been added, otherwise false
	 */
	public boolean addTile(Tile tile) {
		if(tiles.contains(tile)) {
			return false;
		}
		tiles.add(tile);
		return true;
	}
	
	public void resetBag() {
		tiles.clear();
		initializeBag();
	}
	
	/**
	 * Extracts count amount of tiles from the bag
	 * @param count
	 * @return the extracted tiles
	 */
	public List<Tile> extractTiles(Integer count) {
		List<Tile> extracted = new ArrayList<>();
		
		synchronized(tiles) {
			while(count > 0) {
				if(tiles.size() > 0) {
					int idx = Math.abs(rand.nextInt()) % tiles.size();
					Tile extrTile = tiles.remove(idx);
					extracted.add(extrTile);
				}
				count--;
			}
		}
		
		return extracted;
	}
	
	/**
	 * Adds the tiles to the bag
	 * @param tiles
	 */
	public void addTiles(List<Tile> tiles) {
		for(Tile t : tiles) {
			this.addTile(t);
		}
	}
}
