package praktiukum1;

import java.util.Arrays;
import services.impl.FileManagerImpl;
import services.impl.RandomManagerImpl;
import services.service.FileManager;
import services.service.RandomManager;

import java.util.List;

/**
 * Test
 * @author Sven
 */
public class Runner {
    static FileManager fileManager = new FileManagerImpl();
    static RandomManager randomManager = new RandomManagerImpl();

    public static void main(String[] args) {

        
        startTests(AlgorithmFactory.getAlgorithmsAsList(),1000,100,false,true,10);
//        testOnGivenFile("folge.dat");   
//        testAlgoVsAlgo();
    }

    public static void startTests(List<AlgorithmObject> algoList,int maxSize,int stepRange,boolean exportFolgen, boolean exportTimeline, int iterations){
        int valuesArray[];

        if(iterations > 1)Monitor.setALGO_ITERATIONS(iterations);

        for (int size = stepRange; size <= maxSize; size+=stepRange){
            valuesArray = randomManager.initRandomArray(size, 100, -100);
            if(exportFolgen)fileManager.exportRandomSequence(valuesArray);
            testFolgeOnAllAlgos(valuesArray, algoList);  
        }
        fileManager.exportCSV(Monitor.getRecordMap(),Monitor.getAlgoIterations());
        if(exportTimeline) fileManager.exportTimelineCSV(Monitor.getRecordMap(),Monitor.getAlgoIterations());

    }


    public static void testFolgeOnAllAlgos(int[] folge,List<AlgorithmObject> algoList){
        for (AlgorithmObject algorithmObject : algoList) {
                MonitorRecord mr = Monitor.newAlgoRecord(algorithmObject.getName(), folge.length);

                for (int i = 0; i < Monitor.getAlgoIterations(); i++) {
                    Timer rawTimer = Timer.newTimer();
                    Timer monitoredTimer = Timer.newTimer();
                    AlgorithmParameter parameter = AlgorithmFactory.newAlgoParameter(folge,mr);
                                      
                    rawTimer.timerStart();
                    algorithmObject.call(parameter);
                    rawTimer.timerEnd();
                    
                    mr.resetMonitorRecord();
                    monitoredTimer.timerStart();
                    algorithmObject.call_monitored(parameter);
                    monitoredTimer.timerEnd();
                    
                    mr.setTimers(rawTimer, monitoredTimer);
                }
                
            }
    }

    public static void testOnGivenFile(String path){
        testFolgeOnAllAlgos(fileManager.fetchFolgeFromFile(path),AlgorithmFactory.getAlgorithmsAsList());
        fileManager.exportCSV(Monitor.getRecordMap(),Monitor.getAlgoIterations());
    }
    public static void singleTestCase(){
            
        AlgorithmObject algo3 = AlgorithmFactory.maxTeilsumme3();
        AlgorithmObject algo2 = AlgorithmFactory.maxTeilsumme2();

        int iArray[] = randomManager.initRandomArray(100, 100, -100);

        
        algo3.call(AlgorithmFactory.newAlgoParameter(iArray, Monitor.newAlgoRecord("maxTeilsumme3", iArray.length)));
        algo2.call(AlgorithmFactory.newAlgoParameter(iArray, Monitor.newAlgoRecord("maxTeilsumme2", iArray.length)));
        algo3.call_monitored(algo3.getParameter());
        algo2.call_monitored(algo2.getParameter());
        
        System.out.println(MonitorRecord.CSVFILEHEAD_SD);
        System.out.println(Monitor.get("maxTeilsumme3"+iArray.length));
        System.out.println(Monitor.get("maxTeilsumme2"+iArray.length));
        String x = "";
        for (int i : iArray) {
            x+= i+" ";
        }
        System.out.println(x);
    }
    public static void testAlgoVsAlgo(){
        startTests(Arrays.asList(AlgorithmFactory.maxTeilsumme2(),
                                 AlgorithmFactory.maxTeilsumme2_buch(),
                                 AlgorithmFactory.maxTeilsummeRekursiv()),
                                 10000,1000,false,false,1);
    }
}
