// Modified by Viacheslav Mikhailov
// Network.java
// From Classic Computer Science Problems in Java Chapter 7
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package algos.ml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class Network<T> {
    private List<Layer> layers = new ArrayList<>();

    public Network(int[] layerStructure, double learningRate,
                   DoubleUnaryOperator activationFunction, DoubleUnaryOperator derivativeActivationFunction) {
        if (layerStructure.length < 3) {
            throw new IllegalArgumentException("Error: Should be at least 3 layers (1 input, 1 hidden, 1 output).");
        }
        // input layer
        Layer inputLayer = new Layer(Optional.empty(), layerStructure[0], learningRate, activationFunction,
                derivativeActivationFunction);
        layers.add(inputLayer);
        // hidden layers and output layer
        for (int i = 1; i < layerStructure.length; i++) {
            Layer nextLayer = new Layer(Optional.of(layers.get(i - 1)), layerStructure[i], learningRate,
                    activationFunction,
                    derivativeActivationFunction);
            layers.add(nextLayer);
        }
    }

    // Pushes input data to the first layer, then output from the first
    // as input to the second, second to the third, etc.
    private double[] process(double[] input) {
        for (Layer layer : layers) input = layer.process(input);
        return input;
    }

    // Figure out each neuron's changes based on the errors of the output
    // versus the expected outcome
    private void backpropagate(double[] expected) {
        // calculate delta for output layer neurons
        int lastLayer = layers.size() - 1;
        layers.get(lastLayer).calculateDeltasForOutputLayer(expected);
        // calculate delta for hidden layers in reverse order
        for (int i = lastLayer - 1; i >= 0; i--) {
            layers.get(i).calculateDeltasForHiddenLayer(layers.get(i + 1)); // here we calculate deltas for neurons of not only hidden layers but also first (input) layer
        }
    }

    // backpropagate() doesn't actually change any weights
    // this function uses the deltas calculated in backpropagate() to
    // actually make changes to the weights
    private void updateWeights() {
        for (Layer layer : layers.subList(1, layers.size())) {
            for (Neuron neuron : layer.neurons) {
                for (int w = 0; w < neuron.weights.length; w++) {
                    neuron.weights[w] = neuron.weights[w] + (neuron.learningRate *
                            layer.previousLayer.get().outputCache[w] * neuron.delta); // the number of weights per neuron is same across the layer and is equal to amount of neurons in the previous layer
                }
            }
        }
    }


    /**
     * train() uses the results of process() run over many input signals comparing them
     * against corresponding (by array index) expected absolute correct outcomes (prepared answers).
     *
     * @param inputs    are signals from the outside of neural network. Each signal is a vector of coordinates which are dimensional values.
     *                  The amount of neurons in the very first layer must equal to amount of the dimensions of each signal.
     * @param expecteds are the predefined absolutely correct outcomes of neural processing
     */
    public void train(List<double[]> inputs, List<double[]> expecteds) {
        if (inputs.size() != expecteds.size()) throw new IllegalArgumentException("During training, one or several inputs signals don't have corresponding answers for them, or vise versa");
        for (int i = 0; i < inputs.size(); i++) {
            double[] xs = inputs.get(i);
            double[] ys = expecteds.get(i);
            process(xs);
            backpropagate(ys);
            updateWeights();
        }
    }

    public class Results {
        public final int correct;
        public final int trials;
        public final double percentage;

        public Results(int correct, int trials, double percentage) {
            this.correct = correct;
            this.trials = trials;
            this.percentage = percentage;
        }
    }

    // for generalized results that require classification
    // this function will return the correct number of trials
    // and the percentage correct out of the total
    public Results validate(List<double[]> inputs, List<T> expecteds, Function<double[], T> interpret) {
        int correct = 0;
        for (int i = 0; i < inputs.size(); i++) {
            double[] input = inputs.get(i);
            T expected = expecteds.get(i);
            T result = interpret.apply(process(input));
            if (result.equals(expected)) {
                correct++;
            }
        }
        double percentage = (double) correct / (double) inputs.size();
        return new Results(correct, inputs.size(), percentage);
    }
}
