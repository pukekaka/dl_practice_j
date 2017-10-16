package dogtest.booktest.ch2.MultiLayerPerceptrons.util;

import java.util.Random;

public class RandomGenerator {

    public static double uniform(double min, double max, Random rng) {

        return rng.nextDouble() * (max - min) + min;

    }

}