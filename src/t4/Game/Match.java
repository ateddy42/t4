package t4.Game;

/**
 * A Match represents two Players playing each other in a t4 Game
 * 
 * @author Teddy
 */
public class Match implements Comparable<Match> {
	private int matchNumber;
	
	/**
	 * Starts a new Match with the specified matchNumber
	 * @param matchNumber Match number for this Match
	 */
	public Match(int matchNumber) {
		this.matchNumber = matchNumber;
	}
	
	/**
	 * Returns the match number for this Match
	 * @return Match number for this Match
	 */
	public int getMatchNumber() {
		return matchNumber;
	}

	@Override
	public int compareTo(Match o) {
		if (!(o instanceof Match))
			return 0;
		return this.matchNumber - ((Match) o).matchNumber;
	}
	
    public boolean equals(Object o) {
    	if (!(o instanceof Match))
    		return false;
    	return this.matchNumber == ((Match) o).matchNumber;
    }
}
