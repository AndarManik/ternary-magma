package TernaryMagmaNetworks;

import NeuralNetwork.NeuralNetwork;

import java.util.ArrayList;

public class NeuroStructureGenerator {
    public static NeuralNetwork individualMagma(int[] dim, HyperParameters hyper, double[] magma, ArrayList<double[]> inputSpace) {
        for (int j = 0; j < hyper.tries(); j++) {
            NeuralNetwork nn = getTrained(magma, dim, hyper.epocMag(), hyper.rate(), inputSpace);
            double error = error(nn, magma, inputSpace);
            System.out.println(error);
            if (error < hyper.cutOff())
                return nn;
        }
        return null;
    }

    public static NeuralNetwork getTrained(double[] magma, int[] dim, double epocMag, double rate, ArrayList<double[]> inputSpace) {
        NeuralNetwork nn = new NeuralNetwork(dim);
        for (int i = 0; i < Math.pow(10, epocMag); i++) {
            int outputIndex = (int) (Math.random() * magma.length);
            nn.back(inputSpace.get(outputIndex), new double[] { magma[outputIndex]});
            if(i % magma.length == magma.length - 1)
                nn.update(rate);
        }
        return nn;
    }

    private static double error(NeuralNetwork nn, double[] magma, ArrayList<double[]> inputSpace) {
        double[] actualOutput = magmaBehavior(nn, inputSpace);
        double error = 0;
        for (int i = 0; i < actualOutput.length; i++)
            error += Math.abs(magma[i] - actualOutput[i]);
        return error;
    }

    private static double[] magmaBehavior(NeuralNetwork nn, ArrayList<double[]> inputSpace) {
        double[] actualOutput = new double[inputSpace.size()];
        int counter = 0;
        for (double[] inputs : inputSpace)
            actualOutput[counter++] = nn.calc(inputs)[0];
        return actualOutput;
    }
}
