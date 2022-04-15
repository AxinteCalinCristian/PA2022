package homework.main;

import homework.utils.Game;
import homework.utils.Player;

public class Homework {
	public static void main(String[] args) {
		Game game = new Game("./dictionaryFile.txt");
		game.addPlayer(new Player("Player 1"));
		game.addPlayer(new Player("Player 2"));
		game.addPlayer(new Player("Player 3"));
		game.play();
	}
}
