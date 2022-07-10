package TernaryMagmaNetworks;

import NeuralNetwork.NeuralNetwork;


import java.util.ArrayList;

import static TernaryMagmaNetworks.NeuroStructureGenerator.individualMagma;

public class HardestTernarySearch {
    public static void main(String[] args) {
        int[] dim = {2,5,1};
        IndexOfMagmaNetworks index = new IndexOfMagmaNetworks("[2,5,1]`");
        runThrough(dim, HyperParameters.fast, index);
        //indexesOfEmptyMagmas(index);
    }

    private static void indexesOfEmptyMagmas(IndexOfMagmaNetworks index) {
        for (int i = 0; i < index.magmas.length; i++)
            if (index.magmas[i].networks.isEmpty())
                System.out.println(i);
    }


    private static void runThrough(int[] dim, HyperParameters hyper, IndexOfMagmaNetworks index) {
        for (int i = 0; i < index.magmas.length; i++) {
            Magma magma= index.magmas[i];
            if(magma.networks.size() == 0) {
                System.out.println(magma);
                NeuralNetwork nn = individualMagma(dim, hyper, magma.magma, MagmaDataGenerator.TERNARY_INPUT_SPACE);
                if (nn != null) magma.networks.add(nn);
                index.save();
                System.out.println(nn);
            }
        }
    }
}
