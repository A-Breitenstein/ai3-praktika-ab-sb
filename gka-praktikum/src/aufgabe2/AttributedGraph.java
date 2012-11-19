package aufgabe2;

import org.jgrapht.Graph;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 19.11.12
 * Time: 18:26
 */
public interface AttributedGraph extends Graph {
    public int getAttribute(String vertex);
    public boolean isWeighted();
    public boolean isDirected();
    public Graph getGraph();
}
