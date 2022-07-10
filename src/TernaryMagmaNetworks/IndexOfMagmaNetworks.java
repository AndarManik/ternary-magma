package TernaryMagmaNetworks;

import NeuralNetwork.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IndexOfMagmaNetworks {
    String fileName;
    Magma[] magmas;
    IndexOfMagmaNetworks(String fileName) {
        this.fileName = fileName;
        ArrayList<double[]> outputSpace = MagmaDataGenerator.TERNARY_OUTPUT_SPACE;
        magmas = new Magma[outputSpace.size()];
        int index = 0;
        for (double[] magma : outputSpace)
            magmas[index++] = new Magma(magma);
        int counter = 0;
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            for (Magma magma : magmas) {
                int networkCount = Integer.parseInt(fileScanner.nextLine());
                magma.read(networkCount, fileScanner);
                magma.magma = outputSpace.get(counter++);
            }
            save();
        } catch (Exception ignored) {
            save();
        }
    }
    void save() {
        save(fileName);
        save(fileName + "extra");
    }

    void save(String fileName) {
        File file = new File(fileName);
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            for (Magma magma : magmas) {
                writer.println(magma.networks.size());
                for (NeuralNetwork network : magma.networks)
                    network.writeTo(writer);
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("oops");
        }
    }
}
