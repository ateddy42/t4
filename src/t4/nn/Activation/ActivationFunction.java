package t4.nn.Activation;

/**
 * An ActivationFunction is a function used for determining
 * the output of a Neuron based on the sum of its weighted
 * inputs.
 * 
 * @author Teddy
 */
public abstract class ActivationFunction {
	
	/**
	 * Calculates the output value for the given input.
	 * @param input Input value of the Neuron
	 * @return Output value of the Neuron
	 */
	public abstract double getOutput(double input);
}
