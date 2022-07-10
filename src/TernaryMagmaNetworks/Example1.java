package TernaryMagmaNetworks;

import NeuralNetwork.NeuralNetwork;

import java.util.ArrayList;
import java.util.Arrays;

import static TernaryMagmaNetworks.NeuroStructureGenerator.individualMagma;

public class Example1 {
    public static void main(String[] args) {
        ArrayList<double[]> inputSpace = MagmaDataGenerator.BINARY_INPUT_SPACE;
        ArrayList<double[]> outputSpace = MagmaDataGenerator.BINARY_OUTPUT_SPACE;
        boolean[] result = new boolean[outputSpace.size()];


        for (int i = 0; i < result.length; i++) {

                System.out.println(image(outputSpace.get(i)));
            System.out.println(Arrays.toString(outputSpace.get(i)));
            System.out.println();
        }
    }
    /*
    int[] dim = {1,1};
        int counter = 0;
        for (double[] magma : outputSpace) {
            NeuralNetwork neuralNetwork = individualMagma(dim, HyperParameters.fast, magma, inputSpace);
            System.out.println(neuralNetwork);
            result[counter++] = neuralNetwork != null;
        }
     */
    public static String image(double[] magma) {
        StringBuilder sb = new StringBuilder();
        int sqrt = (int) Math.sqrt(magma.length);
        int count = 0;
        for (double v : magma) {
            switch ((int) v) {
                case -1 -> sb.append('-');
                case 1 -> sb.append('%');
            }
            if (count++ %  sqrt == sqrt - 1)
                sb.append("\n");
        }
        return sb.toString();
    }
}
