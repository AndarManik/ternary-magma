package NeuralNetwork;

public interface Activation {
    default double[] activate(double[] input) {
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++)
            output[i] = activate(input[i]);
        return output;
    }

    double activate(double input);

    double der(double input, double output);
}




