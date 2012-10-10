package services.impl;

import services.service.RandomManager;

import java.util.Random;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 03.10.12
 * Time: 20:39
 */
public class RandomManagerImpl implements RandomManager {

    @Override
    public int[] initRandomArray(int arraySize, int upperBound, int lowerBound) {
        int array[] = new int[arraySize];
        Random random = new Random();

        upperBound += (1 + Math.abs(lowerBound));

        for(int i = 0; i < array.length; i++){
            array[i] = random.nextInt(upperBound)+lowerBound;
        }

        return array;
    }
}
