/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;


/**
 *
 * @author Sven
 */
class AbstractAlgorithmObject implements AlgorithmObject {
    protected String name = "AbstractAlgorithmObject";
    protected AlgorithmParameter parameterPointer;
    protected AbstractAlgorithmObject(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void call(AlgorithmParameter parameter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void call_monitored(AlgorithmParameter parameter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AlgorithmParameter getParameter() {
        return parameterPointer;
    }
    
}

final class AlgorithmParameterImpl implements AlgorithmParameter {
    public int[] valuesArray;
    public MonitorRecord monitorRecord;
   
    
    private AlgorithmParameterImpl(){}
    public static AlgorithmParameter create(int[] valuesArray, MonitorRecord record){
        AlgorithmParameter tmp = new AlgorithmParameterImpl();
        tmp.setMonitorRecord(record);
        tmp.setValuesArray(valuesArray);
        return tmp;
    }
    @Override
    public int[] getValuesArray() {
        return valuesArray;
    }

    @Override
    public MonitorRecord getMonitorRecord() {
        return monitorRecord;
    }

    @Override
    public void setValuesArray(int[] ia) {
        valuesArray = ia;
    }

    @Override
    public void setMonitorRecord(MonitorRecord mr) {
        monitorRecord = mr;
    }


}
