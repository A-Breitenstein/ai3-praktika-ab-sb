package aufgabe3;

import org.jgraph.graph.Edge;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 12.12.12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class CustomEdge {
    Object edge;
    double weight;
    String source, target;

    private CustomEdge(Object edge, double weight, String source, String target) {
        this.edge = edge;
        this.weight = weight;
        this.source = source;
        this.target = target;
    }

    public static CustomEdge create(Object edge, double weight, String source, String target) {
        return new CustomEdge(edge, weight, source, target);
    }
}
