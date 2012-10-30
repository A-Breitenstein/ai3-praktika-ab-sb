package aufgabe1;

import org.jgrapht.Graph;
import java.util.*;
import static aufgabe1.TraverseGraphAlgorithms.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.10.12
 * Time: 15:21
 */
public class Main {


    public static void main(String[] args) {
        Graph g1 = GKAFileManager.importGraph("graph1.gka");
        Graph g2 = GKAFileManager.importGraph("graph2.gka");
        Graph g3 = GKAFileManager.importGraph("graph_k5.gka");
        CustomVertex target;
        System.out.println("---------BREADTH FIRST---------------");

        List<CustomVertex> customVertexListG1 = breadthFirst(g1,"a","f");
        getAccessCounter("Graph1");
        target = getVertex(customVertexListG1,"f");
        System.out.println("Graph1: gerichtet: "+customVertexListG1);
//        System.out.println("Graph1 Path: "+getShortestPath(customVertexListG1,target));
        System.out.println(findAllPaths(customVertexListG1, target));



        List<CustomVertex> customVertexListG2 = breadthFirst(g2,"a","f");
        getAccessCounter("Graph2");
        target = getVertex(customVertexListG2,"f");
        System.out.println("Graph2: ungerichtet: "+customVertexListG2);
//        System.out.println("Graph2 Path: "+getShortestPath(customVertexListG2,target));
        System.out.println(findAllPaths(customVertexListG2, target));


        List<CustomVertex> customVertexListG3 = breadthFirst(g3,"a","d");
        getAccessCounter("Graph K5");
        target = getVertex(customVertexListG3,"d");
        System.out.println("Graph K5: ungerichtet: "+customVertexListG3);
//        System.out.println("Graph2 Path: "+getShortestPath(customVertexListG2,target));
        System.out.println(findAllPaths(customVertexListG3, target));

        System.out.println("---------DEPTH FIRST---------------");

        System.out.println("Graph1:");
        List<CustomVertex> depthFirstGraph1 = depthFirst(g1, "a", "f");
        System.out.println(findAllPaths(depthFirstGraph1, CustomVertex.getVertex(depthFirstGraph1, "f")));
        getAccessCounter("Graph1");
        System.out.println("Graph2:");
        depthFirst(g2, "a", "f");
        getAccessCounter("Graph2");
        System.out.println("Graph K5:");
        depthFirst(g3, "a", "d");
        getAccessCounter("Graph K5");
    }
    public static CustomVertex getVertex(List<CustomVertex> list,String name){
        for(CustomVertex vertex : list){
            if(vertex.label.equals(name)){
                return vertex;
            }
        }
        return  null;
    }


}
