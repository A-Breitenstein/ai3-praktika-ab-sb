package services.service;

import java.util.List;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 13.10.12
 * Time: 14:24
 */
public interface CallerManager {

    /**
     * @exception IllegalAccessException if AlgorithmClass isn't set first
     * @return Class which is holding the Algorithms
     */
    Object retrieveAlgorithmClass() throws IllegalAccessException;

    /**
     * !!!Algorithms need to be public!!!
     *
     * @param algorithmClass needs to be the Class which is containing the Algorithms
     * @return itself, for fluid adding of parameters
     */
    CallerManager setAlgorithmClass(Object algorithmClass);

    /**
     *
     * @return List of Algorithms, caller needs to know the names of the Algorithms
     */
    List<String> retrieveAlgorithmNames();

    /**
     *
     * @param algorithmNames
     * @return itself, for fluid adding of parameters
     */
    CallerManager setAlgorithmNames(List<String> algorithmNames);

    /**
     *
     * @return Array with length of 2, containing start and end range indicators of the sequence
     */
    int[] retrieveSequenceParameters() throws IllegalAccessException;

    /**
     *
     * @return first value of range of SequenceParameters
     */
    int retrieveSequenceParameterFrom();

    /**
     *
     * @return last value of range of SequenceParameters
     */
    int retrieveSequenceParameterTo();

    /**
     * Range is a closed interval:
     * [from, to] := {x in N | from <= x <= to}
     *
     * @param from where range starts
     * @param to where the range ends
     * @return itself, for fluid adding of parameters
     */
    CallerManager setSequenceParameters(int from, int to);
}
