package TernaryMagmaNetworks;

import java.util.ArrayList;

public class MagmaDataGenerator {
    public static final double[] Binary = {-1.0, 1.0};
    public static final double[] Ternary = {-1.0, 0.0, 1.0};
    public static final ArrayList<double[]> TERNARY_INPUT_SPACE = everyArrayOf(2, Ternary);
    public static final ArrayList<double[]> TERNARY_OUTPUT_SPACE = everyArrayOf(9, Ternary);

    public static final ArrayList<double[]> BINARY_INPUT_SPACE = everyArrayOf(2, Binary);
    public static final ArrayList<double[]> BINARY_OUTPUT_SPACE = everyArrayOf(4, Binary);

    public static ArrayList<double[]> everyArrayOf(int arraySize, double[] map) {
        ArrayList<double[]> output = new ArrayList<>();
        for (int i = 0; i < Math.pow(map.length, arraySize); i++)
            output.add(getBinaryRepresentation(arraySize, i, map));
        return output;
    }

    private static double[] getBinaryRepresentation(int dimension, int number, double[] map) {
        double[] output = new double[ dimension] ;
        int remainder = number;
        for (int j = 0; j < dimension; j++, remainder /= map.length)
            output[j] = map[remainder % map.length];
        return output;
    }
}
