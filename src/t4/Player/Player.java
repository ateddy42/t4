package t4.Player;

import t4.Board.Board;
import t4.Board.Move;

/**
 * A Player represents a participant in the t4 game. Each Player
 * has a name and an identifying piece that they'll play.
 * 
 * @author Teddy
 */
public abstract class Player {

	private String name;
	private Piece piece;
	
	/**
	 * Sets the Player's name and identifying piece
	 * @param name Name of the Player
	 * @param piece Identifying piece the Player uses
	 */
	public Player(String name, Piece piece) {
		setName(name);
		setPiece(piece);
	}
	
	/**
	 * Returns the name of the Player
	 * @return Name of the Player
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the identifying piece this Player uses
	 * @return The piece the Player uses
	 */
	public Piece getPiece() {
		return this.piece;
	}
	
	/**
	 * Sets the name of this Player
	 * @param name Name of the Player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the Piece this Player uses
	 * @param piece The piece the Player uses
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	/**
	 * Returns the move this Player chose to make for the
	 * given state of the Board.
	 * @param board Current state of the t4 board
	 * @return Move that the Player chose to make
	 */
	public abstract Move getMove(Board board);
}