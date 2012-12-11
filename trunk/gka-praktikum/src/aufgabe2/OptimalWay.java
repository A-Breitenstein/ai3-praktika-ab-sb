package aufgabe2;

import aufgabe1.CustomVertex;
import org.jgraph.graph.*;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Date: 18.11.12
 * Time: 23:30
 */
public class OptimalWay {

    final static String EMPTY = "";
    final static double OMEGA = Double.MAX_VALUE;
    static String smallestVertex = "";

    public static int dijkstraCounter = 0, aSternCounter = 0;

    public static Map<String,CustomVertex> dijkstra(Graph graph, String sourceVertex, String targetVertex){
        dijkstraCounter = 0;
        Set<String> vertexSet = graph.vertexSet();
        Map<String,CustomVertex> customVertexMap = new HashMap<String, CustomVertex>();
        Set<String> openList = new HashSet<String>();

        double distance;
        for (String s : vertexSet){
            customVertexMap.put(s,new CustomVertex(OMEGA,getAdjacentVertexes(graph,s),s,false,EMPTY));
            dijkstraCounter++;
        }

        //Init Start Vertex
        openList.add(sourceVertex);
        customVertexMap.get(sourceVertex).setDistance(0).setPredecessor(sourceVertex).setOK();
        dijkstraCounter++;
        for (String adjacentVertex : customVertexMap.get(sourceVertex).adjacentStringVertexes) {
            distance = graph.getEdgeWeight(graph.getEdge(sourceVertex, adjacentVertex));
            customVertexMap.get(adjacentVertex).setDistance(distance).setPredecessor(sourceVertex);
            dijkstraCounter+=3;
        }
        // solange suchen wie der TargetVertex nicht ok ist geht nicht, da es sein kann das man den TargetVertex
        // garnicht erreichen kann. Dann wird er niee ok hier muss noch ein workaround rein
        while(!openList.isEmpty() &&!isTargetVertexOK(customVertexMap,targetVertex)){
            for (String vertexName : customVertexMap.keySet()) {
                CustomVertex vertex = customVertexMap.get(vertexName);
                dijkstraCounter++;
                if(vertex.getDistance() < Integer.MAX_VALUE && !vertex.isOK())
                    _dijkstra(graph, customVertexMap, vertexName, openList);
            }
            smallestVertex = getSmallestDistanceNeighbor(customVertexMap);
            if(smallestVertex == null) return null;
            openList.remove(smallestVertex);
            _dijkstra(graph, customVertexMap, smallestVertex, openList);
        }

        if(!customVertexMap.get(targetVertex).isOK()){
            System.out.println("es gibt keinen weg zum TargetVertex");
            return null;
        }

        return customVertexMap;
    }

