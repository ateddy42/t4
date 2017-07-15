package t4.Player;

import t4.Board.Move;
import t4.Game.Game;

/**
 * A Player represents a participant in the t4 game. Each Player
 * has a name and an identifying piece that they'll play with.
 * 
 * @author Teddy
 */
public abstract class Player implements Comparable<Player> {
	public static final int HUMAN = 1, RANDOM_AI = 2, BASIC_AI = 3;

	private String name;
	private Piece piece;
	protected int wins;
	protected int losses;
	protected int ties;
	protected Game game;

	/**
	 * Sets the Player's name to the given input, identifying piece
	 * to Piece.BLANK, and the score to 0
	 * @param name Name of the Player
	 */
	public Player(Game game, String name) {
		setGame(game);
		setName(name);
		setPiece(Piece.BLANK);
		setScore(0);
	}

	/**
	 * Returns the Game this Player is in
	 * @return Game this Player is in
	 */
	public Game getGame() {
		return this.game;
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
	 * Returns the number of wins for this Player
	 * @return Player's number of wins
	 */
	public int getWins() {
		return this.wins;
	}
	
	/**
	 * Returns the number of losses for this Player
	 * @return Player's number of losses
	 */
	public int getLosses() {
		return this.losses;
	}
	
	/**
	 * Returns the number of ties for this Player
	 * @return Player's number of ties
	 */
	public int getTies() {
		return this.ties;
	}
	
	/**
	 * Returns a string representing the Player's score
	 * @return String with number of wins, losses, and ties
	 */
	public String getScore() {
		return "W: " + wins + " L: " + losses + " T: " + ties;
	}

	/**
	 * Sets the Game this Player is in
	 * @param game Game this Player is in
	 */
	public void setGame(Game game) {
		this.game = game;
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
	 * Sets the Player's score
	 * @param score Score of the Player
	 */
	public void setScore(int wins) {
		setScore(wins, 0, 0);
	}
	
	/**
	 * Sets the Player's number of wins, losses, and ties
	 * @param wins Number of wins
	 * @param losses Number of losses
	 * @param ties Number of ties
	 */
	public void setScore(int wins, int losses, int ties) {
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
	}

	/**
	 * Returns the move this Player chose to make for the
	 * current state of the Board.
	 * @return Move that the Player chose to make
	 */
	public abstract Move getMove();

	/**
	 * Tells the player that they won the current Match
	 */
	public void addWin() {
		wins++;
	}

	/**
	 * Tells the player that they lost the current Match
	 */
	public void addLoss() {
		losses++;
	}

	/**
	 * Tells the player that they tied the current Match
	 */
	public void addTie() {
		ties++;
	}
	
	/**
	 * Tells the player that a new Match is starting
	 */
	public void alertNewMatch() {};

	public int compareTo(Player p) {
		return this.piece.compareTo(p.piece);
	}
	
	public String toString() {
		return this.piece + " - " + this.name;
	}
}