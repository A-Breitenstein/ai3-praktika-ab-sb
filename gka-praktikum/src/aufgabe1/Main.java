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
        List<CustomVertex> customVertexListG1 = breadthFirst(g1,"a","f");
        List<CustomVertex> customVertexListG2 = breadthFirst(g2,"a","f");
        System.out.println("Graph1: gerichtet: "+customVertexListG1);
        System.out.println("Graph2: ungerichtet: "+customVertexListG2);


        CustomVertex target = null;
        for(CustomVertex vertex : customVertexListG1){
            if(vertex.label.equals("f")){
                target = vertex;
            }
        }
        System.out.println("Graph1 Path: "+getShortestPath(customVertexListG1,target));


        for(CustomVertex vertex : customVertexListG2){
            if(vertex.label.equals("f")){
                target = vertex;
            }
        }
        System.out.println("Graph2 Path: "+getShortestPath(customVertexListG2,target));




        depthFirst(g1, "a", "f");
        depthFirst(g2, "a", "f");
        depthFirst__2(g1,"a","f");
        depthFirst__2(g2,"a","f");
    }



}
