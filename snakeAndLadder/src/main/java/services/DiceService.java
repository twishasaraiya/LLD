package services;

import java.util.Random;

public class DiceService {
    public static int roll(int noOfDices) {
        // max -> 6*noOfDices
        // min -> noOfDices
        // new Random.nextInt(max+1-min) + min
        return new Random().nextInt(6*noOfDices + 1 - noOfDices) + noOfDices;
    }
}
