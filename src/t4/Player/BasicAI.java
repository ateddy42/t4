package t4.Player;

import java.util.ArrayList;

import t4.Board.Board;
import t4.Board.Cell;
import t4.Board.Move;
import t4.Game.Game;
import t4.nn.Layer;
import t4.nn.NeuralNet;
import t4.nn.Activation.Sigmoid;

public class BasicAI extends Player {
	private static final int NUM_INPUTS = 18;
	private static final int NUM_HIDDEN_LAYERS = 1;
	private static final int NUM_HIDDEN_NEURONS = 10;
	private static final int NUM_OUTPUTS = 9;
	private static final double PAYOFF_WIN = 1;
	private static final double PAYOFF_LOSS = -1;
	private static final double PAYOFF_TIE = 0.1;
	private static final double DIMINISH_RATE = 0.5;
	private static final double OUTPUT_THRESHOLD = 0.6;

	private NeuralNet nn;
	private ArrayList<MoveData> data;

	/**
	 * Creates a Basic AI with the default values for NeuralNet
	 * learning rate and bias value, but with the {@link Sigmoid}
	 * activation function
	 * @param game Game this AI is playing
	 * @param name Name of this AI
	 */
	public BasicAI(Game game, String name) {
		super(game, name);
		nn = new NeuralNet(new Sigmoid());
		data = new ArrayList<>();
		// Create Input Layer
		new Layer(nn, NUM_INPUTS, "Input Layer");

		// Create Hidden Layer(s)
		for (int i = 0; i < NUM_HIDDEN_LAYERS; i++) {
			new Layer(nn, NUM_HIDDEN_NEURONS, "Hidden Layer " + i);
		}

		// Create Output Layer
		new Layer(nn, NUM_OUTPUTS, "Output Layer");
	}

	@Override
	public Move getMove() {		
		Board board = game.getMatch().getBoard();
		double[] inputs = new double[NUM_INPUTS];
		double[] desired = new double[NUM_OUTPUTS];
		
		// loop through all Cells to get input values for NeuralNet
		for (int i = 0; i < Board.NUM_ROWS; i++) {
			for (int j = 0; j < Board.NUM_COLS; j++) {
				Cell cell = board.cells[i][j];
				int index = cell.getIndex();
				// If cell is occupied, set desired output to 0
				desired[index] = 0;
				Player player = cell.getPlayer();
				// Set input values for 1 if occupied, 0 otherwise
				inputs[2 * index] = (player != null && player.equals(this)) ? 1 : 0;
				inputs[2 * index + 1] = (player != null && !player.equals(this)) ? 1 : 0;
			}
		}
		
		// update input values for NeuralNet
		try {
			nn.setInputValues(inputs);
		} catch (Exception e) {
			System.out.println("ERROR: Unable to set input values");
			return null;
		}
		
		// get output from NeuralNet and sort by decreasing value
		double[] output = nn.getOutputValues();
		ArrayList<Entry> entries = new ArrayList<>();
		double totalProb = 0;
		for (int i = 0; i < output.length; i++) {
		    if (output[i] > OUTPUT_THRESHOLD) {
		    	totalProb += output[i];
		    	entries.add(new Entry(i, output[i]));
		    }
		}

		// Loop through moves above threshold and check validity
		while (!entries.isEmpty()) {
			double p = Math.random() * totalProb;
			double cumProb = 0.0;
			for (Entry e : entries) {
				cumProb += e.value;
				if (cumProb > p) {
					int row = Cell.getRow(e.index);
					int col = Cell.getCol(e.index);
					Move move = new Move(board, row, col, this);
					if (move.isValid()) {
						// mark desired array with 1 to note which Cell was played
						desired[e.index] = 1;
						// Create MoveData Object for Backpropagation
						MoveData md = new MoveData(inputs, desired);
						data.add(md);
						return move;
					} else {
						// invalid move, so remove from array of possible moves
						totalProb -= e.value;
						entries.remove(e);
						break;
					}
				}
			}
		}
		
		// rare case where only valid move is below threshold probability
		for (int i = 0; i < output.length; i++) {
		    if (output[i] <= OUTPUT_THRESHOLD) {
		    	int row = Cell.getRow(i);
				int col = Cell.getCol(i);
				Move move = new Move(board, row, col, this);
				if (move.isValid()) {
					// mark desired array with 1 to note which Cell was played
					desired[i] = 1;
					// Create MoveData Object for Backpropagation
					MoveData md = new MoveData(inputs, desired);
					data.add(md);
					return move;
				}
		    }
		}
		
		System.out.println("ERROR: Unable to find a valid move");
		return null;
	}

	@Override
	public void addWin() {
		super.addWin();
		backpropagate(PAYOFF_WIN);
	}

	@Override
	public void addLoss() {
		super.addLoss();
		backpropagate(PAYOFF_LOSS);
	}

	@Override
	public void addTie() {
		super.addTie();
		backpropagate(PAYOFF_TIE);
	}
	
	/**
	 * Use the given payoff to update the weights of the NeuralNet
	 * for each of the moves made during this Match.
	 * @param payoff Payoff for this Match
	 */
	private void backpropagate(double payoff) {
		try {
			for (int i = data.size() - 1; i >= 0; i--) {
				MoveData md = data.get(i);
				nn.backpropagate(md.inputs, md.desired, payoff);
				payoff = payoff * DIMINISH_RATE;
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unable to backpropagate values");
		}
	}

	@Override
	public void alertNewMatch() {
		data = new ArrayList<>();
	}

}

/**
 * MoveData holds records of each move the AI plays for a given Match.
 * 
 * @author Teddy
 */
class MoveData {
	/* Input values for each round */
	protected double[] inputs;
	/* Desired output values for each round:
	 * 0 if occupied, 1 if current move, null if empty */
	protected double[] desired;
	
	/**
	 * Constructs a new MoveData() object and initializes the Collections.
	 */
	protected MoveData(double[] inputs, double[] desired) {
		this.inputs  = inputs;
		this.desired = desired;
	}
}

/**
 * An Entry is an object used for sorting an array in reverse
 * order by value but also keeping its original index.
 * 
 * @author Teddy
 */
class Entry implements Comparable<Entry> {
	protected int index;
	protected double value;

	/**
	 * Constructs a new Entry object with the given index and value
	 * @param index Array Index of the Entry
	 * @param value Array Value of the Entry
	 */
	protected Entry(int index, double value) {
		this.index = index;
		this.value = value;
	}
	
	@Override
	public int compareTo(Entry o) {
        return (int) (o.value - this.value);
	}
	
	public String toString() {
		return this.index + " - " + this.value;
	}
}