package NeuralNetwork;

public class LeakyReLu implements Activation {
    @Override

    public double activate( double input ) {
        return ( ( input < 0 ) ? 0.1 : 1 ) * input;
    }

    @Override
    public double der( double input, double output ) {
        return ( input < 0 ) ? 0.1 : 1;
    }
}
