package t4.Game;

import t4.Board.Board;

/**
 * A Match represents two Players playing each other in a t4 Game
 * 
 * @author Teddy
 */
public class Match implements Comparable<Match> {
	private int matchNumber;
	protected Board board;
	
	/**
	 * Starts a new Match with the specified matchNumber
	 * @param matchNumber Match number for this Match
	 */
	public Match(int matchNumber) {
		this.matchNumber = matchNumber;
		setBoard(new Board());
	}
	
	/**
	 * Returns the match number for this Match
	 * @return Match number for this Match
	 */
	public int getMatchNumber() {
		return matchNumber;
	}
	
	/**
	 * Returns the Board for this Match
	 * @return Board for this Match
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Sets the Board for this Match to the given Board
	 * @param board Board for this Match
	 */
	protected void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public int compareTo(Match m) {
		return this.matchNumber - m.matchNumber;
	}
	
	@Override
    public boolean equals(Object o) {
		if (!(o instanceof Match))
			return false;
    	return this.matchNumber == ((Match) o).matchNumber;
    }
}
