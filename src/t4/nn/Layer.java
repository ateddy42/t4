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
	
	/**
	 * Constructs a new Layer with a given number of Neurons.
	 * Initializes the Neurons with default values (see {@link NeuralNet})
	 * and connects them with Bridges to the neurons in the previous layer
	 * as well as the NeuralNet bias.
	 * @param numNeurons Number of Neurons in this Layer
	 */
	protected Layer(int numNeurons) {
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
	}
	
	/**
	 * Update the values for each of the Neurons in this Layer
	 * as the weighted sum of the input to each Neuron. If a
	 * Neuron does not have input values, its value is set to 0.
	 */
	protected void updateValues() {
		for (int i = 0; i < neurons.length; i++) {
			neurons[i].calculateValue();
		}
	}
}
