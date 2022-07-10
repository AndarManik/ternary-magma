package TernaryMagmaNetworks;

public record HyperParameters(int tries, double cutOff, int epocMag, double rate){
    static final HyperParameters slow = new HyperParameters(100, 0.1, 6, 0.05);
    static final HyperParameters fast = new HyperParameters(10, 0.1, 5, 0.1);
}