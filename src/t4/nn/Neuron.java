package t4.nn;

/**
 * A Neuron represents one node in a NeuralNet. It has
 * a value and a collection of Bridge inputs, linking
 * it to other Neurons in previous layers.
 * 
 * @author Teddy
 */
public class Neuron {
	protected double value;
	protected Layer layer;
	protected Bridge[] inputs;
	
	/**
	 * Constructs a new Neuron with the given value, number
	 * of input Bridges, and the Layer it resides in.
	 * @param value Value of the Neuron
	 * @param numInputs Number of input Bridges
	 * @param layer Layer this Neuron is part of
	 */
	protected Neuron(double value, int numInputs, Layer layer) {
		this.value = value;
		this.inputs = new Bridge[numInputs];
		this.layer = layer;
	}
	
	/**
	 * Update the value of the Neuron to be the weighted sum
	 * of the Neurons linked via the input Bridges.
	 * @return Updated value for this Neuron
	 */
	protected double calculateValue() {
		if (inputs.length != 0) {
			value = 0;
			for (int i = 0; i < inputs.length; i++) {
				value += inputs[i].getWeightedInput();
			}
		}
		return this.value;
	}
	
	/**
	 * Sets the value of this Neuron to the given value
	 * @param value New value for this Neuron
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}
