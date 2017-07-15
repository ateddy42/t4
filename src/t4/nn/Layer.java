package t4.nn;

/**
 * A Layer represents a collection of Neurons in one of
 * the NeuralNet's various layers.
 * 
 * @author Teddy
 */
public class Layer {
	protected Neuron[] neurons;
	protected NeuralNet nn;
	private String name;
	
	/**
	 * Constructs a new Layer with a given number of Neurons.
	 * Initializes the Neurons with default values (see {@link NeuralNet})
	 * and connects them with Bridges to the neurons in the previous layer
	 * as well as the NeuralNet bias.
	 * @param numNeurons Number of Neurons in this Layer
	 */
	public Layer(NeuralNet nn, int numNeurons, String name) {
		this.nn = nn;
		this.name = name;
		this.neurons = new Neuron[numNeurons];
		Layer lastLayer = nn.getOutputLayer();
		int numInputs = lastLayer == null ? 0 : lastLayer.neurons.length + 1;
		for (int i = 0; i < numNeurons; i++) {
			Neuron neuron = new Neuron(0, numInputs, this);
			// check if previous layer exists
			if (lastLayer != null) {
				// add bridge to bias
				neuron.inputs[0] = new Bridge(nn.bias,
						NeuralNet.INIT_BIAS_WEIGHT);
				
				// add bridge to all previous layer neurons
				for (int j = 1; j < numInputs; j++) {
					neuron.inputs[j] = new Bridge(
							lastLayer.neurons[j - 1],
							NeuralNet.INIT_NEURON_WEIGHT);
				}
			}
			neurons[i] = neuron;
		}
		nn.layers.add(this);
	}
	
	/**
	 * Update the values for each of the Neurons in this Layer
	 * as the weighted sum of the input to each Neuron. If a
	 * Neuron does not have input values, its value is set to 0.
	 */
	protected void updateValues() {
		for (int i = 0; i < neurons.length; i++) {
			neurons[i].calculateValue(nn.activation);
		}
	}
	
	/**
	 * Return an array of values for this Layer, corresponding to
	 * each of the Neuron's values.
	 * @return Array of values for this Layer's Neurons
	 */
	protected double[] getValues() {
		double[] values = new double[neurons.length];
		for (int i = 0; i < neurons.length; i++) {
			values[i] = neurons[i].value;
		}
		return values;
	}
	
	/**
	 * Sets the values for each of the Neurons in this Layer
	 * @param values Array of values
	 * @throws Exception If number of values != number of Neurons
	 */
	protected void setValues(double[] values) throws Exception {
		if (neurons.length != values.length)
			throw new Exception("Number of input values does not match the number of Neurons");
		for (int i = 0; i < neurons.length; i++) {
			neurons[i].setValue(values[i]);
		}
	}
	
	public String toString() {
		return this.name;
	}
}
