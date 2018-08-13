package t4.Player;

/**
 * Set of possible Pieces the Players can use on the board.
 * 
 * @author Teddy
 */
public enum Piece implements Comparable<Piece> {
	X ("x"), // X goes first
	O ("o"), // O goes second
	BLANK (" "); // piece not yet chosen
	
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
	 * @return Marker for this piece
	 */
	public String getMarker() {
		return this.marker;
	}
	
	public String toString() {
		return this.marker;
	}
}