    private static void _dijkstra(Graph graph, Map<String,CustomVertex> vertexMap, String smallestVertex, Set<String> openList){
        CustomVertex vertex = vertexMap.get(smallestVertex);
        dijkstraCounter++;
        double distance, newDistance;

        for (String adjacentVertexName : vertex.adjacentStringVertexes) {
            CustomVertex adjacentVertex = vertexMap.get(adjacentVertexName);
            openList.add(adjacentVertex.label);
            dijkstraCounter++;
            if(!adjacentVertex.isOK()){

                distance = graph.getEdgeWeight(graph.getEdge(vertex.label, adjacentVertexName));
                dijkstraCounter++;
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

        if(smallestDistanceVertex.isEmpty())//{System.out.println("keinen kleinsten Vertex gefunden!"); System.exit(1);}
            System.out.println("......");

        if (map.containsKey(smallestDistanceVertex)) {

            map.get(smallestDistanceVertex).setOK();
        } else {
            return null;
        }

        return smallestDistanceVertex;
    }

    private static boolean isTargetVertexOK(Map<String,CustomVertex> map,String targetVertex){
        return map.get(targetVertex).isOK();
    }

    private static List<String> getAdjacentVertexes(Graph g, String sourceVertex) {
        Set<String> neighborList = new HashSet<String>();
        for (Object targetVertex : g.vertexSet()) {
            if (g.getEdge(sourceVertex, targetVertex) != null) {
                aSternCounter++;
                dijkstraCounter++;
                neighborList.add(targetVertex.toString());
            }
        }
        neighborList.remove(sourceVertex);

        return new ArrayList<String>(neighborList);
    }


    public static Map<String,CustomVertex> aaaaaaStern(AttributedGraph graph,String sourceVertex,String targetVertex){
        aSternCounter = 0;
        Map<String,CustomVertex> customVertexMap = new HashMap<String, CustomVertex>();
        double distance;
        for (String vertex : (Set<String>) graph.vertexSet()){
            customVertexMap.put(vertex,new CustomVertex(OMEGA,graph.getAttribute(vertex),OMEGA,getAdjacentVertexes(graph,vertex),vertex,false,EMPTY));
            aSternCounter++;
        }
        Set<String> openList = new HashSet<String>();
        //Init Start Vertex
        openList.add(sourceVertex);
        aSternCounter++;
        customVertexMap.get(sourceVertex)
                .setDistance(0)
                .setPredecessor(sourceVertex)
                .setHeuristic(customVertexMap.get(sourceVertex).getAttribute())
                .setOK();
        aSternCounter+=2;

        CustomVertex currentVertex,neighborVertex;
        // solange wie die "openList" nicht leer ist und der TargetVertex nicht OK laufe
        while(!openList.isEmpty() && !isTargetVertexOK(customVertexMap,targetVertex)){
            aSternCounter++;
            // den vertex mit dem kleinesten heuristischen Schätzwert finden
            currentVertex = customVertexMap.get(findSmallestEstimatorInOpenList(openList, customVertexMap));
            aSternCounter++;
            // aus der Openliste entfernen und auf OK setzen
            openList.remove(currentVertex.label);
            currentVertex.setOK();

            // für alle Nachbarn des currentVertexes untersuchen...
            for (String neighborName :  currentVertex.adjacentStringVertexes) {
                aSternCounter++;
                neighborVertex = customVertexMap.get(neighborName);
                aSternCounter++;
                distance = currentVertex.getDistance()+graph.getEdgeWeight(graph.getEdge(currentVertex.label,neighborName));
                aSternCounter+=4;
                // falls die neue Distanz kleiner ist als die bereits eingetragene im nachbarVertex und
                // dieser noch nicht OK gesetzt ist tue folgendes...
                if(!neighborVertex.isOK() && neighborVertex.getDistance() > distance ){
                    aSternCounter++;
                    neighborVertex
                            .setPredecessor(currentVertex.label)
                            .setDistance(distance)
                            .setHeuristic(neighborVertex.getAttribute()+distance);
                    openList.add(neighborName);
                    aSternCounter+=2;
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
        String ziel = target;
        Set<CustomVertex> weg = new HashSet<CustomVertex>();
        System.out.println(" || ------ von "+start+" nach "+target+" ------ ||");
        List<String> strings = new ArrayList<String>();
        while(!start.equals(target)){
            weg.add(map.get(target));
            strings.add(0,target);
//            System.out.print(target);
//            System.out.print(" <- ");
            target = map.get(target).getPredecessor();
            if(start.equals(target)){
                weg.add(map.get(target));
                strings.add(0,target);
//                System.out.println(target);
            }
        }
        for (int i = 0; i < strings.size(); i++) {
            System.out.print(strings.get(i));
            if(i < strings.size()-1)
                System.out.print(" -> ");
        }
        System.out.println(": distance = " + map.get(ziel).getDistance());
        return weg;
    }

    public static void colorMe(JGraphModelAdapter graphModelAdapter, Set<CustomVertex> vertexPathSet, String startVertex, String targetVertex){
        DefaultGraphCell cell;
        Map attrisVertex,attrisEdge;
        DefaultEdge edge;
        for (CustomVertex vertex : vertexPathSet) {
            cell = graphModelAdapter.getVertexCell(vertex.label);
            attrisVertex = cell.getAttributes();
            if(vertex.label.equals(startVertex)){
                GraphConstants.setBackground(attrisVertex, new Color(72, 128, 232));
            }else if(vertex.label.equals(targetVertex)){
                GraphConstants.setBackground(attrisVertex, new Color(232, 60, 71));
            }else {
                GraphConstants.setBackground(attrisVertex, new Color(63, 232, 93));
            }

            Iterator<DefaultEdge> iter = graphModelAdapter.edges(cell.getChildren().get(0));
            while(iter.hasNext()){
                edge = iter.next();
                String predecessor = vertex.getPredecessor();
                String current = vertex.label;
                String target = ((DefaultPort)edge.getTarget()).getParent().toString();
                String source = ((DefaultPort)edge.getSource()).getParent().toString();
                if((current.equals(source) && predecessor.equals(target))||(current.equals(target) && predecessor.equals(source))){
                   attrisEdge = edge.getAttributes();
                    GraphConstants.setLineColor(attrisEdge,new Color(255, 39, 221));
                    GraphConstants.setLineWidth(attrisEdge,5);
                }

            }

        }
    }
}
