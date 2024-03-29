package t4;

import t4.Game.ConsoleGame;
import t4.Game.Game;

/**
 * The main class of the T4 project. Creates a new game and runs it.
 * 
 * @author Teddy
 */
public class T4 {
	
	/**
	 * Creates a new game and runs it.
	 * @param args Program arguments
	 */
	public static void main(String[] args) {
		boolean verbose = false;
		for (String arg : args) {
			if (arg.equals("-v"))
				verbose = true;
		}
		Game game = new ConsoleGame(verbose);
		game.run();
	}

}
