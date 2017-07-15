package t4.nn.Activation;

/**
 * A Sigmoid ActivationFunction is a function that takes
 * values and returns a value in the range [-1, 1]. An input
 * of 0 returns 0, larger input values return positive
 * output values, and smaller input values return negative.
 * 
 * @author Teddy
 */
public class Tanh extends ActivationFunction {

	/**
	 * {@inheritDoc}
	 * 
	 * This implementation uses the Tanh function:
	 * <br><br><code>Math.exp(input) / (1 + Math.exp(input))</code>
	 */
	public double getOutput(double input) {
		double pow = Math.exp(input);
		return pow / (1 + pow);
	}

}
