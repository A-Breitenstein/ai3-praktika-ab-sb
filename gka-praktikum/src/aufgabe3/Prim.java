package aufgabe3;

import aufgabe1.CustomVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 07.12.12
 * Time: 14:12
 */
public class Prim {

    Queue<CustomVertex> vertexQueue = new PriorityQueue<CustomVertex>();


    public void PrimAlgorithm(Graph graph){

        List edges = (List)graph.edgeSet();

        Object smallestEdge = edges.get(0);
        double smallestEdgeWeight = 0d,
               currentEdgeWeight;

        Graph graphT = new WeightedPseudograph(DefaultWeightedEdge.class);

        Collection<Object> graphTvertexes = graphT.vertexSet();

        Map<Object,Double> graphTEdges = new HashMap<Object,Double>();

        while (graphTvertexes.size() < graph.vertexSet().size()) {
            for(Object e : edges){
                smallestEdgeWeight = graph.getEdgeWeight(e);
                currentEdgeWeight = graph.getEdgeWeight(smallestEdge);
                if((smallestEdgeWeight <= currentEdgeWeight) && !graphTEdges.containsKey(smallestEdge))
                    smallestEdge = e;
            }

            graphTEdges.put(smallestEdge,smallestEdgeWeight);

            Object source = graph.getEdgeSource(smallestEdge),
                   target = graph.getEdgeTarget(smallestEdge);

            if (graphTvertexes.contains(source) || graphTvertexes.contains(target)) {
                graphT.addVertex(source);
                graphT.addVertex(target);
                graphT.addEdge(source,target);
            }

        }
    }
}
