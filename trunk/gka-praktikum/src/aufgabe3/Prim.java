package aufgabe3;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
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


    public static Graph primAlgorithm(Graph graph) {

        int edgeMapper = 0,
            graphZugriffe = 0;

        Queue<CustomEdge> edges;

        edges = createPriorityQueue(graph);
        graphZugriffe+=graph.edgeSet().size();

        boolean firstVertexSet = false;

//        List edges = new ArrayList(graph.edgeSet());

        Graph graphT = new WeightedPseudograph(DefaultWeightedEdge.class);

        Map<String, String> graphTEdges = new HashMap<String, String>();

        Object smallestEdge, source, target;
        double smallestEdgeWeight;

        List<CustomEdge> yetNotConnectedEdges = new ArrayList<CustomEdge>();


        while (graphT.vertexSet().size() < graph.vertexSet().size()) {
            if (!edges.isEmpty()) {
//                smallestEdge = edges.get(0);
                CustomEdge customEdge = edges.poll();
                graphZugriffe++;
                smallestEdge = customEdge.edge;



                smallestEdgeWeight = customEdge.weight;
                source = customEdge.source;
                target = customEdge.target;


                if (!firstVertexSet) {
                    graphT.addVertex(source);
                    firstVertexSet = !firstVertexSet;
                }

                if ((graphT.vertexSet().contains(source) && !graphT.vertexSet().contains(target)) || (!graphT.vertexSet().contains(source) && graphT.vertexSet().contains(target))) {
                    String nTarget = graphTEdges.get((String) source + target),
                            nTargetR = graphTEdges.get((String) target + source);
                    if (nTarget == null && nTargetR == null) {
                        graphT.addVertex(source);
                        graphT.addVertex(target);
                        Graphs.addEdge(graphT, source, target, smallestEdgeWeight);

                        //System.out.print("Edge-" + edgeMapper + " :");
                        sout_addEdgeToMap(smallestEdge, smallestEdgeWeight);
                        edgeMapper++;

                        graphTEdges.put((String) source + target, "");
                        edges.addAll(yetNotConnectedEdges);
                        yetNotConnectedEdges.clear();
                    }
//
                } else {
                    yetNotConnectedEdges.add(customEdge);
                }
            }

        }
        //System.out.println("Anzahl der hinzugefügten Kanten: " + edgeMapper + " Anzahl der Elemente in AddedEdgeMap: " + graphTEdges.keySet().size());

        double weightSum = 0;
        for (Object o : graphT.edgeSet()) {
            weightSum += graphT.getEdgeWeight(o);
        }

        System.out.println("Kantengewichtssumme: " + weightSum );//+ ", Zugriffe auf den graphen: " + graphZugriffe);

        return graphT;
    }


    public static Graph primAlgorithmFiboHeap(Graph graph) {

        FibonacciHeap<CustomEdge> fibonacciHeap;

        fibonacciHeap = createFiboHeap(graph);

        boolean firstVertexSet = false;

        Graph graphT = new WeightedPseudograph(DefaultWeightedEdge.class);

        Map<String, String> graphTEdges = new HashMap<String, String>();

        Object smallestEdge, source, target;
        double smallestEdgeWeight;

        List<CustomEdge> yetNotConnectedEdges = new ArrayList<CustomEdge>();

        int edgeMapper = 0;

        while (graphT.vertexSet().size() < graph.vertexSet().size()) {

            if (!fibonacciHeap.isEmpty()) {
//                smallestEdge = edges.get(0);
                FibonacciHeap.Entry customEdgeEntry = fibonacciHeap.dequeueMin();
                CustomEdge customEdge = (CustomEdge) customEdgeEntry.getValue();
                smallestEdge = customEdge.edge;



                smallestEdgeWeight = customEdge.weight;
                source = customEdge.source;
                target = customEdge.target;


                if (!firstVertexSet) {
                    graphT.addVertex(source);
                    firstVertexSet = !firstVertexSet;
                }

                if ((graphT.vertexSet().contains(source) && !graphT.vertexSet().contains(target)) || (!graphT.vertexSet().contains(source) && graphT.vertexSet().contains(target))) {
                    String nTarget = graphTEdges.get((String) source + target),
                            nTargetR = graphTEdges.get((String) target + source);
                    if (nTarget == null && nTargetR == null) {
                        graphT.addVertex(source);
                        graphT.addVertex(target);
                        Graphs.addEdge(graphT, source, target, smallestEdgeWeight);

                        //System.out.print("Edge-" + edgeMapper + " :");
                        sout_addEdgeToMap(smallestEdge, smallestEdgeWeight);
                        edgeMapper++;

                        graphTEdges.put((String) source + target, "");

                        for (CustomEdge yNCE : yetNotConnectedEdges) {
                            fibonacciHeap.enqueue(yNCE, yNCE.weight);
                        }
                        yetNotConnectedEdges.clear();
                    }
//
                } else {
                    yetNotConnectedEdges.add(customEdge);
                }
            }

        }
        //System.out.println("Anzahl der hinzugefügten Kanten: " + edgeMapper + " Anzahl der Elemente in AddedEdgeMap: " + graphTEdges.keySet().size());

        double weightSum = 0;
        for (Object o : graphT.edgeSet()) {
            weightSum += graphT.getEdgeWeight(o);
        }

        System.out.println("Kantengewichtssumme: " + weightSum);

        return graphT;
    }

    private static Queue<CustomEdge> createPriorityQueue(Graph graph) {
        PriorityQueue<CustomEdge> customEdges = new PriorityQueue<CustomEdge>(200000, new Comparator<CustomEdge>() {

            @Override
            public int compare(CustomEdge o1, CustomEdge o2) {
                return Double.compare(o1.weight, o2.weight);
            }
        });

        for (Object o : graph.edgeSet()) {
            customEdges.add(CustomEdge.create(o, graph.getEdgeWeight(o), (String) graph.getEdgeSource(o), (String) graph.getEdgeTarget(o)));
        }

        return customEdges;
    }

    private static FibonacciHeap createFiboHeap(Graph graph) {
        FibonacciHeap<CustomEdge> customEdgeFibonacciHeap = new FibonacciHeap<CustomEdge>();

        for (Object o : graph.edgeSet()) {
            CustomEdge customEdge = CustomEdge.create(o, graph.getEdgeWeight(o), (String) graph.getEdgeSource(o), (String) graph.getEdgeTarget(o));
            customEdgeFibonacciHeap.enqueue(customEdge, customEdge.weight);
        }

        return customEdgeFibonacciHeap;
    }

    private static void sout_addEdgeToMap(Object smallestEdge, double smallestEdgeWeight) {
        //System.out.println("Edge added to Map: " + smallestEdge + ", EdgeWeight: " + smallestEdgeWeight);
    }

}
