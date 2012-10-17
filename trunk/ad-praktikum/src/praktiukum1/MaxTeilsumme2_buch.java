/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

/**
 *
 * @author abg667
 */
final class MaxTeilsumme2_buch extends AbstractAlgorithmObject {

    private MaxTeilsumme2_buch() {
        name = "MaxTeilsumme2_buch";
    }

    public static AlgorithmObject create() {
        return new MaxTeilsumme2_buch();
    }

    private void maxteilsumme2_buch(final int folge[]) {
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

    private void maxteilsumme2_buch_monitored(final int folge[]) {
        MonitorRecord m2 = parameterPointer.getMonitorRecord();
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

    @Override
    public void call(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxteilsumme2_buch(parameter.getValuesArray());

    }

    @Override
    public void call_monitored(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxteilsumme2_buch_monitored(parameter.getValuesArray());

    }
}
