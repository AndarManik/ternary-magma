package NeuralNetwork;

public class ReLu implements Activation {

    @Override
    public double activate(double input) {
        return (input < 0) ? 0 : input;
    }

    @Override
    public double der(double input, double output) {
        return (input < 0) ? 0 : 1;
    }
}