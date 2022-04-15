package compulsory.main;

import compulsory.utils.Game;
import compulsory.utils.Player;

public class Compulsory {
	public static void main(String[] args) {
		Game game = new Game("./dictionaryFile.txt");
		game.addPlayer(new Player("Player 1"));
		game.addPlayer(new Player("Player 2"));
		game.addPlayer(new Player("Player 3"));
		game.play();
	}
}
