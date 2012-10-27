package aufgabe1;

import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abg628
 * Date: 27.10.12
 * Time: 02:05
 * To change this template use File | Settings | File Templates.
 */
public class TraverseGraphAlgorithms {

    final static private int START_STEP = 0;

    public static List<CustomVertex> breadthFirst(Graph graph, String startVertex, String targetVertex){
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

    private static List<CustomVertex> _breadthFirst(Graph graph, List<CustomVertex> customVertexList, CustomVertex targetVertex){

        List<CustomVertex> newVertexList = new ArrayList<CustomVertex>(customVertexList);

        for(CustomVertex vertex: customVertexList){
            for(String s: vertex.adjacentStringVertexes){
                CustomVertex newCustomVertex = new CustomVertex(s);
                if(!(customVertexList.contains(newCustomVertex))){
                    newCustomVertex.step = vertex.step + 1;
                    newCustomVertex.adjacentStringVertexes = new ArrayList<String>(getAdjacentVertexes(graph,newCustomVertex.label));
                    newVertexList.add(newCustomVertex);
                }
            }
        }

        if(!(newVertexList.contains(targetVertex))){
            return _breadthFirst(graph, newVertexList, targetVertex);
        }else{
            return newVertexList;
        }

    }


    private static List<String> getAdjacentVertexes(Graph g, String sourceVertex) {
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

}
