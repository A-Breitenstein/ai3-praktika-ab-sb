package praktiukum1;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 12.10.12
 * Time: 11:25
 */
public class Algorithms {



    //BEGIN:: Algorithm specified parameters ::BEGIN

        //maxTeilsummeRekursiv
        public static AlgorithmParameter parameterMaxTeilsummeRekursiv;

    //END:: Algorithm specified parameters ::END





    //Algorithms

    //#####maxTeilsumme1#####@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public int maxTeilsumme(AlgorithmParameter parameter){
        final int[] folge = parameter.getValuesArray();
        int teilSumMax = 0, rechtsRandMax = 0;

        for (int i : folge) {
            rechtsRandMax = Math.max(0, rechtsRandMax + i);
            teilSumMax = Math.max(teilSumMax, rechtsRandMax);
        }
        return teilSumMax;
    }

    public int maxTeilsumme_monitored(AlgorithmParameter parameter){
        final int[] folge = parameter.getValuesArray();
        MonitorRecord mr = parameter.getMonitorRecord();
        int teilSumMax = 0, rechtsRandMax = 0, pos = 0;

        for (int i = 0; i<folge.length; i++) {

            rechtsRandMax = Math.max(0, rechtsRandMax + folge[i]);


            if(rechtsRandMax == 0) pos = i+1;

            if(rechtsRandMax > teilSumMax){
                mr.setIndicies(pos, i);
            }
            teilSumMax = Math.max(teilSumMax, rechtsRandMax);

        }
        mr.folgeHits = folge.length;

        mr.maxTeilsumme = teilSumMax;
        return teilSumMax;
    }


    //#####maxTeilsumme2#####@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void maxTeilsumme2_2(AlgorithmParameter parameter) {
        final int[] array = parameter.getValuesArray();
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0;

        for (int position = 0; position < arrayLength; position++) {
            memory[position] = new int[arrayLength - position];
            memory[position][0] = array[position];

            for (int to = 1; to < memory[position].length; to++) {

                memory[position][to] = memory[position][to-1] + array[position+to];
                temp = memory[position][to];

                if (temp > biggestSum) biggestSum = temp;
            }
            //cause of the 0 case :/
            temp = memory[position][0];
            if (temp > biggestSum) biggestSum = temp;
        }
    }
    public void maxTeilsumme2_2_monitored(AlgorithmParameter parameter) {
        MonitorRecord m2 = parameter.getMonitorRecord();
        final int[] array = parameter.getValuesArray();
        final int arrayLength = array.length;
        int memory[][] = new int[arrayLength][];
        int biggestSum = 0, temp = 0,pos2 = 0, to2 = 0;

        for (int position = 0; position < arrayLength; position++) {
            memory[position] = new int[arrayLength - position];
            memory[position][0] = array[position];
            m2.folgeHits++;
            m2.memoryHits+=2;

            for (int to = 1; to < memory[position].length; to++) {

                memory[position][to] = memory[position][to-1] + array[position+to];
                temp = memory[position][to];
                m2.memoryHits+=3;
                m2.folgeHits++;
                if (temp > biggestSum){
                    biggestSum = temp;
                    to2 = position+to;
                    pos2 = position;
                }
            }
            //cause of the 0 case :/
            temp = memory[position][0];
            m2.memoryHits++;
            if (temp > biggestSum) biggestSum = temp;

        }

        m2.setRange(pos2,to2);
        m2.maxTeilsumme = biggestSum;
    }

    //#####maxTeilsumme2_buch#####@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void maxTeilsumme2_buch(AlgorithmParameter parameter) {
        final int[] folge = parameter.getValuesArray();
        final int n = folge.length;
        int[][] teilsummen = new int[n][];
        /* Dreiecktabelle der Teilsummen: für i
        teilsummen[i][j] ist Teilsumme i bis j, d.h. folge[i]+folge[i+1]+ … +folge[j] */
        /* Jede Komponente teilsummen[von] mit einer int-Reihung der richtigen Länge
        (nämlich n-von) initialisieren und die 0-te Komponente dieser int-Reihung mit dem
        int-Wert folge[von] (mit der Summe der Teilfolge folge[von..von]) initialisieren: */
        for (int von = 0; von < folge.length; von++) {
            teilsummen[von] = new int[n - von]; // von-te Zeile des Dreiecks erzeugen
            teilsummen[von][0] = folge[von]; // 0-te Komponente initialisieren
        }
