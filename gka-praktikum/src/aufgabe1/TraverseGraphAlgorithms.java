package aufgabe1;

import org.jgrapht.Graph;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg628
 * Date: 27.10.12
 * Time: 02:05
 */
public class TraverseGraphAlgorithms {

    final static private int START_STEP = 0;
    private static long GRAPH_ACCESS_COUNTER = 0;

    //ACCESS_COUNTER-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static long getAccessCounter(String graphName){
        long tmp = GRAPH_ACCESS_COUNTER;
        GRAPH_ACCESS_COUNTER = 0;
        return  tmp;
    }
    public static void initializeAccessCounter(){
        GRAPH_ACCESS_COUNTER = 0;
    }
    //BREADTH FIRST-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static List<CustomVertex> breadthFirst(Graph graph, String startVertex, String targetVertex) {
        List<CustomVertex> customVertexList = new ArrayList<CustomVertex>();
        //startVertex
        List<String> startAdjacentList = getAdjacentVertexes(graph, startVertex);
        CustomVertex vStart = new CustomVertex(startVertex, START_STEP, startAdjacentList);
        //targetVertex
        CustomVertex vTarget = new CustomVertex(targetVertex);

        customVertexList.add(vStart);

        return _breadthFirst(graph, customVertexList, vTarget);
    }

    private static List<CustomVertex> _breadthFirst(Graph graph, List<CustomVertex> customVertexList, CustomVertex targetVertex) {

        List<CustomVertex> newVertexList = new ArrayList<CustomVertex>(customVertexList);

        for (CustomVertex vertex : customVertexList) {

            if(!(vertex.isChecked())){
                for (String s : vertex.adjacentStringVertexes) {
                    CustomVertex newCustomVertex = new CustomVertex(s);
                    if (!(newVertexList.contains(newCustomVertex))) {
                        newCustomVertex.step = vertex.step + 1;
                        newCustomVertex.adjacentStringVertexes = new ArrayList<String>(getAdjacentVertexes(graph, newCustomVertex.label));
                        newVertexList.add(newCustomVertex);
                    }
                }
            }
            vertex.checked();
        }

        if (!(newVertexList.contains(targetVertex))) {
            return _breadthFirst(graph, newVertexList, targetVertex);
        } else {
            return newVertexList;
        }
    }


    //DEPTH FIRST-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public static List<CustomVertex> depthFirst(Graph g, String startVertex, String targetVertex) {
        Map<String, CustomVertex> map = new HashMap<String, CustomVertex>();
        map.put(startVertex, new CustomVertex(startVertex, 0, getAdjacentVertexes(g, startVertex)));
        _depthFirst(g, map.get(startVertex), map, 1);


        List<CustomVertex>list =  new ArrayList<CustomVertex>();
        for(Object name : g.vertexSet()){
            list.add(map.get(name));
        }
        return  list;
    }


    private static void _depthFirst(Graph g, CustomVertex currentVertex, Map<String, CustomVertex> checkedMap, int steps) {
        //List<String> neighborsList = getAdjacentVertexes(g,currentVertex.label);

        for (String neighbor : currentVertex.adjacentStringVertexes) {
            // wenn die map den nachbar schon enthält, dann gucke ob der aktuelle steps wert
            // besser ist als der bereits eingetragene. Ist er besser,dann suche von da nach eventuell neuen wegen
            // andernfalls stoppe.
            if (checkedMap.containsKey(neighbor)) {
                if (checkedMap.get(neighbor).step > steps) {
                    checkedMap.get(neighbor).step = steps;
                    _depthFirst(g, checkedMap.get(neighbor), checkedMap, steps + 1);
                }

            } else {
                checkedMap.put(neighbor, new CustomVertex(neighbor, steps, getAdjacentVertexes(g, neighbor)));
                _depthFirst(g, checkedMap.get(neighbor), checkedMap, steps + 1);
            }

        }
    }

    //UTIL FUNCTIONS-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private static String reversePath(List<CustomVertex> path) {
        StringBuilder sb = new StringBuilder();
        ListIterator<CustomVertex> iter = path.listIterator(path.size());
        while (iter.hasPrevious()) {

            sb.append(iter.previous().label);
        }
        return sb.toString();
    }

    /**
     * Bestimmt alle möglichen Wege auf der gegebnen
     * VertexList --[ diese enthält alle Markierten Knoten
     * sowie ihre Entferung (step) von dem Start Knoten (der Knoten mit der Entferung 0)
     * und deren benachbarte Knoten ]--
     *
     * @param vertexList  "Tabelle" siehe oben
     * @param target    Ziel Vertex
     * @return   Liste von Strings mit den kürzesten möglichen Pfaden
     */
    public static List<String> findAllPaths(List<CustomVertex> vertexList, CustomVertex target) {
        List<String> validPaths = new ArrayList<String>();
        _findAllPaths(vertexList, target,new ArrayList<CustomVertex>(Arrays.asList(target)),validPaths);
       return  validPaths;
    }
    private static void _findAllPaths(List<CustomVertex> vertexList, CustomVertex target, List<CustomVertex> path,List<String> validPaths) {
        if (target.step == 0)
            validPaths.add(reversePath(path));
        else {
            for (CustomVertex vertex : vertexList) {
                if (target.step - 1 == vertex.step && vertex.adjacentStringVertexes.contains(target.label)) {
                    path.add(vertex);
                    _findAllPaths(vertexList, vertex, path,validPaths);
                    path.remove(vertex);
                }
            }

        }
    }


    /**
     * Liefert die Benachbarten Knoten von einem gegebnen Knoten
     *
     * @param g  ein befülltes Graph Element aus dem JGraphT Paket
     * @param sourceVertex  Der Knoten von dem die Nachbarn bestimmt werden sollen
     * @return  Liste von Benachbarten Knoten als Strings
     */
    private static List<String> getAdjacentVertexes(Graph g, String sourceVertex) {
        Set<String> neighborList = new HashSet<String>();
        for (Object targetVertex : g.vertexSet()) {
            GRAPH_ACCESS_COUNTER++;
            if (g.getEdge(sourceVertex, targetVertex) != null) {
                neighborList.add(targetVertex.toString());
            }
        }
        neighborList.remove(sourceVertex);

        return new ArrayList<String>(neighborList);
    }

    /**
     *  Bestimmt den kürsten Weg auf einer gegeben vertexList
     *
     * @param vertexList
     * @param target
     * @return  liefert den kürzesten Weg zurück
     */
    public static String getShortestPath(List<CustomVertex> vertexList,CustomVertex target){
        List<CustomVertex> path = new ArrayList<CustomVertex>(Arrays.asList(target));
        path = _getShortestPath(vertexList,target,path);
        return reversePath(path);

    }

    private static List<CustomVertex> _getShortestPath(List<CustomVertex> vertexList,CustomVertex target,List<CustomVertex> path){
        if(target.step == 0) return  path;
        else {
            for(CustomVertex vertex : vertexList){
                if(target.step-1 == vertex.step && vertex.adjacentStringVertexes.contains(target.label)){
                    path.add(vertex);
                    return _getShortestPath(vertexList, vertex, path);
                }
            }
            System.out.println("ERROR: there is no predecessor");
            System.out.println(vertexList);
            System.exit(1);
            return null;
        }
    }
    public static int getPathLength(List<String> paths){
        return paths.get(0).length()-1;
    }

}
