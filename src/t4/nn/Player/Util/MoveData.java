package t4.nn.Player.Util;

/**
 * MoveData holds records of each move the AI plays for a given Match.
 * 
 * @author Teddy
 */
public class MoveData {
	/* Input values for each round */
	public double[] inputs;
	/* Desired output values for each round:
	 * 0 if occupied, 1 if current move, null if empty */
	public double[] desired;
	
	/**
	 * Constructs a new MoveData() object and initializes the Collections.
	 */
	public MoveData(double[] inputs, double[] desired) {
		this.inputs  = inputs;
		this.desired = desired;
	}
}