// die Spalte 0 von teilsummen wurde initialisiert; jetzt die übrigen Spalten initialisieren:
        for (int von = 0; von < n; von++) {
            for (int bis = 1; bis < n - von; bis++) {
                teilsummen[von][bis] = teilsummen[von][bis - 1] + folge[von + bis];
            }
        }
        /* Teilsummen 0 bis 1, …, 0 bis n-1:
        Auf die vorherige Teilsumme wurde das nächste Element addiert. */
        // die maximale Komponente in teilsummen ermitteln:
        int maxSumme = 0;
        for (int von = 0; von < n; von++) {
            for (int bis = 0; bis < n - von; bis++) {
                maxSumme = Math.max(maxSumme, teilsummen[von][bis]);
            }
        }

    }

    public void maxTeilsumme2_buch_monitored(AlgorithmParameter parameter) {
        final int[] folge = parameter.getValuesArray();
        MonitorRecord m2 = parameter.getMonitorRecord();
        final int n = folge.length;
        int[][] teilsummen = new int[n][];

        for (int von = 0; von < folge.length; von++) {
            teilsummen[von] = new int[n - von];
            teilsummen[von][0] = folge[von];
            m2.folgeHits++;
            m2.memoryHits+=2;
        }

        for (int von = 0; von < n; von++) {
            for (int bis = 1; bis < n - von; bis++) {
                teilsummen[von][bis] = teilsummen[von][bis - 1] + folge[von + bis];
                m2.folgeHits++;
                m2.memoryHits+=2;
            }
        }

        int maxSumme = 0,v = 0,b = 0,tmp = 0;
        for (int von = 0; von < n; von++) {
            for (int bis = 0; bis < n - von; bis++) {
                tmp = teilsummen[von][bis];
                m2.memoryHits++;
                //maxSumme = Math.max(maxSumme, teilsummen[von][bis]);
                if(maxSumme < tmp){
                    maxSumme = tmp;
                    v = von;
                    b = von+bis;
                }

            }
        }
        m2.setIndicies(v, b);
        m2.maxTeilsumme = maxSumme;

    }
    //#####maxTeilsumme3#####@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void maxTeilsumme3(AlgorithmParameter parameter) {
        final int[] array = parameter.getValuesArray();
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
    public void maxTeilsumme3_monitored(AlgorithmParameter parameter){
        final int[] array = parameter.getValuesArray();
        MonitorRecord m3 = parameter.getMonitorRecord();
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
    }
    //#####maxTeilsummeRekursiv#####@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /*--------------------------------------------------------------------------
    *
    * Algorithmus aus dem Buch "Grundkurs Algorithmen und Datenstrukturen in Java"
    * entnommen, Seite 15 bis 16.
    *
    *--------------------------------------------------------------------------
    */

    private int rechtesRandMax(final int[] folge, int links, int rechts) { // 2
// requires 0 <= links <= rechts < folge.length
// berechnet rechtes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        for (int i = rechts; i >= links; i--) {
            bisherSum += folge[i];
            bisherMax = Math.max(bisherMax, bisherSum);
        }
        return bisherMax;
    }

    private int linkesRandMax(final int[] folge, int links, int rechts) {
// requires 0 <= links <= rechts < folge.length
// berechnet linkes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        for (int i = links; i <= rechts; i++) {
            bisherSum += folge[i];
            bisherMax = Math.max(bisherMax, bisherSum);
        }
        return bisherMax;
    }

    private int maxTeilsummeRekursiv(final int[] folge, int links, int rechts) {
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

    public int maxTeilsummeRekursiv(AlgorithmParameter parameter) {
        final int[] folge = parameter.getValuesArray();
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
    static int reM = 0, liM = 0, iS, iE;

    private int rechtesRandMax_monitored(final int[] folge, int links, int rechts) { // 2
        // requires 0 <= links <= rechts < folge.length
        // berechnet rechtes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        for (int i = rechts; i >= links; i--) {
            bisherSum += folge[i];
            bisherMax = Math.max(bisherMax, bisherSum);
        }

        if(reM < bisherMax){
            iS = links;
        }

        return bisherMax;
    }

    private int linkesRandMax_monitored(final int[] folge, int links, int rechts) {
        // requires 0 <= links <= rechts < folge.length
        // berechnet linkes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        for (int i = links; i <= rechts; i++) {
            bisherSum += folge[i];
            bisherMax = Math.max(bisherMax, bisherSum);
        }

        if( liM < bisherMax){
            iE = rechts;
        }

        return bisherMax;
    }

    private int maxTeilsummeRekursiv_monitored(final int[] folge, int links, int rechts) {
// requires 0 <= links <= rechts < folge.length
// berechnet maximale Teilsumme in folge zwischen links und rechts

        MonitorRecord mR = parameterMaxTeilsummeRekursiv.getMonitorRecord();
        if (links == rechts) // nur ein Element
        {
            return Math.max(0, folge[links]);
        } else {
            final int mitte = (rechts + links) / 2;
            final int maxLinks = maxTeilsummeRekursiv_monitored(folge, links, mitte);
            final int maxRechts = maxTeilsummeRekursiv_monitored(folge, mitte + 1, rechts);

//            lm = maxLinks;
//            rm = maxRechts;

            final int rechtesMax = rechtesRandMax_monitored(folge, links, mitte);// linke Hälfte
            final int linkesMax = linkesRandMax_monitored(folge, mitte + 1, rechts);// rechte Hälfte

            if(rechtesMax+linkesMax >= reM+liM){

                if(rechtesMax > reM){reM = rechtesMax;} else {}

                if(linkesMax > liM){liM = linkesMax;} else {}

            }

            mR.addFolgeHits(rechts - mitte+1+mitte-links);
            mR.setIndicies(iS,iE);
            return Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
        }
    }

    public int maxTeilsummeRekursiv_monitored(AlgorithmParameter parameter) {
        parameterMaxTeilsummeRekursiv = parameter;
        final int[] folge = parameterMaxTeilsummeRekursiv.getValuesArray();

// berechnet maximale Teilsumme von folge
        MonitorRecord mR = parameterMaxTeilsummeRekursiv.getMonitorRecord();
        mR.maxTeilsumme= maxTeilsummeRekursiv_monitored(folge, 0, folge.length - 1);
        return mR.maxTeilsumme;
    }
}
