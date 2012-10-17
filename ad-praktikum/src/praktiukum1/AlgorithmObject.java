/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktiukum1;

/**
 *
 * @author abg667
 */
public interface AlgorithmObject {

    void call(AlgorithmParameter parameter);
    void call_monitored(AlgorithmParameter parameter);
    
    String getName();
    AlgorithmParameter getParameter();
    
}

