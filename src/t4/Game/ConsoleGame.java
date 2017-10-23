package t4.Game;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import t4.Board.Board;
import t4.Board.Move;
import t4.Player.*;

/**
 * A ConsoleGame is a T4 implementation to be played via the Console.
 * 
 * @author Teddy
 */
public class ConsoleGame extends Game {
	public Scanner sc;
	private boolean verbose;
	
	/**
	 * Constructs a new Game and preps for user input.
	 */
	public ConsoleGame(boolean verbose) {
		super();
		this.verbose = verbose;
		sc = new Scanner(System.in);
	}

	@Override
	public void initializePlayers() {
		try {
			for (int i = 1; i <= NUM_PLAYERS; i++) {
				addPlayer(getNewPlayer(i));
			}
			assignPieces();
		} catch (Exception e) {
			System.out.println("Unable to get user input.");
		}
	}
	
	/**
	 * Queries the user to get the name and type of a new player
	 * @param index Player number for this Game
	 * @return New Player with the input name and type
	 */
	private Player getNewPlayer(int index) {
		Player player = null;
		// input Player name
		String name = getString("Input player " + index + " name: ");

		// input Player type
		String query = "Input player type:\n\t"
				+ Player.HUMAN + " = human\n\t"
				+ Player.RANDOM_AI + " = random AI\n\t"
				+ Player.BASIC_AI + " = basic AI\n\t"
				+ Player.NEW_AI + " = new AI\n";
		while (player == null) {
			try {
				switch(getInt(query)) {
				case Player.HUMAN:
					player = new Human(this, name);
					break;
				case Player.RANDOM_AI:
					player = new RandomAI(this, name);
					break;
				case Player.BASIC_AI:
					player = new BasicAI(this, name);
					break;
				case Player.NEW_AI:
					player = new NewAI(this, name);
				default:
					break;
				}
			} catch (InputMismatchException e) {}
			if (player == null) {
				System.out.println("Invalid player type");
			}
		}
		return player;
	}

	@Override
	public void run() {
		initializePlayers();
		if (!this.isReady()) {
			System.out.println("Players not initialized.");
			return;
		}
		int matchNumber = 0;
		int numToPlay = 0;
		while (true) {
			if (numToPlay == 0) {
				numToPlay = getInt("Number of Matches to play? ", 1);
			}
			if (verbose)
				System.out.println("\nStarting Game " + ++matchNumber + "...\n");
			match = new Match(matchNumber);
			Board board = match.board;
			boolean won = false;
			// tell the players a new match is beginning
			for (Player p : players) {
				p.alertNewMatch();
			}

			int playIndex = -1;
			while (!won && board.isPlayable()) {
				playIndex = ++playIndex % Game.NUM_PLAYERS;
				Player player = players.get(playIndex);
				Move move = player.getMove();
				won = board.playMove(move);
			}
			
			// assign points
			if (won) {
				for (Player player : players) {
					if (players.indexOf(player) == playIndex) {
						if (verbose)
							System.out.println("\n--- Winner: " + player.getName() + " ---\n");
						player.addWin();
					} else {
						player.addLoss();
					}
				}
			} else {
				if (verbose)
					System.out.println("\n--- Tie game ---\n");
				for (Player player : players) {
					player.addTie();
				}
			}
			if (verbose)
				printBoard(false);
			if (--numToPlay == 0) {
				printScores();
				if (!checkContinue()) {
					System.out.println("Exiting...");
					return;
				}
			}
			// reassign pieces for new Match
			assignPieces();
		}
	}
	
	/**
	 * Randomly assigns a piece to each of the Players
	 */
	public void assignPieces() {
		long seed = System.nanoTime();
		Collections.shuffle(players, new Random(seed));
		Piece[] pieces = Piece.values();
		for (int i = 0; i < players.size() && i < pieces.length; i++) {
			players.get(i).setPiece(pieces[i]);
		}
	}
	
	/**
	 * Prints the current state of the Board
	 */
	public void printBoard(boolean indices) {
		System.out.println(match.board.toString(indices));
	}
	
	/**
	 * Prints the current scores for the Players
	 */
	public void printScores() {
		System.out.println("Current standings:");
		for (Player player : players) {
			System.out.println("\t" + player.getName() + ":\t" + player.getScore());
		}
	}
	
	/**
	 * {@inheritDoc}
	 * This implementation queries the user via the console.
	 */
	public boolean checkContinue() {
		while (true) {
			String input = getString("Play again? (y/n): ");
			if (input.equalsIgnoreCase("y")) return true;
			if (input.equalsIgnoreCase("n")) return false;
			if (input.equalsIgnoreCase("s")) {
				changePlayer();
			} else {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	/**
	 * Query the user to see which player they would like to replace
	 */
	private void changePlayer() {
		while (true) {
			String query = "Which player to replace?:\n";
			for (int i = 1; i <= players.size(); i++) {
				query += "\t" + i + " - " + players.get(i - 1).getName() + "\n";
			}
			int index = getInt(query, 0, players.size());
			try {
				players.remove(index - 1);
				players.add(index - 1, getNewPlayer(index));
				return;
			} catch (IndexOutOfBoundsException e) {
				return;
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 * This implementation queries the user via the console using
	 * the given query.
	 */
	public int getInt(String query) {
		return getInt(query, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * {@inheritDoc}
	 * This implementation queries the user via the console using
	 * the given query and minimum allowable value.
	 */
	public int getInt(String query, int min) {
		return getInt(query, min, Integer.MAX_VALUE);
	}
	
	/**
	 * {@inheritDoc}
	 * This implementation queries the user via the console using
	 * the given query and minimum/maximum allowable values.
	 */
	public int getInt(String query, int min, int max) {
		int in = -1;
		try {
			while (true) {
				try {
					System.out.print(query);
					in = sc.nextInt();
					if (in < min || in > max)
						throw new InputMismatchException();
					sc.nextLine();
					break;
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("Invalid input");
				}
			}
		} catch (Exception e)  {
			System.out.println("Error getting user input");
		}
		return in;
	}
	
	/**
	 * {@inheritDoc}
	 * This implementation queries the user via the console.
	 */
	public String getString(String query) {
		try {
			System.out.print(query);
			return sc.nextLine();
		} catch (Exception e) {
			System.out.println("Error getting user input");
			return null;
		}
	}

}
