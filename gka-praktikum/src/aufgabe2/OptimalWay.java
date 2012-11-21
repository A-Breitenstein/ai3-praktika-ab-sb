package aufgabe2;

import aufgabe1.CustomVertex;
import org.jgraph.graph.*;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

/**
 * Date: 18.11.12
 * Time: 23:30
 */
public class OptimalWay {

    final static String EMPTY = "";
    final static int OMEGA = Integer.MAX_VALUE;
    static String smallestVertex = "";

    public static Map<String,CustomVertex> dijkstra(Graph graph, String sourceVertex, String targetVertex){
        Set<String> vertexSet = graph.vertexSet();
        Map<String,CustomVertex> customVertexMap = new HashMap<String, CustomVertex>();
        double distance;
        for (String s : vertexSet)
            customVertexMap.put(s,new CustomVertex(OMEGA,getAdjacentVertexes(graph,s),s,false,EMPTY));

        //Init Start Vertex
        customVertexMap.get(sourceVertex).setDistance(0).setPredecessor(sourceVertex).setOK();
        for (String adjacentVertex : customVertexMap.get(sourceVertex).adjacentStringVertexes) {
            distance = graph.getEdgeWeight(graph.getEdge(sourceVertex, adjacentVertex));
            customVertexMap.get(adjacentVertex).setDistance(distance).setPredecessor(sourceVertex);
        }
        // solange suchen wie der TargetVertex nicht ok ist geht nicht, da es sein kann das man den TargetVertex
        // garnicht erreichen kann. Dann wird er niee ok hier muss noch ein workaround rein
        while(!isTargetVertexOK(customVertexMap,targetVertex)){
            for (String vertexName : customVertexMap.keySet()) {
                CustomVertex vertex = customVertexMap.get(vertexName);

                if(vertex.getDistance() < Integer.MAX_VALUE && !vertex.isOK())
                    _dijkstra(graph, customVertexMap, vertexName);
            }
            smallestVertex = getSmallestDistanceNeighbor(customVertexMap);
            _dijkstra(graph, customVertexMap, smallestVertex);
        }
        return customVertexMap;
    }

    private static void _dijkstra(Graph graph, Map<String,CustomVertex> vertexMap, String smallestVertex){
        CustomVertex vertex = vertexMap.get(smallestVertex);
        double distance, newDistance;

        for (String adjacentVertexName : vertex.adjacentStringVertexes) {
            CustomVertex adjacentVertex = vertexMap.get(adjacentVertexName);
            if(!adjacentVertex.isOK()){

                distance = graph.getEdgeWeight(graph.getEdge(vertex.label, adjacentVertexName));
                newDistance = vertex.getDistance()+distance;
                if(newDistance < adjacentVertex.getDistance())
                    adjacentVertex.setDistance(newDistance).setPredecessor(vertex.label);
            }
        }
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

        map.get(smallestDistanceVertex).setOK();

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


    public static Map<String,CustomVertex> aaaaaaStern(AttributedGraph graph,String sourceVertex,String targetVertex){
        Map<String,CustomVertex> customVertexMap = new HashMap<String, CustomVertex>();
        double distance;
        for (String vertex : (Set<String>) graph.vertexSet())
            customVertexMap.put(vertex,new CustomVertex(OMEGA,graph.getAttribute(vertex),OMEGA,getAdjacentVertexes(graph,vertex),vertex,false,EMPTY));

        Set<String> openList = new HashSet<String>();
        //Init Start Vertex
        openList.add(sourceVertex);
        customVertexMap.get(sourceVertex)
                .setDistance(0)
                .setPredecessor(sourceVertex)
                .setHeuristic(customVertexMap.get(sourceVertex).getAttribute())
                .setOK();

        CustomVertex currentVertex,neighborVertex;
        // solange wie die "openList" nicht leer ist und der TargetVertex nicht OK laufe
        while(!openList.isEmpty() && !isTargetVertexOK(customVertexMap,targetVertex)){
            // den vertex mit dem kleinesten heuristischen Schätzwert finden
            currentVertex = customVertexMap.get(findSmallestEstimatorInOpenList(openList, customVertexMap));
            // aus der Openliste entfernen und auf OK setzen
            openList.remove(currentVertex.label);
            currentVertex.setOK();

            // für alle Nachbarn des currentVertexes untersuchen...
            for (String neighborName :  currentVertex.adjacentStringVertexes) {

                neighborVertex = customVertexMap.get(neighborName);
                distance = currentVertex.getDistance()+graph.getEdgeWeight(graph.getEdge(currentVertex.label,neighborName));
                // falls die neue Distanz kleiner ist als die bereits eingetragene im nachbarVertex und
                // dieser noch nicht OK gesetzt ist tue folgendes...
                if(!neighborVertex.isOK() && neighborVertex.getDistance() > distance ){
                    neighborVertex
                            .setPredecessor(currentVertex.label)
                            .setDistance(distance)
                            .setHeuristic(neighborVertex.getAttribute()+distance);
                    openList.add(neighborName);
                }
            }
        }

        if(!customVertexMap.get(targetVertex).isOK()){
            System.out.println("es gibt keinen weg zum TargetVertex");
            System.exit(1); // <- :(
        }

      return  customVertexMap;

    }
    private static String findSmallestEstimatorInOpenList(Set<String> openList, Map<String, CustomVertex> vertexMap){
       double smallestHeuristic = OMEGA;
       String vertexName ="";
        CustomVertex vertex;
        for (String s : openList) {
            vertex = vertexMap.get(s);
            if(vertex.getHeuristic() < smallestHeuristic){
                smallestHeuristic = vertex.getHeuristic();
                vertexName = s;
            }
        }
        return vertexName;
    }



    public static Set<CustomVertex> getPathInSout(String start,String target,Map<String,CustomVertex> map){
        Set<CustomVertex> weg = new HashSet<CustomVertex>();
        System.out.println(" || ------ von "+start+" nach "+target+" ------ ||");
        while(!start.equals(target)){
            weg.add(map.get(target));
            System.out.println(target);
            target = map.get(target).getPredecessor();
            if(start.equals(target)){
                weg.add(map.get(target));
                System.out.println(target);
            }
        }
        return weg;
    }

    public static void colorMe(JGraphModelAdapter graphModelAdapter, Set<CustomVertex> vertexPathSet){
        DefaultGraphCell cell;
        Map attrisVertex,attrisEdge;
        DefaultEdge edge;
        for (CustomVertex vertex : vertexPathSet) {
            cell = graphModelAdapter.getVertexCell(vertex.label);
            attrisVertex = cell.getAttributes();
            GraphConstants.setBackground(attrisVertex, new Color(63, 232, 93));

            Iterator<DefaultEdge> iter = graphModelAdapter.edges(cell.getChildren().get(0));
            while(iter.hasNext()){
                edge = iter.next();
                String predecessor = vertex.getPredecessor();
                String current = vertex.label;
                String target = ((DefaultPort)edge.getTarget()).getParent().toString();
                String source = ((DefaultPort)edge.getSource()).getParent().toString();
                if((current.equals(source) && predecessor.equals(target))||(current.equals(target) && predecessor.equals(source))){
                   attrisEdge = edge.getAttributes();
                    GraphConstants.setLineColor(attrisEdge,new Color(220, 80, 205));
                    GraphConstants.setLineWidth(attrisEdge,20);
                }

            }

        }
    }
}
