package NeuralNetwork;

import SortedInsert.ValueSortedStack;

import java.util.ArrayList;

public class TrainBooleanOperatorNetworkProof {
    /*
        Understanding about Neural Networks have come from projecting the space of neural networks
        to the space of boolean operations. The motivating example of this is XOR,
        a boolean operation which is not linearly separable. When training a neural network on XOR,
        the necessity of an extra hidden node is projected to the non-separability.

        This program is a proof of a type of neural network which can be seen as a projection of
        the entire task space of boolean operations, a boolean operator.
        The idea is to find a common structure between all boolean operations, the arbitrary choice
        of parametrizing the biases makes weights a projection of the entire task space.
     */

    static final int NUMBER_OF_TRIES_FOR_FAILURE = 10000;
    static final double MAX_ERROR_FOR_SUCCESS = 0.1;
    static final boolean PRINT_ERRORS = false;
    static final double MAGNITUDE_OF_EPOCHS = 6;
    static final double RATE = 0.1;
    static final ArrayList<double[]> INPUT_SPACE = binaryProductSpace(2);
    static final ArrayList<double[]> OUTPUT_SPACE = binaryProductSpace(4);
    static Activation hiddenActivation = new Tanh();
    static Activation outputActivation = new Tanh();
    public static void main(String[] args) {
        howSimilar();
    }

    private static void howSimilar() {
        BiasManager works = works();
        System.out.println(works);
        ValueSortedStack<BiasManager> minStack = new ValueSortedStack<>(100);

        for (int i = 0; i < 10000; i++) {
            BiasManager gen = works();
            assert works != null;
            double distance = weightDistance(works, gen);
            System.out.println(distance);
            System.out.println();
            minStack.add(distance, gen);
            System.out.println(minStack);
        }
    }

    private static ArrayList<ArrayList<BiasManager>> clusterWorks() {
        ArrayList<ArrayList<BiasManager>> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            BiasManager bm = works();
            boolean isPlaced = false;
            for (int j = 0; j < list.size() && !isPlaced; j++) {
                if(weightDistance(list.get(j).get(0), bm) < 1){
                    list.get(j).add(bm);
                    isPlaced = true;
                }
            }
            if(!isPlaced){
                ArrayList<BiasManager> newList = new ArrayList<>();
                newList.add(bm);
                list.add(newList);
            }
            System.out.println(list.size());
        }
        return list;
    }

    public static double weightDistance(BiasManager first, BiasManager second) {
        double distance = 0;
        for (int i = 0; i < first.network.size(); i++)
            for (int j = 0; j < first.network.get(i).weight.length; j++)
                for (int k = 0; k < first.network.get(i).weight[j].length; k++)
                    distance += Math.abs(first.network.get(i).weight[j][k] - second.network.get(i).weight[j][k]);

        return distance;
    }
    /**
     * Trains a new initialization of a Bias Manager a number of times
     * @return      True if the total error of a single try is less than 0.5
     *              False if it does not return true after 'TRIES' times
     */
    private static BiasManager works() {
        for (int i = 0; i < NUMBER_OF_TRIES_FOR_FAILURE; i++) {
            BiasManager bm = train(MAGNITUDE_OF_EPOCHS, RATE);
            if(proof(bm) < MAX_ERROR_FOR_SUCCESS)
                return bm;
        }
        return null;
    }

    /**
     * initialize a new bnn and train it epocMag times
     * return the final error of the bnn
     * @param epocMag   10^epocMag number of epoc iterations
     * @param rate      learning rate for the bnn
     * @return          final error of the bnn
     */
    private static BiasManager train(double epocMag, double rate) {
        BiasManager bm = new BiasManager(new int[]{2, 3, 1}, 16, hiddenActivation, outputActivation);
        setFinalBiasZero(bm);
        for (int epoc = 0; epoc < Math.pow(10, epocMag); epoc++)
            singlePass(bm, rate, epoc % 16);
        return bm;
    }

    /**
     * a single pass through an entire task space
     * @param rate  learning rate for the bnn
     * @param task  which task and biases to use on the bnn
     */
    private static void singlePass(BiasManager bm, double rate, int task) {
        bm.setBias(task);
        double[] currentOp = OUTPUT_SPACE.get(task);
        for (int i = 0; i < INPUT_SPACE.size(); i++)
            bm.back(INPUT_SPACE.get(i), new double[]{currentOp[i]});
        bm.update(rate);
        setFinalBiasZero(bm);
    }

    //Proof=============================================================
    //Proof=============================================================
    //Proof=============================================================

    /**
     * Sums of the errors of the network at its current state
     * @return  Sum of errors
     */
    private static double proof(BiasManager bm) {
        double sum = 0;
        for (double d : getScoreList(bm))
            sum += d;
        if(PRINT_ERRORS)
            System.out.println(sum);
        return sum;
    }

    /**
     * Computes the error of the Bias Manager for every tasks
     * @return  Array storing the errors for every task
     */
    private static double[] getScoreList(BiasManager bm) {
        double[] scoreList = new double[16];
        for (int task = 0; task < 16; task++)
            scoreList[task] = getTaskError(bm, task);
        return scoreList;
    }

    /**
     * Computes the error for a single task
     * @param task  Current task for the Bias Manager
     * @return      Error for the current task
     */
    private static double getTaskError(BiasManager bm, int task) {
        bm.setBias(task);
        double[] currentOp = OUTPUT_SPACE.get(task);
        double error = 0;
        for (int i = 0; i < currentOp.length; i++)
            error += Math.abs((bm.calc(INPUT_SPACE.get(i))[0] - currentOp[i]));
        return error;
    }


    //Data Generation====================================================
    //Data Generation====================================================
    //Data Generation====================================================


    /**
     * Computes an ArrayList which stores every binary number of a certain digit count
     * @param dimension Number of digits
     * @return          ArrayList of Double[] which contain the individual binary digits
     */
    public static ArrayList<double[]> binaryProductSpace(double dimension) {
        ArrayList<double[]> productSpace = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, dimension); i++)
            productSpace.add(getBinaryRepresentation(dimension, i));
        return productSpace;
    }

    /**
     * Convert number to binary and stores the digits in an array
     * @param dimension Number of digits
     * @param number    Current number to be converted
     * @return          Double[] which stores the individual digits
     */
    private static double[] getBinaryRepresentation(double dimension, int number) {
        double[] output = new double[(int) dimension];
        int remainder = number;
        for (int j = 0; j < dimension; j++, remainder /= 2)
            output[j] = (remainder % 2 - 0.5) * 2;
        return output;
    }

    private static void setFinalBiasZero(BiasManager bm){
        bm.network.get(bm.network.size() - 1).bias[0] = 0;
    }

}