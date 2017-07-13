package t4.Game;

import java.util.ArrayList;
import java.util.Collection;

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
	
	private Collection<Match> matches;
	private Player[] players;
	
	/**
	 * Initializes the Players, and starts a new Match
	 */
	public Game() {
		matches = new ArrayList<>();
		players = new Player[NUM_PLAYERS];
		initializePlayers();
		start();
	}
	
	/**
	 * Adds a new Match to this Game
	 * @param match Match to add to this Game
	 */
	public void addMatch(Match match) {
		this.matches.add(match);
	}
	
	/**
	 * Adds a new Player to this Game at the specified index
	 * @param player Player to add to the Game
	 * @param index Index of the player [0, NUM_PLAYERS)
	 */
	public void addPlayer(Player player, int index) {
		players[index] = player;
	}
	
	/**
	 * Return the number of Matches in this Game
	 * @return Number of Matches in this Game
	 */
	public int getNumMatches() {
		return this.matches.size();
	}
	
	/**
	 * Initialize the Players array for this Game
	 */
	public abstract void initializePlayers();
	
	/**
	 * Start the Game, and creates and plays Matches with the Players until exit
	 */
	public abstract void start();
}
