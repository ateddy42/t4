package t4.Game;

import java.util.ArrayList;

import t4.Player.Piece;
import t4.Player.Player;

/**
 * A Game represents an instance of two Players playing t4 against
 * each other. Each Game can have multiple Matches, as Players can
 * play each other multiple times in the same Game.
 * 
 * @author Teddy
 */
public abstract class Game {
	public static final int NUM_PLAYERS = 2;
	
	protected Match match;
	protected ArrayList<Player> players;
	
	/**
	 * Initializes the Players, and start gameplay.
	 */
	public Game() {
		players = new ArrayList<>();
	}
	
	/**
	 * Whether the Game is properly configured to begin play. This
	 * includes checking whether the Players are non-null and have
	 * assigned pieces.
	 * @return Whether the Game is ready to begin play
	 */
	protected boolean isReady() {
		if (players.size() != 2) return false;
		for (Player player : players) {
			if (player.getPiece().equals(Piece.BLANK)) return false;
		}
		return true;
	}
	
	/**
	 * Returns the current Match for this Game.
	 * @return Match for this Game
	 */
	public Match getMatch() {
		return this.match;
	}
	
	/**
	 * Adds a new Match to this Game.
	 * @param match Match to add to this Game
	 */
	protected void setMatch(Match match) {
		this.match = match;
	}
	
	/**
	 * Adds a new Player to this Game at the specified index.
	 * @param player Player to add to the Game
	 * @param index Index of the player [0, NUM_PLAYERS)
	 */
	protected void addPlayer(Player player) {
		players.add(player);
	}
	
	/**
	 * Return the number of Matches in this Game.
	 * @return Number of Matches in this Game
	 */
	public int getNumMatches() {
		return this.match.getMatchNumber();
	}
	
	/**
	 * Initialize the Players array for this Game.
	 */
	protected abstract void initializePlayers();
	
	/**
	 * Start the Game, and creates and plays Matches with the
	 * Players until exit.
	 */
	public abstract void run();
	
	/**
	 * Checks whether to continue playing Matches.
	 * @return Whether to continue playing
	 */
	public abstract boolean checkContinue();
	
	/**
	 * Returns the next integer entered to the Console.
	 * @param query Query to display to the user
	 * @return Next integer entered by the user
	 */
	public abstract int getInt(String query);
	
	/**
	 * Returns the next integer entered to the Console that falls
	 * between min and max, inclusive.
	 * @param query Query to display to the user
	 * @param min Minimum value to accept as input
	 * @param max Maximum value to accept as input
	 * @return Next integer entered by the user
	 */
	public abstract int getInt(String query, int min, int max);
	
	/**
	 * Returns the next String entered to the Console.
	 * @param query Query to display to the user
	 * @return Next string entered by the user
	 */
	public abstract String getString(String query);
}
