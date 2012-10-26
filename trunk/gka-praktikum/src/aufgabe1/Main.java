package aufgabe1;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.event.TraversalListenerAdapter;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import sun.misc.Regexp;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.10.12
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void qwe(String[] args) {

        Graph g = GKAFileManager.importGraph("Z:\\AI3\\GKA\\graph1.gka");
        String startVertex = "a";
        String targetVertex = "f";
        String currentVertex = "a";
        int steps = 1;

        Map<String,Integer> bfsMap = new HashMap<String, Integer>();
        //initialize bfsMap
//        for(Object vertex:g.vertexSet()){
//                  bfsMap.put((String) vertex,-1);
//        }

        bfsMap.put(startVertex,0);

        Pattern p = Pattern.compile("\\((.*) : (.*)\\)");
        String s,t;
        List<String> targetList = new LinkedList<String>();


        while(bfsMap.get(targetVertex) == null){

            for(Object edge: g.edgeSet()){
                Matcher m = p.matcher(edge.toString());
                m.matches();
                s = m.group(1);
                t = m.group(2);
                System.out.println(g.getEdgeSource(edge));
                System.out.println(g.getEdgeTarget(edge));
                if(currentVertex.equals(s) && bfsMap.get(t) == null && !(targetList.contains(t))){
                    targetList.add(t);
                }


            }
            if(!(targetList.get(targetList.size()-1).equals("#")))
                targetList.add("#");

            System.out.println(targetList + " Steps: " + steps);

            for (int i = 0; i < targetList.size(); i++) {
                String target = targetList.get(i);
                if(!(target.equals("#"))){
                    if(bfsMap.get(target) == null && !(target.equals("#"))){
                        bfsMap.put(target,steps);
                    }
                }else{
                    i = targetList.size();
                }
            }

            System.out.println(bfsMap);
            System.out.println("");
            currentVertex = targetList.get(0);
            targetList = targetList.subList(1,targetList.size());
            if(currentVertex.equals("#")){
                steps++;
                currentVertex = targetList.get(0);
                targetList = targetList.subList(1, targetList.size());
            }
        }
        int  n = 1000;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n ; k++) {
                    for (int l = 0; l < n; l++) {
                        for (int m = 0; m < n; m++) {
                            sum++;
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }
    public static void main(String[] args){
        Graph g = GKAFileManager.importGraph("Z:\\AI3\\GKA\\graph1.gka");
        //System.out.println(getBenachbarteKnoten(g, "a"));
        String startVertex = "a";

        for(Object vertex : g.vertexSet()){
            System.out.println(getBenachbarteKnoten(g, vertex.toString()));
        }
        List<String> A_seine_nachbarn = getBenachbarteKnoten(g,"a");
        List<WestCoastCustomVertex> listeVertex = new ArrayList<WestCoastCustomVertex>();
        listeVertex.add(new WestCoastCustomVertex("a",0,A_seine_nachbarn));
        for(String vertex : A_seine_nachbarn){
            WestCoastCustomVertex customVertex = new WestCoastCustomVertex(vertex);
            if(!listeVertex.contains(customVertex)){
                customVertex.step= 0+1;
            }
        }



    }
//    public static WestCoastCustomVertex ratter(Graph g,){
//
//    }
    public static List<String> getBenachbarteKnoten(Graph g,String sourceVertex){
        List<String> targetList = new ArrayList<String>();
         String s,t;
        for(Object edge: g.edgeSet()){
            s = g.getEdgeSource(edge).toString();
            t = g.getEdgeTarget(edge).toString();
            if(sourceVertex.equals(s) && !(targetList.contains(t))){
                targetList.add(t);
            }


        }
        return targetList;
    }
}
