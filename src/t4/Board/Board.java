package t4.Board;

/**
 * A Board represents the collection of Cells that make up a t4 board.
 * 
 * @author Teddy
 */
public class Board {
	public static final int NUM_ROWS = 3;
	public static final int NUM_COLS = NUM_ROWS;
	public static final int NUM_CELLS = NUM_ROWS * NUM_COLS;
	public Cell[][] cells;
	
	/**
	 * Initializes the Board with an empty Cell in every position of the Board
	 */
	public Board() {
		cells = new Cell[NUM_ROWS][NUM_COLS];
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}
	
	/**
	 * Checks if the given Move is valid, i.e. if the cell is unoccupied
	 * @param move Move to be played
	 * @return Whether the Move is valid
	 */
	public boolean isValidMove(Move move) {
		Cell cell = cells[move.getRow()][move.getCol()];
		return !cell.isOccupied();
	}
	
	/**
	 * Plays the given Move, and returns whether the Player won
	 * @param move Move to be played
	 * @return Whether the Player won
	 */
	public boolean playMove(Move move) {
		if (!isValidMove(move)) return false;
		cells[move.getRow()][move.getCol()].setPlayer(move.getPlayer());
		return move.isWinning();
	}
}
