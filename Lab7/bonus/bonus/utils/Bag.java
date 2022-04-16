package bonus.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private Map<Character, Pair<Integer, Integer>> letterMappings = new HashMap<>();
	
	@Getter(lombok.AccessLevel.NONE)
	@Setter(lombok.AccessLevel.NONE)
	private Random rand = new Random();
	
	public Bag() {
		initializeMappings();
		initializeBag();
	}
	
	private void initializeMappings() {
		letterMappings.put('A', new Pair<Integer, Integer>(9, 1)); 
		letterMappings.put('B', new Pair<Integer, Integer>(2, 3));
		letterMappings.put('C', new Pair<Integer, Integer>(2, 3));
		letterMappings.put('D', new Pair<Integer, Integer>(4, 2));
		letterMappings.put('E', new Pair<Integer, Integer>(12, 1));
		letterMappings.put('F', new Pair<Integer, Integer>(2, 4));
		letterMappings.put('G', new Pair<Integer, Integer>(3, 2));
		letterMappings.put('H', new Pair<Integer, Integer>(2, 4));
		letterMappings.put('I', new Pair<Integer, Integer>(9, 1));
		letterMappings.put('J', new Pair<Integer, Integer>(1, 8)); 
		letterMappings.put('K', new Pair<Integer, Integer>(1, 5));
		letterMappings.put('L', new Pair<Integer, Integer>(4, 1));
		letterMappings.put('M', new Pair<Integer, Integer>(2, 3));
		letterMappings.put('N', new Pair<Integer, Integer>(6, 1));
		letterMappings.put('O', new Pair<Integer, Integer>(8, 1));
		letterMappings.put('P', new Pair<Integer, Integer>(2, 3));
		letterMappings.put('Q', new Pair<Integer, Integer>(1, 10));
		letterMappings.put('R', new Pair<Integer, Integer>(6, 1));
		letterMappings.put('S', new Pair<Integer, Integer>(4, 1)); 
		letterMappings.put('T', new Pair<Integer, Integer>(6, 1));
		letterMappings.put('U', new Pair<Integer, Integer>(4, 1));
		letterMappings.put('V', new Pair<Integer, Integer>(2, 4));
		letterMappings.put('W', new Pair<Integer, Integer>(2, 4));
		letterMappings.put('X', new Pair<Integer, Integer>(1, 8));
		letterMappings.put('Y', new Pair<Integer, Integer>(2, 4));
		letterMappings.put('Z', new Pair<Integer, Integer>(1, 10));
	}
	
	private void initializeBag() {
		for(char c = 'A'; c <= 'Z'; c++) {
			for(int i=0;i<letterMappings.get(c).first();i++) {
				addTile(new Tile(c, letterMappings.get(c).second()));
			}
		}
	}
	
	/**
	 * Adds the tile to the bag
	 * @param tile
	 * @return true if it has been added, otherwise false
	 */
	public boolean addTile(Tile tile) {
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
	
	public void addLetterMapping(Character c, Pair<Integer, Integer> mapping) {
		letterMappings.put(c, mapping);
	}
	
}
