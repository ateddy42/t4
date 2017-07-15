package t4.Player;

import t4.Board.Board;
import t4.Board.Move;
import t4.Game.ConsoleGame;
import t4.Game.Game;

/**
 * A Human is a type of Player that is controlled by user input
 * 
 * @author Teddy
 */
public class Human extends Player {

	public Human(Game game, String name) {
		super(game, name);
	}

	@Override
	public Move getMove() {
		if (this.game instanceof ConsoleGame) {
			ConsoleGame game = (ConsoleGame) this.game;
			game.printBoard(true);
			while (true) {
				int index = game.getInt("Enter an index: ", 0, Board.NUM_CELLS - 1);
				int row = index / Board.NUM_ROWS;
				int col = index % Board.NUM_COLS;
				Move move = new Move(game.getMatch().getBoard(), row, col, this);
				if (move.isValid())
					return move;
				System.out.println("Invalid move");
			}
		}
		return null;
	}

	@Override
	public void alertNewMatch() {
		System.out.println("\n--- New Match ---\n");
	}

}
