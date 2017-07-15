package t4.Player;

import java.util.concurrent.ThreadLocalRandom;

import t4.Board.Board;
import t4.Board.Move;
import t4.Game.Game;

/**
 * A RandomAI is a type of Player that plays in a random Cell
 * 
 * @author Teddy
 */
public class RandomAI extends Player {

	public RandomAI(Game game, String name) {
		super(game, name);
	}

	@Override
	public Move getMove() {
		Board board = game.getMatch().getBoard();
		while (true) {
			int row = ThreadLocalRandom.current().nextInt(0, Board.NUM_ROWS);
			int col = ThreadLocalRandom.current().nextInt(0, Board.NUM_COLS);
			Move move = new Move(board, row, col, this);
			if (move.isValid())
				return move;
		}
	}

}
