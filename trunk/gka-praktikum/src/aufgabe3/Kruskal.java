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
            unusedEdgeSet = new HashSet(),
            graphEdgeSet = new HashSet(graph.edgeSet());

        int numberOfVertices = graph.vertexSet().size();
        double edgeWeight = 0d;
        Object edge,
               sourceVertex,
               targetVertex;

        boolean hasSource, hasTarget;

        while (graphT.vertexSet().size() < numberOfVertices){ //&& graphEdgeSet.size() != ZERO) {
            edge = (new ArrayList(graphEdgeSet)).get(ZERO);

            for (Object o : graphEdgeSet) {
                if (graph.getEdgeWeight(o) < graph.getEdgeWeight(edge)) {
                    edge = o;
                }
            }

            sourceVertex = graph.getEdgeSource(edge);
            targetVertex = graph.getEdgeTarget(edge);
            hasSource = graphT.vertexSet().contains(sourceVertex);
            hasTarget = graphT.vertexSet().contains(targetVertex);

            if(hasSource && !hasTarget || !hasSource && hasTarget || !hasSource && !hasTarget){
                boolean nTarget = graphTEdgeSet.contains((String)sourceVertex + targetVertex),
                        nTargetR = graphTEdgeSet.contains((String)targetVertex + sourceVertex);
                if(!nTarget && !nTargetR){
                    edgeWeight = graph.getEdgeWeight(edge);
                    graphT.addVertex(sourceVertex);
                    graphT.addVertex(targetVertex);
                    graphTEdgeSet.add((String)sourceVertex + targetVertex);
                    graphTEdgeSet.add((String)targetVertex + sourceVertex);
                    Graphs.addEdge(graphT,sourceVertex,targetVertex,edgeWeight);
                    System.out.println("added " + edge);
                }
            }else{
//                unusedEdgeSet.add(edge);
                System.out.println("Transitivitätsüberprüfung für Kante: " + edge);
                if (!checkTransitivity(graphT,sourceVertex,targetVertex)) {
                    Graphs.addEdge(graphT,sourceVertex,targetVertex,edgeWeight);
                    System.out.println("added " + edge);
                }
            }
            graphEdgeSet.remove(edge);
        }

//        --------------------------------------------------------------------------------------------------------------


        unusedEdgeSet.addAll(graphEdgeSet);


        while(graphT.edgeSet().size() != graphT.vertexSet().size() - 1) {

            edge = (new ArrayList(unusedEdgeSet)).get(ZERO);

            for (Object o : unusedEdgeSet) {
                if (graph.getEdgeWeight(o) < graph.getEdgeWeight(edge)) {
                    edge = o;
                }
            }

            edgeWeight = graph.getEdgeWeight(edge);
            sourceVertex = graph.getEdgeSource(edge);
            targetVertex = graph.getEdgeTarget(edge);
            System.out.println( edge +" source nachbarn" + Graphs.neighborListOf(graphT,sourceVertex));
            System.out.println( edge +" target nachbarn" + Graphs.neighborListOf(graphT,targetVertex));

            boolean nTarget = graphTEdgeSet.contains((String)sourceVertex + targetVertex),
                    nTargetR = graphTEdgeSet.contains((String)targetVertex + sourceVertex);
            if (!nTarget && !nTargetR) {
                System.out.println(edge + " ist in graphT nicht vorhanden!");
            } else {
                System.out.println(edge + " ist in graphT schon vorhanden!");
            }

            if (!checkTransitivity(graphT,sourceVertex,targetVertex)) {
                Graphs.addEdge(graphT,sourceVertex,targetVertex,edgeWeight);
                System.out.println("added " + edge);
            }

            unusedEdgeSet.remove(edge);

        }

        sout_graphInfo(graphT);

        return graphT;
    }

    private static void sout_graphInfo(Graph graphT) {
        System.out.println("GraphT, Vertices: " + graphT.vertexSet().size() + ", Edges: " + graphT.edgeSet().size());
    }

    private static boolean checkTransitivity(Graph graph, Object sourceVertex, Object targetVertex) {

        return (OptimalWay.dijkstra(graph, (String) sourceVertex, (String) targetVertex) != null);
    }

}
