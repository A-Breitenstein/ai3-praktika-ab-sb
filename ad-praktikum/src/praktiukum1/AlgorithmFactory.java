/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Sven
 */
public final class AlgorithmFactory {
    private AlgorithmFactory(){}
    public static AlgorithmObject maxTeilsumme3(){
        return MaxTeilsumme3.create();
    }
    public static AlgorithmObject maxTeilsumme2(){
        return MaxTeilsumme2.create();
    }
    public static AlgorithmObject maxTeilsummeRekursiv(){
        return MaxTeilsummeRekursiv.create();
    }
    public static AlgorithmObject maxTeilsumme1(){
        return MaxTeilsumme1.create();
    }
    public static AlgorithmObject maxTeilsumme2_buch(){
        return MaxTeilsumme2_buch.create();
    }

    private static Collection<AlgorithmObject> allAlgorithmsAsList(){
        return Arrays.asList(
                maxTeilsumme3(),
                maxTeilsumme2(),
                maxTeilsummeRekursiv(),
                maxTeilsumme1(),
                maxTeilsumme2_buch()
                
                );

    }

    public static List<AlgorithmObject> getAlgorithmsAsList(){
//        List<AlgorithmObject> tmp = new ArrayList<AlgorithmObject>();
//        tmp.addAll(allAlgorithmsAsList());
//        return tmp;
        return new ArrayList<AlgorithmObject>(allAlgorithmsAsList());

    }
    public static AlgorithmParameter newAlgoParameter(int[] valuesArray, MonitorRecord record){
        return AlgorithmParameterImpl.create(valuesArray, record);
    }
}

