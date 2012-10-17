/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author abg667
 */
public final class Monitor {
    private static int ALGO_ITERATIONS = 1;
    private static Map<String, MonitorRecord> recordMap = new HashMap<String, MonitorRecord>();

    public static MonitorRecord newAlgoRecord(String name, int quantity) {
        recordMap.put(name+quantity, MonitorRecord.makeRecord(name,quantity));
        return get(name+quantity);
    }
    public static MonitorRecord get(String name) {
        return recordMap.get(name);
    }

    static void setALGO_ITERATIONS(int iterations){ALGO_ITERATIONS = iterations;}

    static int getAlgoIterations() {return ALGO_ITERATIONS;}

    public static void exportTimeLines(){
                List<MonitorRecord> list = new ArrayList<MonitorRecord>(recordMap.values());
                Collections.sort(list);
                for (MonitorRecord mr : list){
                    System.out.println(mr.getTimeLineCVS());
                }
    }

    public static Map<String, MonitorRecord> getRecordMap() {
        return recordMap;
    }
    public static void reset(){
        recordMap.clear();
    }
}