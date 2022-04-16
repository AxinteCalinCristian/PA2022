package bonus.main;

import bonus.dictionary.LookupType;
import bonus.utils.Game;
import bonus.utils.Player;

public class Bonus {
	public static void main(String[] args) {
		Game game = new Game("./dictionaryFile.txt");
		game.addPlayer(new Player("Player 1"));
		game.addPlayer(new Player("Player 2"));
		game.addPlayer(new Player("Player 3"));
		//game.play();
		System.out.println(game.getGameBoard().getDictionary().containsPrefix("AGE", LookupType.PrefixTree));
		System.out.println(game.getGameBoard().getDictionary().containsPrefix("AGE", LookupType.Multithreaded));
	}
}
