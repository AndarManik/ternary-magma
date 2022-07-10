package TernaryMagmaNetworks;

import NeuralNetwork.NeuralNetwork;

import java.util.ArrayList;

import static TernaryMagmaNetworks.NeuroStructureGenerator.individualMagma;

public class Test {
    public static void main(String[] args) {
        ArrayList<double[]> inputSpace = MagmaDataGenerator.BINARY_INPUT_SPACE;
        ArrayList<double[]> outputSpace = MagmaDataGenerator.BINARY_OUTPUT_SPACE;
        boolean[] result = new boolean[outputSpace.size()];

        int[] dim = {1};
        int counter = 0;
        for (double[] magma : outputSpace) {
            NeuralNetwork neuralNetwork = individualMagma(dim, HyperParameters.slow, magma, inputSpace);
            System.out.println(neuralNetwork);
            result[counter++] = neuralNetwork != null;

        }
        for (boolean b : result) {
            System.out.println(b);
        }
    }
}
