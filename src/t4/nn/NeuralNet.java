package t4.nn;

import java.util.ArrayList;

import t4.nn.Activation.ActivationFunction;

/**
 * A NeuralNet is a representation of a Neural Network.
 * It has a Collection of Layers containing Neurons linked
 * together by Bridges. A NeuralNet also has a bias Neuron,
 * containing the bias used for all Neurons outside the
 * input layer, a learning rate alpha, and an activation
 * function used by each of the Neurons.
 * 
 * @author Teddy
 */
public class NeuralNet {
	public static double INIT_BIAS_WEIGHT = -1;
	public static double INIT_NEURON_WEIGHT = 1;
	
	protected ArrayList<Layer> layers;
	protected Neuron bias;
	protected double alpha;
	protected ActivationFunction activation;
	
	/**
	 * Construct a new NeuralNet object with the given activation
	 * function, learning rate, and bias value.
	 * @param activation Activation function
	 * @param alpha Learning rate
	 * @param bias Value for the bias
	 */
	public NeuralNet(ActivationFunction activation, double alpha, double bias) {
		layers = new ArrayList<>();
		this.activation = activation;
		this.alpha = alpha;
		this.bias = new Neuron(bias, 0, null);
	}
	
	/**
	 * Return the final layer of this NeuralNet
	 * @return Output Layer
	 */
	public Layer getOutputLayer() {
		if (layers.isEmpty()) return null;
		return layers.get(layers.size() - 1);
	}
	
	/**
	 * Return the first layer of this NeuralNet
	 * @return Input Layer
	 */
	public Layer getInputLayer() {
		if (layers.isEmpty()) return null;
		return layers.get(0);
	}
}
