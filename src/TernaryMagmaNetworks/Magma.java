package TernaryMagmaNetworks;

import NeuralNetwork.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Magma {
    public double[] magma;
    ArrayList<NeuralNetwork> networks;

    Magma(double[] magma) {
        this.magma = magma;
        networks = new ArrayList<>();
    }

    void read(int networkCount, Scanner fileScanner) {
        for (int i = 0; i < networkCount; i++) {
            int[] dim = inverseToStringInteger(fileScanner.nextLine());
            NeuralNetwork nn = new NeuralNetwork(dim);
            for (Layer layer : nn.network) {
                for (int j = 0; j < layer.weight.length; j++)
                    layer.weight[j] = inverseToStringDouble(fileScanner.nextLine());
                layer.bias = inverseToStringDouble(fileScanner.nextLine());
            }
            networks.add(nn);
        }
    }


    double[] inverseToStringDouble(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        double[] output = new double[strings.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = Double.parseDouble(strings[i]);
        }
        return output;
    }

    boolean converged(NeuralNetwork network) {
        ArrayList<double[]> inputSpace = MagmaDataGenerator.TERNARY_INPUT_SPACE;
        double[] networkOutput = new double[9];
        for (int i = 0; i < inputSpace.size(); i++)
            networkOutput[i] = network.calc(inputSpace.get(i))[0];

        for (int i = 0; i < networkOutput.length; i++)
            if(networkOutput[i] - magma[i] > 0.1)
                return false;
        return true;
    }

    int[] inverseToStringInteger(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int[] output = new int[strings.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = Integer.parseInt(strings[i]);
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (double v : magma) {
            switch ((int) v) {
                case -1 -> sb.append('~');
                case 0 -> sb.append(' ');
                case 1 -> sb.append('i');
            }
            if (count++ % 3 == 2)
                sb.append("\n");
        }
        return sb.toString();
    }
}
