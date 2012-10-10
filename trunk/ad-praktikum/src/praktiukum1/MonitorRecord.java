/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sven
 */
public class MonitorRecord implements Comparable {
    final static int ZERO = 0;

    public final static String CSVFILEHEAD_SD = "Anzahl;Algorithmus;maxTeilsumme;Index1;Index2;Zeit (inkl. Zaehlen);Zeit (exkl. Zaehlen);Summe aller Zugriffe;Matrix Zugriffe";
    public final static String CSVFILEHEAD_TL = "Algorithmus;Anzahl;TimelineValue;Zeit (inkl. Zaehlen);Zeit (exkl. Zaehlen)";


    int quantity = 0, maxTeilsumme = 0, folgeHits = 0, memoryHits = 0;
    int maxTeilsummeIndices[] = new int[2];
    String algoName;
    //Timer pElapsedTime = Timer.newTimer(), pElapsedTimeMonitored = Timer.newTimer();
    Timer pRawTimer,pMonitoredTimer;
    List<TimeInNsec> timeValues = new ArrayList<TimeInNsec>();
   

    private MonitorRecord(String name, int quantity) {
        this.algoName = name;
        this.quantity = quantity;
    }

    public void addFolgeHits(int hits){
        folgeHits += hits;
    }
    public void addMemoryHits(int hits){
        memoryHits += hits;
    }

    private int getArrayHits() {
        return folgeHits + memoryHits;
    }

    @Deprecated
    public void setRange(int startIndex, int endIndex){
        setIndicies(startIndex, endIndex);
//        this.startIndex = startIndex;
//        this.endIndex = endIndex;
    }

    public void setIndicies(int startIndex, int endIndex){
        this.maxTeilsummeIndices[0] = startIndex;
        this.maxTeilsummeIndices[1] = endIndex;
    }
    public int getStartIndex(){
        return maxTeilsummeIndices[0];
    }

    public int getEndIndex(){
        return maxTeilsummeIndices[1];
    }
    public void timerStart(){
        resetMonitorRecord();
        pRawTimer.timerStart();
    }
    public void timerEnd(){
        pRawTimer.timerEnd();
    }
    public void timerMonitoredStart(){
        pMonitoredTimer.timerStart();
    }
    public void timerMonitoredEnd(){
        pMonitoredTimer.timerEnd();
    }
    public void resetMonitorRecord(){
        folgeHits = 0;
        memoryHits = 0;   
    }
    public void setTimers(Timer rawTimer,Timer monitoredTimer){
        pRawTimer = rawTimer;
        pMonitoredTimer = monitoredTimer;
        timeValues.add(TimeInNsec.val(pRawTimer.getElapsedTime(), pMonitoredTimer.getElapsedTime()));     
    }
    @Deprecated
    public static MonitorRecord makeRecord(String name) {
        return new MonitorRecord(name,ZERO);
    }
    public static MonitorRecord makeRecord(String name, int quantity) {
        return new MonitorRecord(name, quantity);
    }
    @Deprecated
    public String getTimeLine(){
        String tmp = algoName+" Anzahl "+quantity+" Iterations "+timeValues.size()+"\n";
        tmp+="timeRaw    ;     timeMonitored \n";
        for (TimeInNsec elem : timeValues) {
            tmp+=elem.toString()+"\n";
        }
        return tmp;
    }
    public String getTimeLineCVS(){
        TimeInNsec elem = timeValues.get(0);
        return algoName+";"+quantity+";"+timeValues.size()+";"+ elem.getTimeRawMsec()+";"+elem.getTimeMonitoredMsec();
    }
    private void getMonitoredAvarage(){
        double accu1 = 0d;
        double accu2 = 0d;
        for (TimeInNsec elem : timeValues){
            accu1+=elem.timeRaw;
            accu2+=elem.timeMonitored;
        }
        int size = timeValues.size();
        accu1/=size;
        accu2/=size;
    }
    @Override
    public String toString() {
        
        double accu1 = 0d;
        double accu2 = 0d;
        for (TimeInNsec elem : timeValues){
            accu1+=elem.timeRaw;
            accu2+=elem.timeMonitored;
        }
        int size = timeValues.size();
        accu1/=size;
        accu2/=size;
        TimeInNsec tms = TimeInNsec.val(accu1,accu2);
        
        return quantity + ";" + algoName + ";" + maxTeilsumme + ";" + getStartIndex() + ";" + getEndIndex()
                + ";" + tms.getTimeMonitoredMsec() + ";" + tms.getTimeRawMsec() + ";" + getArrayHits()+";"+memoryHits;
    }


    @Override
    public int compareTo(Object o) {
        //TODO: o != null ist immer WAHR wenn sie erreicht wird !!!
        if(o instanceof MonitorRecord && o != null){
            MonitorRecord mr = (MonitorRecord)o;
            int compareval = Integer.compare(this.quantity, mr.quantity);
            if(compareval == 0){
                return this.algoName.compareTo(mr.algoName);
            }else return compareval;
        }else return 0;
    }
}
class Timer {

    private double nsec_StartedTime = 0.d;
    private double nsec_ElapsedTime = 0.d;
    private Timer(double nsec_start,double nsec_elapsed){
        this.nsec_StartedTime = nsec_start;
        this.nsec_ElapsedTime = nsec_elapsed;
    }
    public static Timer newTimer(){
        return new Timer(0d,0d);
    }
    private static Timer newTimer(double nsec_start,double nsec_elapsed){
        return new Timer(nsec_start,nsec_elapsed);
    }

    public void setElapsedTime(double x) {
        nsec_ElapsedTime = x;
    }
    public double getElapsedTime() {
        return nsec_ElapsedTime;
    }

    public void timerStart(){
        nsec_StartedTime = nanoTime();
    }
    public void timerEnd(){
        nsec_ElapsedTime = nanoTime() - nsec_StartedTime;
        }

    public long nanoTime(){
        return System.nanoTime();
    }
    public double getMS(){
        return nsec_ElapsedTime /1000000.d;
    }
    public String getSec(){
        return  String.format("%.4f", nsec_ElapsedTime /1000000000.d);
    }
    public String getMsec(){
        return String.format("%.4f", getMS());
    }

}
class TimeInNsec{
    public double timeRaw = 0d;
    public double timeMonitored = 0d;
    private TimeInNsec(double nsec1,double nsec2){
        this.timeRaw = nsec1;
        this.timeMonitored = nsec2;
    }
    public static TimeInNsec val(double timeRaw,double timeMonitored){
        return new TimeInNsec(timeRaw,timeMonitored);
    }
    public String toString(){
        return getTimeRawMsec()+" ; "+getTimeMonitoredMsec();
    }
    public String getTimeRawMsec(){
        return String.format("%.4f", timeRaw /1000000.d);
    }
    public String getTimeMonitoredMsec(){
        return String.format("%.4f", timeMonitored /1000000.d);
    }
}

