package aufgabe1;

import org.jgrapht.Graph;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg628
 * Date: 27.10.12
 * Time: 02:05
 * To change this template use File | Settings | File Templates.
 */
public class TraverseGraphAlgorithms {

    final static private int START_STEP = 0;

    public static List<CustomVertex> breadthFirst(Graph graph, String startVertex, String targetVertex) {
        List<CustomVertex> customVertexList = new ArrayList<CustomVertex>();
        //startVertex
        List<String> startAdjacentList = getAdjacentVertexes(graph, startVertex);
        CustomVertex vStart = new CustomVertex(startVertex, START_STEP, startAdjacentList);
        //targetVertex
        List<String> targetAdjacentList = getAdjacentVertexes(graph, targetVertex);
        CustomVertex vTarget = new CustomVertex(targetVertex, -1, targetAdjacentList);

        customVertexList.add(vStart);

        return _breadthFirst(graph, customVertexList, vTarget);

    }

    private static List<CustomVertex> _breadthFirst(Graph graph, List<CustomVertex> customVertexList, CustomVertex targetVertex) {

        List<CustomVertex> newVertexList = new ArrayList<CustomVertex>(customVertexList);

        for (CustomVertex vertex : customVertexList) {
            for (String s : vertex.adjacentStringVertexes) {
                CustomVertex newCustomVertex = new CustomVertex(s);
                if (!(newVertexList.contains(newCustomVertex))) {
                    newCustomVertex.step = vertex.step + 1;
                    newCustomVertex.adjacentStringVertexes = new ArrayList<String>(getAdjacentVertexes(graph, newCustomVertex.label));
                    newVertexList.add(newCustomVertex);
                }
            }
        }

        if (!(newVertexList.contains(targetVertex))) {
            return _breadthFirst(graph, newVertexList, targetVertex);
        } else {
            return newVertexList;
        }
    }

    @Deprecated
    private static List<String> getAdjacentVertexes_OLD(Graph g, String sourceVertex) {
        List<String> targetList = new ArrayList<String>();
        String s, t;
        for (Object edge : g.edgeSet()) {
            s = g.getEdgeSource(edge).toString();
            t = g.getEdgeTarget(edge).toString();
            if (sourceVertex.equals(s) && !(targetList.contains(t))) {
                targetList.add(t);
            }
        }
        return targetList;
    }
    private  static  List<String> getAdjacentVertexes(Graph g,String sourceVertex){
        Set<String> neighborList = new HashSet<String>();
        for(Object targetVertex : g.vertexSet()){
            if(g.getEdge(sourceVertex,targetVertex) != null){
                neighborList.add(targetVertex.toString());
            }
        }
        neighborList.remove(sourceVertex);

        return new ArrayList<String>(neighborList);
    }
    // first implementation off depthFirst, cant work with CustomVertexs
    @Deprecated
    public static void depthFirst(Graph g,String startVertex,String targetVertex){
            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put(startVertex,0);

            _depthFirst(g, startVertex, map, 1);
        System.out.println(map);
    }
    @Deprecated
    public static void _depthFirst(Graph g, String currentVertex, Map<String, Integer> checkedMap, int steps){
        List<String> neighborsList = getAdjacentVertexes(g,currentVertex);
        for(String neighbor : neighborsList){
            // wenn die map den nachbar schon enthält, dann gucke ob der aktuelle steps wert
            // besser ist als der bereits eingetragene. Ist er besser,dann suche von da nach eventuell neuen wegen
            // andernfalls stoppe.
            if(checkedMap.containsKey(neighbor)){
                if(checkedMap.get(neighbor)> steps){
                    checkedMap.put(neighbor,steps);
                    _depthFirst(g, neighbor, checkedMap, steps + 1);
                }

            }else{
                checkedMap.put(neighbor,steps);
            _depthFirst(g, neighbor, checkedMap, steps + 1);
            }

        }
    }
    // this one works with Customvertexes
    private static void __depthFirst(Graph g,CustomVertex currentVertex,Map<String,CustomVertex> checkedMap,int steps){
        //List<String> neighborsList = getAdjacentVertexes(g,currentVertex.label);

        for(String neighbor : currentVertex.adjacentStringVertexes){
            // wenn die map den nachbar schon enthält, dann gucke ob der aktuelle steps wert
            // besser ist als der bereits eingetragene. Ist er besser,dann suche von da nach eventuell neuen wegen
            // andernfalls stoppe.
            if(checkedMap.containsKey(neighbor)){
                if(checkedMap.get(neighbor).step > steps){
                    checkedMap.get(neighbor).step = steps;
                    __depthFirst(g,checkedMap.get(neighbor),checkedMap,steps+1);
                }

            }else{
                checkedMap.put(neighbor,new CustomVertex(neighbor,steps,getAdjacentVertexes(g,neighbor)));
                __depthFirst(g, checkedMap.get(neighbor), checkedMap, steps + 1);
            }

        }
    }

    public static void depthFirst__2(Graph g,String startVertex,String targetVertex){
        Map<String,CustomVertex> map = new HashMap<String, CustomVertex>();
        map.put(startVertex,new CustomVertex(startVertex,0,getAdjacentVertexes(g,startVertex)));

        __depthFirst(g, map.get(startVertex), map, 1);
        System.out.println(map.values());
        System.out.println(getShortestPath(new ArrayList<CustomVertex>(map.values()), map.get(targetVertex)));
    }
    public static String getShortestPath(List<CustomVertex> vertexList,CustomVertex target){
        List<CustomVertex> path = new ArrayList<CustomVertex>(Arrays.asList(target));
        StringBuilder sb = new StringBuilder();
        path = _getShortestPath(vertexList,target,path);
        ListIterator<CustomVertex> iter = path.listIterator(path.size());
        while(iter.hasPrevious()){

            sb.append(iter.previous().label);
        }
        return sb.toString();
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
}
