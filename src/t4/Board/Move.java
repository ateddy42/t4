package t4.Board;

import t4.Player.Player;

/**
 * A Move is a representation of a Player attempting to play in a specific
 * location on the Board, identified by <code>row</code> and <code>col</code>.
 * 
 * @author Teddy
 */
public class Move {
	private Board board;
	private int row;
	private int col;
	private Player Player;
	
	/**
	 * Creates the Move the Player is attempting to make
	 * @param board Current state of the Board
	 * @param row Row index of the desired Cell
	 * @param col Column index of the desired Cell
	 * @param Player Player making the Move
	 */
	public Move(Board board, int row, int col, Player Player) {
		this.board = board;
		this.row = row;
		this.col = col;
		this.Player = Player;
	}
	
	/**
	 * Returns the index of the row this Move is for
	 * @return Row index
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the index of the column this Move is for
	 * @return Column index
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Returns the Player playing this Move
	 * @return Player playing this Move
	 */
	public Player getPlayer() {
		return Player;
	}
	
	/**
	 * Whether this is a valid Move for this Board
	 * @return Whether this Move is valid
	 */
	public boolean isValid() {
		return this.board.isValidMove(this);
	}
	
	/**
	 * Checks whether the row and column indices are within the
	 * boundaries of the Board
	 * @return Whether the indices are within the boundaries
	 */
	public boolean inBoundaries() {
		return row >= 0 && row < Board.NUM_ROWS
				&& col >= 0 && col < Board.NUM_COLS;
	}
	
	/**
	 * Whether this Move is a winning Move, i.e. if the Player
	 * plays this, will they win the Match.
	 * @return Whether the Move is a winning Move
	 */
	public boolean isWinning() {
		if (checkHorizontal(row)) return true;
		if (checkVertical(col)) return true;
		if (row == col)
			if (checkDiag1()) return true;
		if (row + col == Board.NUM_ROWS - 1)
			if (checkDiag2()) return true;
		return false;
	}
	
	/**
	 * Whether the Player has played in every Cell in the given row
	 * @param row Which row to check the Cells for
	 * @return Whether the Player has played in every Cell
	 */
	private boolean checkHorizontal(int row) {
		for (int j = 0; j < Board.NUM_COLS; j++) {
			if (j == this.col) continue;
			if (!board.cells[row][j].isOccupied())
				return false;
			if (!board.cells[row][j].getPlayer().equals(Player))
				return false;
		}
		return true;
	}
	
	/**
	 * Whether the Player has played in every Cell in the given column
	 * @param col Which column to check the Cells for
	 * @return Whether the Player has played in every Cell
	 */
	private boolean checkVertical(int col) {
		for (int i = 0; i < Board.NUM_ROWS; i++) {
			if (i == this.row) continue;
			if (!board.cells[i][col].isOccupied())
				return false;
			if (!board.cells[i][col].getPlayer().equals(Player))
				return false;
		}
		return true;
	}
	
	/**
	 * Whether the Player has played in every Cell in the diagonal running
	 * from the upper left to the lower right
	 * @return Whether the Player has played in every Cell
	 */
	private boolean checkDiag1() {
		for (int i = 0, j = 0; i < Board.NUM_ROWS && j < Board.NUM_COLS; i++, j++) {
			if (i == this.row && j == this.col) continue;
			if (!board.cells[i][j].isOccupied())
				return false;
			if (!board.cells[i][j].getPlayer().equals(Player))
				return false;
		}
		return true;
	}
	
	/**
	 * Whether the Player has played in every Cell in the diagonal running
	 * from the lower left to the upper right
	 * @return Whether the Player has played in every Cell
	 */
	private boolean checkDiag2() {
		for (int i = Board.NUM_ROWS - 1, j = 0; i >= 0 && j < Board.NUM_COLS; i--, j++) {
			if (i == this.row && j == this.col) continue;
			if (!board.cells[i][j].isOccupied())
				return false;
			if (!board.cells[i][j].getPlayer().equals(Player))
				return false;
		}
		return true;
	}
}
