package praktiukum1;

import java.util.Random;

/**
 *
 * @author abg667
 */
public class Main {
    
    final static String maxT3 = "maxTeilsumme3";
    final static String maxT2 = "maxTeilsumme2";
    final static String maxTR = "maxTeilsummeR";
    final static String maxT1 = "maxTeilsumme1";
    
    static int size = 100;

    public static void main(String[] args) {
        
        System.out.println(MonitorRecord.CSVFILEHEAD_SD);
        
        while(size <= 1000){
            
        int[] array = new int[size];
        
        Random rand = new Random();
        
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(200)-100;
        }
        
        MonitorRecord moniT3 = Monitor.newAlgoRecord(maxT3,size);
        MonitorRecord moniT2 = Monitor.newAlgoRecord(maxT2,size);
        MonitorRecord moniTR = Monitor.newAlgoRecord(maxTR, size);
        MonitorRecord moniT1 = Monitor.newAlgoRecord(maxT1, size);

        //MaxTeilsumme3
        moniT3.timerStart();
        maxteilsumme3(array);
        moniT3.timerEnd();
        
        moniT3.timerMonitoredStart();
//        moniT3.timerStart(moniT3.pElapsedTimeMonitored);
        maxteilsumme3_monitored(array);
//        moniT3.timerEnd(moniT3.pElapsedTimeMonitored);
        moniT3.timerMonitoredEnd();
        
        //MaxTeilsumme2
        moniT2.timerStart();
        maxteilsumme2_2(array);
        moniT2.timerEnd();

        moniT2.timerMonitoredStart();
        maxteilsumme2_2_monitored(array);
        moniT2.timerMonitoredStart();
        
        //MaxTeilsummeRekursiv
        moniTR.timerStart();
        maxTeilsummeRekursiv(array);
        moniTR.timerEnd();
        
        moniTR.timerMonitoredStart();
        maxTeilsummeRekursiv_monitored(array);
        moniTR.timerMonitoredEnd();

        //MaxTeilsumme
        moniT1.timerStart();
        maxTeilsumme(array);
        moniT1.timerEnd();
        
        moniT1.timerMonitoredStart();
        maxTeilsumme_monitored(array);
        moniT1.timerMonitoredEnd();
        
        //DEBUG
        System.out.println(moniT3);
        System.out.println(moniT2);
        System.out.println(moniTR);
        System.out.println(moniT1);
        
        size+=100;
        }
        
        //Monitor.exportCSV();
            
        //maxteilsumme2_1(array);
        //maxteilsumme2_3(array);
        //maxteilsumme2_4(array);
    }
    

    static void maxteilsumme3(final int array[]) {
        final int arrayLength = array.length;
        int biggestSum = 0, temp;

        for (int position = 0; position < arrayLength; position++) {
            for (int sector = position; sector < arrayLength; sector++) {

                temp = 0;

                for (int offset = position; offset <= sector; offset++) {
                    temp += array[offset];
                }
                if (temp > biggestSum) {
                    biggestSum = temp;
                }
            }
        }
    }
        static void maxteilsumme3_monitored(final int array[]) {
        MonitorRecord m3 = Monitor.get(maxT3+size);
        //m3.startTimer();
        
        final int arrayLength = array.length;
        int biggestSum = 0, temp;

        for (int position = 0; position < arrayLength; position++) {
            for (int sector = position; sector < arrayLength; sector++) {

                temp = 0;

                for (int offset = position; offset <= sector; offset++) {
                    temp += array[offset];
                    m3.folgeHits++;
                }
                if (temp > biggestSum) {
                    biggestSum = temp;
                    m3.setRange(position, sector);
                }
            }
        }
        m3.maxTeilsumme = biggestSum;
        //m3.endTimer(m3.pElapsedTimeMonitored);
    }

    static void maxteilsumme2_1(final int array[]) {
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0;

        for (int position = 0; position < arrayLength; position++) {

            memory[position] = new int[arrayLength - position + 1];

            for (int to = position + 1; to < memory[position].length; to++) {
                temp = to - 1;
                memory[position][to] = memory[position][temp] + array[temp];
                temp = memory[position][to];

            }

            if (temp > biggestSum) {
                biggestSum = temp;
            }

        }
//       System.out.println(biggestSum);
    }

    static void maxteilsumme2_2(final int array[]) {
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0;

        for (int position = 0; position < arrayLength; position++) {

            memory[position] = new int[arrayLength - position + 1];

            for (int to = position; to < memory[position].length - 1; to++) {
                temp = to + 1;
                memory[position][temp] = memory[position][to] + array[to];
                temp = memory[position][temp];

            }

            if (temp > biggestSum) {
                biggestSum = temp;
            }

        }
    }
    static void maxteilsumme2_2_monitored(final int array[]) {
        MonitorRecord m2 = Monitor.get(maxT2+size);
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0,pos2 = 0, to2 = 0;
            
        for (int position = 0; position < arrayLength; position++) {

            memory[position] = new int[arrayLength - position + 1];

            for (int to = position; to < memory[position].length - 1; to++) {
                temp = to + 1;
                memory[position][temp] = memory[position][to] + array[to];
                temp = memory[position][temp];
                m2.folgeHits++;
                m2.folgeHits++;
                m2.folgeHits++;
                m2.folgeHits++;
                if(to > to2)
                    to2 = to;

            }

         if (temp > biggestSum) {
                biggestSum = temp;
                 pos2 = position;
         }   

        }
        m2.setRange(pos2,to2);
        m2.maxTeilsumme = biggestSum;
    }

    static void maxteilsumme2_3(final int array[]) {
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0;

        for (int position = 0; position < arrayLength; position++) {

            memory[position] = new int[arrayLength - position + 1];

            for (int to = position + 1; to < memory[position].length; to++) {

                memory[position][to] = memory[position][to - 1] + array[to - 1];
                temp = memory[position][to];

            }

            if (temp > biggestSum) {
                biggestSum = temp;
            }

        }
    }

    static void maxteilsumme2_4(final int array[]) {
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0;

        for (int position = 0; position < arrayLength; position++) {

            memory[position] = new int[arrayLength - position + 1];

            for (int to = position; to < memory[position].length - 1; to++) {

                memory[position][to + 1] = memory[position][to] + array[to];
                temp = memory[position][to + 1];

            }

            if (temp > biggestSum) {
                biggestSum = temp;
            }

        }
//        System.out.println(biggestSum);
    }

    
    /*--------------------------------------------------------------------------
     *  
     * Algorithmus aus dem Buch "Grundkurs Algorithmen und Datenstrukturen in Java"
     * entnommen, Seite 15 bis 16.
     * 
     *--------------------------------------------------------------------------
     */

    private static int rechtesRandMax(final int[] folge, int links, int rechts) { // 2
// requires 0 <= links <= rechts < folge.length
// berechnet rechtes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        for (int i = rechts; i >= links; i--) {
            bisherSum += folge[i];
            bisherMax = Math.max(bisherMax, bisherSum);
        }
        return bisherMax;
    }

    private static int linkesRandMax(final int[] folge, int links, int rechts) {
// requires 0 <= links <= rechts < folge.length
// berechnet linkes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        for (int i = links; i <= rechts; i++) {
            bisherSum += folge[i];
            bisherMax = Math.max(bisherMax, bisherSum);
        }
        return bisherMax;
    }

    private static int maxTeilsummeRekursiv(final int[] folge, int links, int rechts) {
// requires 0 <= links <= rechts < folge.length
// berechnet maximale Teilsumme in folge zwischen links und rechts
        if (links == rechts) // nur ein Element
        {
            return Math.max(0, folge[links]);
        } else {
            final int mitte = (rechts + links) / 2;
            final int maxLinks = maxTeilsummeRekursiv(folge, links, mitte);
            final int maxRechts = maxTeilsummeRekursiv(folge, mitte + 1, rechts);
            final int rechtesMax = rechtesRandMax(folge, links, mitte);
// linke Hälfte
            final int linkesMax = linkesRandMax(folge, mitte + 1, rechts);
// rechte Hälfte
            return Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
        }
    }
    
        public static int maxTeilsummeRekursiv(final int[] folge) {
// berechnet maximale Teilsumme von folge
        return maxTeilsummeRekursiv(folge, 0, folge.length - 1);
    }

