package NeuralNetwork;

public class Layer {
    public Activation activation;
    public double[] val, preAct, bias, biasGrad, preActGrad;
    public double[][] weight, grad;

    public Layer( int nodeCount, int prevLayerCount, Activation a ) {
        val = new double[ nodeCount ];
        preAct = new double[ nodeCount ];
        weight = new double[ nodeCount ][ prevLayerCount ];
        grad = new double[ nodeCount ][ prevLayerCount ];
        bias = new double[ nodeCount ];
        biasGrad = new double[ nodeCount ];
        preActGrad = new double[ nodeCount ];
        activation = a;

        for ( double[] w : weight )
            for ( int i = 0; i < w.length; i++ )
                w[ i ] = Math.random() - 0.5;

        for ( int i = 0; i < bias.length; i++ )
            bias[ i ] = Math.random() - 0.5;
    }

    public double[] calc( double[] prevVal ) {
        preAct = bias.clone();
        for ( int pos = 0; pos < weight.length; pos++ )
            for ( int prev = 0; prev < weight[0].length; prev++ )
                preAct[ pos ] += prevVal[ prev ] * weight[ pos ][ prev ];
        val = activation.activate( preAct );
        return val.clone();
    }

    public double[] back( double[] grad ) {
        for ( int i = 0; i < preActGrad.length; i++ ) {
            preActGrad[ i ] = activation.der( preAct[ i ], val[ i ] ) * grad[ i ];
            biasGrad[ i ] += preActGrad[ i ];
        }
        double[] gradient = new double[ weight[ 0 ].length ];
        for ( int pos = 0; pos < weight[ 0 ].length; pos++ )
            for ( int next = 0; next < weight.length; next++ )
                gradient[ pos ] += preActGrad[ next ] * weight[ next ][ pos ];
        return gradient;
    }

    public double[] weightGrad( double[] prevVal ) {
        for ( int pos = 0; pos < grad.length; pos++ )
            for ( int prev = 0; prev < grad[ 0 ].length; prev++ )
                grad[ pos ][ prev ] += preActGrad[ pos ] * prevVal[ prev ];
        return val.clone();
    }

    public void update( double rate ) {
        for ( int i = 0; i < weight.length; i++ )
            for ( int j = 0; j < weight[ i ].length; j++ ) {
                weight[ i ][ j ] -= grad[ i ][ j ] * rate;
                grad[ i ][ j ] = 0;
            }
        for ( int i = 0; i < bias.length; i++ ) {
            bias[ i ] -= biasGrad[ i ] * rate;
            biasGrad[ i ] = 0;
        }
    }
}