package t4.Player;

import java.util.ArrayList;

import t4.Board.Board;
import t4.Board.Cell;
import t4.Board.Move;
import t4.Game.Game;
import t4.nn.Layer;
import t4.nn.NeuralNet;

public class BasicAI extends Player {
	private static final int NUM_INPUTS = 18;
	private static final int NUM_HIDDEN_LAYERS = 1;
	private static final int NUM_HIDDEN_NEURONS = 10;
	private static final int NUM_OUTPUTS = 9;

	private NeuralNet nn;
	private ArrayList<MoveData> data;

	/**
	 * Creates a Basic AI with the default values for NeuralNet
	 * activation function, learning rate, and bias value.
	 * @param game Game this AI is playing
	 * @param name Name of this AI
	 */
	public BasicAI(Game game, String name) {
		super(game, name);
		nn = new NeuralNet();
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
		// Create MoveData Object for Backpropagation
		MoveData md = new MoveData();
		data.add(md);
		
		Board board = game.getMatch().getBoard();
		double[] inputs = new double[NUM_INPUTS];
		Double[] desired = new Double[NUM_OUTPUTS];
		
		// loop through all Cells to get input values for NeuralNet
		for (int i = 0; i < Board.NUM_ROWS; i++) {
			for (int j = 0; j < Board.NUM_COLS; j++) {
				Cell cell = board.cells[i][j];
				int index = cell.getIndex();
				// If cell is occupied, set desired output to 0
				desired[index] = cell.isOccupied() ? new Double(0) : null;
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
		for (int i = 0; i < output.length; i++) {
		    entries.add(new Entry(i, output[i]));
		}
		
		// Try moves in decreasing order of value
		for (Entry e : entries) {
			int row = Cell.getRow(e.index);
			int col = Cell.getCol(e.index);
			Move move = new Move(board, row, col, this);
			if (move.isValid()) {
				// mark desired array with 1 to note which Cell was played
				desired[e.index] = new Double(1);
				md.addInput(inputs);
				md.addDesired(desired);
				md.addOutput(output);
				return move;
			}
		}
		System.out.println("ERROR: Unable to find a valid move");
		return null;
	}

	@Override
	public void addWin() { this.score++; }

	@Override
	public void addLoss() { return; }

	@Override
	public void addTie() { return; }

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
	protected ArrayList<double[]> inputs;
	/* Output values for each round */
	protected ArrayList<double[]> outputs;
	/* Desired output values for each round:
	 * 0 if occupied, 1 if current move, null if empty */
	protected ArrayList<Double[]> desired;
	
	/**
	 * Constructs a new MoveData() object and initializes the Collections.
	 */
	protected MoveData() {
		inputs  = new ArrayList<>();
		outputs = new ArrayList<>();
		desired = new ArrayList<>();
	}
	
	/**
	 * Adds an input value array to its Collection
	 * @param input Input values
	 */
	protected void addInput(double[] input) {
		this.inputs.add(input);
	}
	
	/**
	 * Adds an output value array to its Collection
	 * @param output Output values
	 */
	protected void addOutput(double[] output) {
		this.outputs.add(output);
	}
	
	/**
	 * Adds the desired output values to its Collection
	 * @param desired Desired output values
	 */
	protected void addDesired(Double[] desired) {
		this.desired.add(desired);
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

	protected Entry(int index, double value) {
		this.index = index;
		this.value = value;
	}
	
	@Override
	public int compareTo(Entry o) {
        return (int) (o.value - this.value);
	}
}