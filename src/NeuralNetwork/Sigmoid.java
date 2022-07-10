package NeuralNetwork;

public class Sigmoid implements Activation {
    @Override
    public double activate( double input ) {
        double exp = Math.exp( input );
        return exp / ( exp + 1 );
    }

    @Override
    public double der( double input, double output ) {
        return output * ( 1 - output );
    }
}
