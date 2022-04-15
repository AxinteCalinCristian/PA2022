package compulsory.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a game board, with a bag of tiles and a dictionary
 * @author Calin Axinte
 */
@Getter
@Setter
public class Board {
	private Bag tilesBag;
	private Dictionary dictionary;
	
	public Board(String pathToDictFile) {
		dictionary = new Dictionary(pathToDictFile);
		tilesBag = new Bag();
	}
	
}
