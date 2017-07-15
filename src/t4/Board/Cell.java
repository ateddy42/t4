package t4.Board;

import t4.Player.Piece;
import t4.Player.Player;

/**
 * A Cell represents one square on a t4 board. It is identified by
 * an index for both the row and column of the Cell. Only one Player
 * is allowed to play in a Cell for a given Board.
 * 
 * @author Teddy
 */
public class Cell {
	private int row;
	private int col;
	private Player player;
	
	/**
	 * Creates a new Cell for the given row and column indices
	 * @param row Row index of the Cell
	 * @param col Column index of the Cell
	 */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		this.player = null;
	}
	
	/**
	 * Returns the Player that occupies this Cell
	 * @return The Player occupying this Cell
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the Player that occupies this Cell
	 * @param Player Player to occupy this Cell
	 */
	public void setPlayer(Player Player) {
		this.player = Player;
	}
	
	/**
	 * Whether a player has played on this Cell
	 * @return Whether the cell is occupied
	 */
	public boolean isOccupied() {
		return player != null;
	}
	
	/**
	 * Returns the index of the Row this Cell is for
	 * @return Row index of this Cell
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Returns the index of the Column this Cell is for
	 * @return Column index of this Cell
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Returns the index [0,9) of this Cell
	 * @return Index of this Cell
	 */
	public int getIndex() {
		return row * Board.NUM_ROWS + col;
	}
	
	/**
	 * Returns the row of the given index
	 * @param index Index of the cell [0,9)
	 * @return Row index of the given index
	 */
	public static int getRow(int index) {
		return index / Board.NUM_ROWS;
	}
	
	/**
	 * Returns the column of the given index
	 * @param index Index of the cell [0,9)
	 * @return Column index of the given index
	 */
	public static int getCol(int index) {
		return index % Board.NUM_COLS;
	}
	
	public String toString(boolean indices) {
		if (isOccupied())
			return player.getPiece().toString();
		if (indices)
			return String.valueOf(getIndex());
		return Piece.BLANK.toString();
	}
}
