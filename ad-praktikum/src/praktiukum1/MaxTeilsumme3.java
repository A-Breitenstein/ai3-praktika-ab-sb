/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

/**
 *
 * @author Sven
 */
final class MaxTeilsumme3 extends AbstractAlgorithmObject {
    
    private MaxTeilsumme3(){
        name = "MaxTeilsumme3";
    }
    public static AlgorithmObject create(){
        return new MaxTeilsumme3();
    }
    
   private void maxteilsumme3(final int array[]) {
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
   private void maxteilsumme3_monitored(final int array[]){
        MonitorRecord m3 = parameterPointer.getMonitorRecord();
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
   
   
    @Override
    public void call(AlgorithmParameter parameter) {
        parameterPointer = parameter;

            maxteilsumme3(parameter.getValuesArray());
    }

    @Override
    public void call_monitored(AlgorithmParameter parameter) {
        parameterPointer = parameter;
            maxteilsumme3_monitored(parameter.getValuesArray());
    }
    
}
