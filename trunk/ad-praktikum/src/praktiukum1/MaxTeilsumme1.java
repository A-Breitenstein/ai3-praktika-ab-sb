/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

/**
 *
 * @author abg667
 */
final class MaxTeilsumme1 extends AbstractAlgorithmObject {
    private MaxTeilsumme1(){
        name = "MaxTeilsumme1";
    }
    public static AlgorithmObject create(){
        return new MaxTeilsumme1();
    }
    
   private int maxTeilsumme(final int folge[]){
        int teilSumMax = 0, rechtsRandMax = 0;
        
        for (int i : folge) {
            rechtsRandMax = Math.max(0, rechtsRandMax + i);
            teilSumMax = Math.max(teilSumMax, rechtsRandMax);
        }
        return teilSumMax;
    }
    
    private int maxTeilsumme_monitored(final int folge[]){
        MonitorRecord mr = parameterPointer.getMonitorRecord();
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
   
    
    @Override
    public void call(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxTeilsumme(parameter.getValuesArray());
    }

    @Override
    public void call_monitored(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxTeilsumme_monitored(parameter.getValuesArray());
    }
}
