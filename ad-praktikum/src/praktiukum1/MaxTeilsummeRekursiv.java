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
    StringBuffer strackTrace = new StringBuffer();
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
    static int reM = 0, liM = 0, iS, iE,tmpMax = 0,calls = 0,rmax_calls = 0,lmax_calls = 0;

    private returnValues rechtesRandMax_monitored(final int[] folge, int links, int rechts) { // 2
    // requires 0 <= links <= rechts < folge.length
    // berechnet rechtes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        MonitorRecord mR = parameterPointer.getMonitorRecord();
        rmax_calls++;
                    StringBuffer tmp = new StringBuffer();
            for (int i = 0; i < calls; i++) {
                tmp.append("---");   
            }
            tmp.append("> ");
            //System.out.println("depth: "+calls+" "+tmp+" : rechtesRandMax_monitored called: links = "+links+" rechts = "+rechts);
        
        returnValues retVals = returnValues.vals(0, 0, rechts);    
        for (int i = rechts; i >= links; i--) {
            bisherSum += folge[i];
            mR.folgeHits++;
            //bisherMax = Math.max(bisherMax, bisherSum);
            if(bisherSum > bisherMax){
                bisherMax = bisherSum;
                retVals.startIndex = i;

            }
        }
        retVals.maxTeilsumme = bisherMax;
        //System.out.println("depth: "+calls+" "+tmp+" : --> return "+bisherMax+" startIndex: "+retVals.startIndex);
        return retVals;
    }

    private returnValues linkesRandMax_monitored(final int[] folge, int links, int rechts) {
    // requires 0 <= links <= rechts < folge.length
    // berechnet linkes Randmaximum in folge zwischen links und rechts
        int bisherMax = 0, bisherSum = 0;
        lmax_calls++;
            StringBuffer tmp = new StringBuffer();
            for (int i = 0; i < calls; i++) {
                tmp.append("---");   
            }
            tmp.append("> ");
            //System.out.println("depth: "+calls+" "+tmp+" : linkesRandMax_monitored called: links = "+links+" rechts = "+rechts);
        MonitorRecord mR = parameterPointer.getMonitorRecord();
        
        returnValues retVals = returnValues.vals(0, links, 0);
        for (int i = links; i <= rechts; i++) {
            bisherSum += folge[i];
            mR.folgeHits++;
           // bisherMax = Math.max(bisherMax, bisherSum);
            if(bisherSum > bisherMax){
                bisherMax = bisherSum;
                retVals.endIndex = i;
                
            }
        }
        retVals.maxTeilsumme = bisherMax;
        
        //System.out.println("depth: "+calls+" "+tmp+" : --> return "+bisherMax+" endIndex: "+retVals.endIndex);
        return retVals;
    }

        private returnValues maxTeilsummeRekursiv_monitored(final int[] folge, int links, int rechts) {
// requires 0 <= links <= rechts < folge.length
// berechnet maximale Teilsumme in folge zwischen links und rechts
            calls++;
            StringBuffer tmpBuff = new StringBuffer();
            for (int i = 0; i < calls; i++) {
                tmpBuff.append("---");   
            }
            tmpBuff.append("> ");
            //System.out.println("depth: "+calls+" "+tmpBuff+" : maxTeilsummeRekursiv_monitored called: links = "+links+" rechts = "+rechts);
        MonitorRecord mR = parameterPointer.getMonitorRecord();
        if (links == rechts) // nur ein Element
        {   
            mR.folgeHits++;
            int res = Math.max(0, folge[links]);
            //System.out.println("depth: "+calls+" "+tmpBuff+" : --> return "+res);
            calls--;
            return returnValues.vals(res, links, rechts);
        } else {
            final int mitte = (rechts + links) / 2;
            final returnValues maxLinks = maxTeilsummeRekursiv_monitored(folge, links, mitte);
            final returnValues maxRechts = maxTeilsummeRekursiv_monitored(folge, mitte + 1, rechts);


            final returnValues rechtesRandMax = rechtesRandMax_monitored(folge, links, mitte);// linke Hälfte
            final returnValues linkesRandMax = linkesRandMax_monitored(folge, mitte + 1, rechts);// rechte Hälfte

            returnValues retVal = returnValues.vals(rechtesRandMax.maxTeilsumme + linkesRandMax.maxTeilsumme, rechtesRandMax.startIndex, linkesRandMax.endIndex);
            if(maxLinks.maxTeilsumme > retVal.maxTeilsumme){
                retVal = maxLinks;  
            }
            if(maxRechts.maxTeilsumme > retVal.maxTeilsumme){
                retVal = maxRechts;
            }
            //System.out.println("depth: "+calls+" "+tmpBuff+" : --> return "+retVal);
            calls--;
            return retVal;
            //return Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
        }
    }

    private int maxTeilsummeRekursiv_monitored(final int[] folge) {
// berechnet maximale Teilsumme von folge
        MonitorRecord mR = parameterPointer.getMonitorRecord();
        returnValues tmp = maxTeilsummeRekursiv_monitored(folge, 0, folge.length - 1); 
        mR.maxTeilsumme= tmp.maxTeilsumme;
        mR.setIndicies(tmp.startIndex, tmp.endIndex);
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
        //System.out.println("lmax calls: "+lmax_calls);
        //System.out.println("rmax calls: "+rmax_calls);
        
    }  
}
    class returnValues{
        private returnValues(int maxTeilsumme,int startIndex,int endIndex){
            this.maxTeilsumme = maxTeilsumme;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        public static returnValues vals(int maxTeilsumme,int startIndex,int endIndex){
            return new returnValues(maxTeilsumme, startIndex, endIndex);
        }
        public int maxTeilsumme = 0;
        public int startIndex = 0;
        public int endIndex = 0;
        public String toString(){
            return "maxTeilsumme: "+maxTeilsumme+" start: "+startIndex+" end: "+endIndex;
        }
    }