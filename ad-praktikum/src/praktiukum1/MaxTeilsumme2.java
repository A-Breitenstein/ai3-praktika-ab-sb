/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

/**
 *
 * @author abg667
 */
final class MaxTeilsumme2 extends AbstractAlgorithmObject {
        private MaxTeilsumme2(){
        name = "MaxTeilsumme2";
    }
    public static AlgorithmObject create(){
        return new MaxTeilsumme2();
    }
   private void maxteilsumme2_2(final int array[]) {
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
    private void maxteilsumme2_2_monitored(final int array[]) {
        MonitorRecord m2 = parameterPointer.getMonitorRecord();
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
    
    @Override
    public void call(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxteilsumme2_2(parameter.getValuesArray());

    }

    @Override
    public void call_monitored(AlgorithmParameter parameter) {
        parameterPointer = parameter;
        maxteilsumme2_2_monitored(parameter.getValuesArray());
    }
}
