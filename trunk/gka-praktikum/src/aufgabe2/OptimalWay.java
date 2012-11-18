package aufgabe2;

import aufgabe1.CustomVertex;
import org.jgrapht.Graph;

import java.util.*;

/**
 * Date: 18.11.12
 * Time: 23:30
 */
public class OptimalWay {

    final static String EMPTY = "";
    final static int DISTANCE_INIT = -1;

    public static List<CustomVertex> dijkstra(Graph graph, String sourceVertex, String targetVertex){
        Set<String> vertexSet = graph.vertexSet();
        Map<String,CustomVertex> customVertexMap = new HashMap<String, CustomVertex>();

        for (String s : vertexSet)
            customVertexMap.put(s,new CustomVertex(DISTANCE_INIT,getAdjacentVertexes(graph,s),s,false,EMPTY));

        //Init Start Vertex
        CustomVertex sourceCustomVertex = customVertexMap.get(sourceVertex);
        customVertexMap.remove(sourceVertex);
        sourceCustomVertex.setDistance(0).setPredecessor(sourceVertex).setOK();
        customVertexMap.put(sourceVertex,sourceCustomVertex);

        return null;
    }

    private static List<String> getAdjacentVertexes(Graph g, String sourceVertex) {
        Set<String> neighborList = new HashSet<String>();
        for (Object targetVertex : g.vertexSet()) {
            if (g.getEdge(sourceVertex, targetVertex) != null) {
                neighborList.add(targetVertex.toString());
            }
        }
        neighborList.remove(sourceVertex);

        return new ArrayList<String>(neighborList);
    }

}
