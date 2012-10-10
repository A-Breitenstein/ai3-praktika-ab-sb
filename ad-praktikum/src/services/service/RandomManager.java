package services.service;


/**
 * AD-Praktikum
 * Team: 13
 * Date: 03.10.12
 * Time: 20:38
 */
public interface RandomManager {

    /**
     * Returns an Array containing randomized numbers.
     *
     * @param arraySize size of the returned array
     * @param upperBound highest value of random numbers
     * @param lowerBound lowest value of random numbers
     * @return array with attributes according to parameters
     */
    int[] initRandomArray(final int arraySize, int upperBound, final int lowerBound);

}
