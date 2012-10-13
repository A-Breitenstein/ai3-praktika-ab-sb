package services.impl;

import services.service.CallerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 13.10.12
 * Time: 14:24
 */
public class CallerManagerImpl implements CallerManager {

    private int[] range;
    private Object algorithmClass;
    private List<String> algorithmNames;


    private CallerManagerImpl(int[] range, Object algorithmClass, List<String> algorithmNames) {
        this.range = range;
        this.algorithmClass = algorithmClass;
        this.algorithmNames = algorithmNames;
    }

    public static CallerManagerImpl create(int[] range, Object algorithmClass, List<String> algorithmNames){
        return new CallerManagerImpl(range, algorithmClass, algorithmNames);
    }

    @Override
    public Object retrieveAlgorithmClass() throws IllegalAccessException {
        if(algorithmClass == null) throw new IllegalAccessException();
        return algorithmClass;
    }

    @Override
    public CallerManager setAlgorithmClass(Object algorithmClass) {
        if(algorithmClass == null) throw new IllegalArgumentException();

        this.algorithmClass = algorithmClass;

        return this;
    }

    @Override
    public List<String> retrieveAlgorithmNames() {
        return algorithmNames;
    }

    @Override
    public CallerManager setAlgorithmNames(List<String> algorithmNames) {
        this.algorithmNames = new ArrayList<String>(algorithmNames);
        return this;
    }

    @Override
    public int[] retrieveSequenceParameters() throws IllegalAccessException {
        if(range == null) throw new IllegalAccessException();
        return range;
    }

    @Override
    public int retrieveSequenceParameterFrom() {
        return (range == null ? 0 : range[0]);
    }

    @Override
    public int retrieveSequenceParameterTo() {
        return (range == null ? 0 : range[1]);
    }

    @Override
    public CallerManager setSequenceParameters(int from, int to) {
        range[0] = from;
        range[1] = to;

        return this;
    }
}
