package aufgabe3;

import aufgabe1.TraverseGraphAlgorithms;
import aufgabe2.OptimalWay;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 07.12.12
 * Time: 14:12
 */
public class Kruskal {

    private static final int ZERO = 0;

    public static Graph kruskalAlgorithm(Graph graph){
        Graph graphT = new WeightedPseudograph(DefaultWeightedEdge.class);

        Set graphTEdgeSet = new HashSet<String>(),
            graphEdgeSet = new HashSet(graph.edgeSet());

        int numberOfVertices = graph.vertexSet().size(),
        graphZugriffe = 0;
        double edgeWeight = 0d;
        Object edge,
               sourceVertex,
               targetVertex;

        boolean hasSource, hasTarget;

        while (graphT.vertexSet().size() < numberOfVertices){ //&& graphEdgeSet.size() != ZERO) {
            edge = (new ArrayList(graphEdgeSet)).get(ZERO);
            graphZugriffe++;
            for (Object o : graphEdgeSet) {
                graphZugriffe++;
                if (graph.getEdgeWeight(o) < graph.getEdgeWeight(edge)) {
                    edge = o;
                }
            }

            graphZugriffe++;
            graphZugriffe++;
            graphZugriffe++;
            graphZugriffe++;
            sourceVertex = graph.getEdgeSource(edge);
            targetVertex = graph.getEdgeTarget(edge);
            hasSource = graphT.vertexSet().contains(sourceVertex);
            hasTarget = graphT.vertexSet().contains(targetVertex);

            boolean nSource,nTarget;
            nSource = graphTEdgeSet.contains((String)sourceVertex + targetVertex);
            nTarget = graphTEdgeSet.contains((String)targetVertex + sourceVertex);
            if(!nSource && !nTarget){
                if(!hasSource || !hasTarget){

                        edgeWeight = graph.getEdgeWeight(edge);
                        graphTEdgeSet.add((String)sourceVertex + targetVertex);
                        graphTEdgeSet.add((String)targetVertex + sourceVertex);
                        Graphs.addEdgeWithVertices(graphT,sourceVertex,targetVertex,edgeWeight);
                        //System.out.println("added " + edge + ", EdgeWeigh: " + edgeWeight);

                }else{
//                    //System.out.print("Transitivitätsüberprüfung für Kante: " + edge);
                    graphZugriffe++;
                    if (!checkTransitivity(graphT, sourceVertex, targetVertex)) {

                            edgeWeight = graph.getEdgeWeight(edge);
                            graphTEdgeSet.add((String)sourceVertex + targetVertex);
                            graphTEdgeSet.add((String)targetVertex + sourceVertex);
                            Graphs.addEdgeWithVertices(graphT, sourceVertex, targetVertex, edgeWeight);
                            //System.out.println("added " + edge + ", EdgeWeigh: " + edgeWeight);

                    } else {
//                        //System.out.println("FAIL");
                    }
                }
            }
            graphEdgeSet.remove(edge);

//            if(graphT.edgeSet().size() >= graphT.vertexSet().size() || graphT.edgeSet().size() > 199)
//                break;
        }

        double weightSum = 0;
        for (Object o : graphT.edgeSet()) {
            weightSum += graphT.getEdgeWeight(o);
        }
        //System.out.println("Kantengewichtssumme: " + weightSum + ", Zugriffe auf den graphen: " + graphZugriffe);

        sout_graphInfo(graphT);

        return graphT;
    }

    private static void sout_graphInfo(Graph graphT) {
        //System.out.println("GraphT, Vertices: " + graphT.vertexSet().size() + ", Edges: " + graphT.edgeSet().size());
    }

    private static boolean checkTransitivity(Graph graph, Object sourceVertex, Object targetVertex) {

        return (OptimalWay.dijkstra(graph, (String) sourceVertex, (String) targetVertex) != null);
    }

}
