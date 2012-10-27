package aufgabe1;

import org.jgrapht.Graph;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static aufgabe1.TraverseGraphAlgorithms.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.10.12
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    public static void main(String[] args) {
        Graph g1 = GKAFileManager.importGraph("graph1.gka");
        Graph g2 = GKAFileManager.importGraph("graph2.gka");
        Graph g3 = GKAFileManager.importGraph("graph_k5.gka");
        CustomVertex target;
        System.out.println("---------BREADTH FIRST---------------");

        List<CustomVertex> customVertexListG1 = breadthFirst(g1,"a","f");
        target = getVertex(customVertexListG1,"f");
        System.out.println("Graph1: gerichtet: "+customVertexListG1);
//        System.out.println("Graph1 Path: "+getShortestPath(customVertexListG1,target));
        _findAllPaths(customVertexListG1,target,new ArrayList<CustomVertex>(Arrays.asList(target)));



        List<CustomVertex> customVertexListG2 = breadthFirst(g2,"a","f");
        target = getVertex(customVertexListG2,"f");
        System.out.println("Graph2: ungerichtet: "+customVertexListG2);
//        System.out.println("Graph2 Path: "+getShortestPath(customVertexListG2,target));
        _findAllPaths(customVertexListG2,target,new ArrayList<CustomVertex>(Arrays.asList(target)));


        List<CustomVertex> customVertexListG3 = breadthFirst(g3,"a","d");
        target = getVertex(customVertexListG3,"d");
        System.out.println("Graph K5: ungerichtet: "+customVertexListG3);
//        System.out.println("Graph2 Path: "+getShortestPath(customVertexListG2,target));
        _findAllPaths(customVertexListG3,target,new ArrayList<CustomVertex>(Arrays.asList(target)));

        System.out.println("---------DEPTH FIRST---------------");

        System.out.println("Graph1:");
        depthFirst__2(g1,"a","f");
        System.out.println("Graph2:");
        depthFirst__2(g2,"a","f");
        System.out.println("Graph K5:");
        depthFirst__2(g3,"a","d");
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
