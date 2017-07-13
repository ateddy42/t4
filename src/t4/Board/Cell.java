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
	private Player Player;
	
	/**
	 * Creates a new Cell for the given row and column indices
	 * @param row Row index of the Cell
	 * @param col Column index of the Cell
	 */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		this.Player = null;
	}
	
	/**
	 * Returns the Player that occupies this Cell
	 * @return The Player occupying this Cell
	 */
	public Player getPlayer() {
		return Player;
	}
	
	/**
	 * Sets the Player that occupies this Cell
	 * @param Player Player to occupy this Cell
	 */
	public void setPlayer(Player Player) {
		this.Player = Player;
	}
	
	/**
	 * Whether a player has played on this Cell
	 * @return Whether the cell is occupied
	 */
	public boolean isOccupied() {
		return Player != null;
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
	
	public String toString(boolean indices) {
		if (isOccupied())
			return Player.getPiece().toString();
		if (indices)
			return String.valueOf(this.row * Board.NUM_ROWS + this.col);
		return Piece.BLANK.toString();
	}
}
