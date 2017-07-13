package t4.Player;

/**
 * Set of possible Pieces the Players can use on the board.
 * 
 * @author Teddy
 */
public enum Piece {
	X ("x"), // X goes first
	O ("o"); // O goes second
	
	private final String marker;
	
	/**
	 * Creates the Piece with the given String marker
	 * @param marker
	 */
	private Piece(String marker) {
		this.marker = marker;
	}
	
	/**
	 * Returns the marker for this Piece
	 */
	public String toString() {
		return this.marker;
	}
}