//    
//
//    
// ---------------------------------------------------------------------------   
//    
    /*--------------------------------------------------------------------------
     *  
     * Algorithmus aus dem Buch "Grundkurs Algorithmen und Datenstrukturen in Java"
     * entnommen, Seite 15 bis 16.
     * 
     *--------------------------------------------------------------------------
     */

 
    private static int maxTeilsummeRekursiv_monitored(final int[] folge, int links, int rechts) {
// requires 0 <= links <= rechts < folge.length
// berechnet maximale Teilsumme in folge zwischen links und rechts
        MonitorRecord mR = Monitor.get(maxTR+size);
        if (links == rechts) // nur ein Element
        {
            return Math.max(0, folge[links]);
        } else {
            final int mitte = (rechts + links) / 2;
            final int maxLinks = maxTeilsummeRekursiv_monitored(folge, links, mitte);
            final int maxRechts = maxTeilsummeRekursiv_monitored(folge, mitte + 1, rechts);
            final int rechtesMax = rechtesRandMax(folge, links, mitte);
            mR.folgeHits +=mitte-links;
// linke Hälfte
            final int linkesMax = linkesRandMax(folge, mitte + 1, rechts);
            mR.folgeHits +=rechts - mitte+1;

// rechte Hälfte
            
            return Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
        }
    }

    public static int maxTeilsummeRekursiv_monitored(final int[] folge) {
// berechnet maximale Teilsumme von folge
        MonitorRecord mR = Monitor.get(maxTR+size);
        mR.maxTeilsumme= maxTeilsummeRekursiv_monitored(folge, 0, folge.length - 1);
        return mR.maxTeilsumme;
    }
//    
//
//    
// ---------------------------------------------------------------------------   
//    
    static int maxTeilsumme(final int folge[]){
        int teilSumMax = 0, rechtsRandMax = 0;
        
        for (int i : folge) {
            rechtsRandMax = Math.max(0, rechtsRandMax + i);
            teilSumMax = Math.max(teilSumMax, rechtsRandMax);
        }
        return teilSumMax;
    }
    
    static int maxTeilsumme_monitored(final int folge[]){
        MonitorRecord m = Monitor.get(maxT1+size);
        int teilSumMax = 0, rechtsRandMax = 0, temp = 0, pos = 0;
        
        for (int i = 0; i<folge.length; i++) {
            
            rechtsRandMax = Math.max(0, rechtsRandMax + folge[i]);

            if(rechtsRandMax == 0) pos = i+1;

            if(rechtsRandMax > teilSumMax){
                m.setRange(pos, i);
            }

            teilSumMax = Math.max(teilSumMax, rechtsRandMax);

           
//            temp = Math.max(teilSumMax, rechtsRandMax);
                        
        }
        m.folgeHits = folge.length;

        m.maxTeilsumme = teilSumMax;
        return teilSumMax;
    }
}                   
