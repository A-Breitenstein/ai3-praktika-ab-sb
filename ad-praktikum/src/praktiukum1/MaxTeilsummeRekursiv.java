/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

/**
 *
 * @author Sven
 */
final class MaxTeilsummeRekursiv extends AbstractAlgorithmObject {
    
    private MaxTeilsummeRekursiv(){
        name = "MaxTeilsummeRekursiv";
    }
    public static AlgorithmObject create(){
        return new MaxTeilsummeRekursiv();
    }
    
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
    
   private int maxTeilsummeRekursiv(final int[] folge) {
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

        MonitorRecord mR = parameterPointer.getMonitorRecord();
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

    private int maxTeilsummeRekursiv_monitored(final int[] folge) {
// berechnet maximale Teilsumme von folge
        MonitorRecord mR = parameterPointer.getMonitorRecord();
        mR.maxTeilsumme= maxTeilsummeRekursiv_monitored(folge, 0, folge.length - 1);
        return mR.maxTeilsumme;
    }
    @Override
    public void call(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxTeilsummeRekursiv(parameter.getValuesArray());
        

    }

    @Override
    public void call_monitored(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxTeilsummeRekursiv_monitored(parameter.getValuesArray());
    }  
}
