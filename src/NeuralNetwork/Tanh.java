package NeuralNetwork;

public class Tanh implements Activation {
    @Override
    public double activate(double input) {
        return Math.tanh(input);
    }

    @Override
    public double der(double input, double output) {
        return (1 - output * output);
    }
}