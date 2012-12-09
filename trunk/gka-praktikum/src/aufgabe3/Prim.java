package aufgabe3;

import aufgabe1.CustomVertex;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
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


    public static Graph primAlgorithm(Graph graph){
        boolean firstVertexSet = false;
        List edges = new ArrayList(graph.edgeSet());

        Graph graphT = new WeightedPseudograph(DefaultWeightedEdge.class);

        Map<String,String> graphTEdges = new HashMap<String,String>();

        Object smallestEdge, source, target;
        double smallestEdgeWeight = 0d;

        List yetNotConnectedEdges = new ArrayList();

        int edgeMapper = 0;

        while (graphT.vertexSet().size() < graph.vertexSet().size()) {
            if(!edges.isEmpty()){
                smallestEdge = edges.get(0);


                for (Object edge : edges) {
                    if(!yetNotConnectedEdges.contains(edge)){
                        if(graph.getEdgeWeight(edge) < graph.getEdgeWeight(smallestEdge)){
                            smallestEdge = edge;
                        }
                    }
                }

                smallestEdgeWeight = graph.getEdgeWeight(smallestEdge);
                source = graph.getEdgeSource(smallestEdge);
                target = graph.getEdgeTarget(smallestEdge);




                if(!firstVertexSet){
                    graphT.addVertex(source);
                    firstVertexSet = !firstVertexSet;
                }

                if ((graphT.vertexSet().contains(source) && !graphT.vertexSet().contains(target))||(!graphT.vertexSet().contains(source) && graphT.vertexSet().contains(target))) {
                    String nTarget = graphTEdges.get((String)source + target),
                           nTargetR = graphTEdges.get((String)target + source);
                    if(nTarget == null && nTargetR == null){
                    graphT.addVertex(source);
                    graphT.addVertex(target);
                    Graphs.addEdge(graphT,source,target,smallestEdgeWeight);

                    System.out.print("Edge-" + edgeMapper + " :");
                    sout_addEdgeToMap(smallestEdge, smallestEdgeWeight);
                    edgeMapper++;

                    graphTEdges.put((String)source + target,"");
                    }

                }else{
                    yetNotConnectedEdges.add(smallestEdge);
                }
                edges.remove(smallestEdge);
            }else{
                edges.addAll(yetNotConnectedEdges);
                yetNotConnectedEdges.clear();
            }

        }
        System.out.println("Anzahl der hinzugefügten Kanten: " + edgeMapper + " Anzahl der Elemente in AddedEdgeMap: " + graphTEdges.keySet().size());

        return graphT;
    }

    private static void sout_addEdgeToMap(Object smallestEdge, double smallestEdgeWeight) {
        System.out.println("Edge added to Map: " + smallestEdge + ", EdgeWeight: " + smallestEdgeWeight);
    }
}
