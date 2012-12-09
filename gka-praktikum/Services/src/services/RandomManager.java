package services;

import java.util.Random;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 03.10.12
 * Time: 20:39
 */
public class RandomManager  {
    private static final int ZERO = 0;


//    public static int[] initRandomArray(int arraySize, int upperBound, int lowerBound) {
//        int array[] = new int[arraySize];
//        Random random = new Random();
//
//        upperBound += (1 + Math.abs(lowerBound));
//
//        for(int i = 0; i < array.length; i++){
//            array[i] = random.nextInt(upperBound)+lowerBound;
//        }
//
//        return array;
//    }

    public static long longNumber(int upperBound, int lowerBound) {
        long number;
        Random random = new Random();

        if (lowerBound != ZERO) {
            if(lowerBound < 0)
                upperBound += (1 + Math.abs(lowerBound));
            else if(upperBound > lowerBound)
                upperBound = (upperBound+1) - lowerBound;
        }

        number = random.nextInt(upperBound)+lowerBound;

        return number;
    }

    public static int intNumber(int upperBound, int lowerBound) {
        int number;
        Random random = new Random();

        if (lowerBound != ZERO) {
            if(lowerBound < 0)
                upperBound += (1 + Math.abs(lowerBound));
            else if(upperBound > lowerBound)
                upperBound = (upperBound+1) - lowerBound;
        }

        number = random.nextInt(upperBound)+lowerBound;

        return number;
    }

    public static double doubleNumber(double upperBound, double lowerBound) {
        double number;
        Random random = new Random();

        if (lowerBound != ZERO) {
            if(lowerBound < 0)
                upperBound += (1 + Math.abs(lowerBound));
            else if(upperBound > lowerBound)
                upperBound = (upperBound+1) - lowerBound;
        }

        number = random.nextDouble() * upperBound;

        if(number < lowerBound)
            number = lowerBound;

        return number;
    }
}
