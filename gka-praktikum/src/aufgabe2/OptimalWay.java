package aufgabe2;

import aufgabe1.CustomVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

/**
 * Date: 18.11.12
 * Time: 23:30
 */
public class OptimalWay {

    final static String EMPTY = "";
    final static int OMEGA = Integer.MAX_VALUE;

    public static List<CustomVertex> dijkstra(Graph graph, String sourceVertex, String targetVertex){
        Set<String> vertexSet = graph.vertexSet();
        Map<String,CustomVertex> customVertexMap = new HashMap<String, CustomVertex>();
        double distance,newDistance;
        for (String s : vertexSet)
            customVertexMap.put(s,new CustomVertex(OMEGA,getAdjacentVertexes(graph,s),s,false,EMPTY));

        //Init Start Vertex
        customVertexMap.get(sourceVertex).setDistance(0).setPredecessor(sourceVertex).setOK();
        for (String adjacentVertex : customVertexMap.get(sourceVertex).adjacentStringVertexes) {
            distance = graph.getEdgeWeight(graph.getEdge(sourceVertex, adjacentVertex));
            customVertexMap.get(adjacentVertex).setDistance(distance).setPredecessor(sourceVertex);
        }



        while(!isTargetVertexOK(customVertexMap,targetVertex)){
            for (String vertexName : customVertexMap.keySet()) {
                CustomVertex vertex =customVertexMap.get(vertexName);

                if(vertex.getDistance() < Integer.MAX_VALUE && !vertex.isOK()){
                    for (String adjacentVertexName : vertex.adjacentStringVertexes) {
                        CustomVertex adjacentVertex = customVertexMap.get(adjacentVertexName);
                        if(!adjacentVertex.isOK()){

                            distance = graph.getEdgeWeight(graph.getEdge(vertex.label, adjacentVertexName));
                            newDistance = vertex.getDistance()+distance;
                            if(newDistance < adjacentVertex.getDistance())
                                adjacentVertex.setDistance(newDistance).setPredecessor(vertex.label);
                        }
                    }


                }
            }
            getSmallestDistanceNeighbor(customVertexMap);

        }
        return null;
    }

    private static String getSmallestDistanceNeighbor(Map<String,CustomVertex> map) {
        String smallestDistanceVertex = "";
        double distancei = Double.MAX_VALUE;
        for (CustomVertex customVertex : map.values()) {

            if(!customVertex.isOK() && customVertex.getDistance()<distancei){
                smallestDistanceVertex = customVertex.label;
                distancei = customVertex.getDistance();
            }

        }

        if(smallestDistanceVertex.isEmpty()){System.out.println("keinen kleinsten Vertex gefunden!"); System.exit(1);}

        return smallestDistanceVertex;
    }

    private static boolean isTargetVertexOK(Map<String,CustomVertex> map,String targetVertex){
        return map.get(targetVertex).isOK();
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
