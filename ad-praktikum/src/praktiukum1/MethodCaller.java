package praktiukum1;

import services.impl.CallerManagerImpl;
import services.impl.RandomManagerImpl;
import services.service.CallerManager;
import services.service.RandomManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 13.10.12
 * Time: 14:21
 */
public class MethodCaller {
    final static List<String> AlgorithmsA1 = Arrays.asList(
            "maxTeilsumme",
            "maxTeilsumme_monitored",
            "maxTeilsumme2_2",
            "maxTeilsumme2_2_monitored",
            "maxTeilsumme2_buch",
            "maxTeilsumme2_buch_monitored",
            "maxTeilsumme3",
            "maxTeilsumme3_monitored",
            "maxTeilsummeRekursiv",
            "maxTeilsummeRekursiv_monitored"
    );
    private static RandomManager randomManager = new RandomManagerImpl();

    private static void methodCaller(CallerManager callerManager) throws InvocationTargetException, IllegalAccessException {

        Object AlgoClass = callerManager.retrieveAlgorithmClass();
        //Get Methods of o and remove unnecessary Methods, excluding Main
        Method[] methods = AlgoClass.getClass().getMethods();
        List<Method> methodList = new ArrayList<Method>(Arrays.asList(methods));
        methodList.removeAll(Arrays.asList(new Object().getClass().getMethods()));

        List<String> algoNames = callerManager.retrieveAlgorithmNames();

        //Schleifenbeginn usw. -->
        int[] i = randomManager.initRandomArray(100,callerManager.retrieveSequenceParameterFrom(),callerManager.retrieveSequenceParameterTo());

        //new AlgorithmParameter
        AlgorithmParameter algorithmParameter = AlgorithmParameterImpl.create(i, Monitor.newAlgoRecord("test", i.length));

        //initial method calling
        for(Method m : methodList){
            if(algoNames.contains(m.getName())){
                System.out.println("calling: " + m.getName()+"->");
                System.out.println(m.invoke(AlgoClass,algorithmParameter));
            }
        }

    }
    //PSVM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Object algorithmObject = new Algorithms();
        int[] range = {0,0};

        CallerManager cm = CallerManagerImpl.create(range,algorithmObject,AlgorithmsA1);

        methodCaller(cm);
    }
}
