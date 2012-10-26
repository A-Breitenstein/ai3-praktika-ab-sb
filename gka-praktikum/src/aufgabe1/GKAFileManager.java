package aufgabe1;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.10.12
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
public class GKAFileManager {

    final private static String GERICHTET = "#gerichtet";
    final private static String UNGERICHTET = "#ungerichtet";
    final private static String GEWICHTET = "#gewichtet";
    final private static String ATTRIBUTIERT = "#attributiert";

    private static List<List<String>> importGKAFile(String path){
        final InputStream inputStream;
        BufferedReader br;
        String line ="";
        List<List<String>> list = new ArrayList<List<String>>();
        try{
            inputStream = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String fileHead = br.readLine();

            if(fileHead.contains(UNGERICHTET) ||fileHead.contains(GERICHTET) ){

                List<String> graphPropertys = new ArrayList<String>(Arrays.asList(br.readLine().split(",")));
                if(graphPropertys.contains("gewichtet")){
                    graphPropertys.set(graphPropertys.indexOf("gewichtet"),GEWICHTET);
                }
                if(graphPropertys.contains(GEWICHTET) || graphPropertys.contains(ATTRIBUTIERT)){
                   graphPropertys.add(0, fileHead);
                   list.add(graphPropertys);
                }else{
                    list.add(Arrays.asList(fileHead));
                    list.add(graphPropertys);
                }

                while ((line = br.readLine()) != null) {
                    list.add(Arrays.asList(line.split(",")));
                }

            }else {
                br.close();
                throw new IOException("GKA-FileHead does not match");
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }
        return list;

    }

    public static Graph importGraph(String path){
        List<List<String>> graphList = importGKAFile(path);
        Graph graph = null;
        List<String> graphAttributes = graphList.get(0);

        if(graphAttributes.contains(UNGERICHTET)){
            if(graphAttributes.contains(GEWICHTET)){
                graph = new WeightedPseudograph(DefaultWeightedEdge.class);
                addVertexesAndEdges(graph, graphList);
            }else{
                graph = new Pseudograph(DefaultEdge.class);
                addVertexesAndEdges(graph, graphList);
            }
        }else if(graphAttributes.contains(GERICHTET)){
            if(graphAttributes.contains(GEWICHTET)){
                graph = new ListenableDirectedWeightedGraph(DefaultWeightedEdge.class);
                addVertexesAndEdgesWeighted(graph,graphList);
            }else{
                graph = new DirectedPseudograph(DefaultEdge.class);
                addVertexesAndEdges(graph, graphList);
            }
        }else{
            System.out.println("Fehler, Graph kann nicht importiert werden:");
            System.out.println(graphAttributes+ " at: "+path);
            System.exit(1);
        }
        return graph;
    }

    private static void addVertexesAndEdgesWeighted(Graph graph, List<List<String>> graphList){
        for (int i = 1; i < graphList.size(); i++) {
            String source = graphList.get(i).get(0),
                    target = graphList.get(i).get(1);
            double weight = Double.parseDouble(graphList.get(i).get(2));

            Graphs.addEdgeWithVertices(graph,source,target,weight);
        }
    }
    private static void addVertexesAndEdges(Graph graph, List<List<String>> graphList){
        for (int i = 1; i < graphList.size(); i++) {
            String source = graphList.get(i).get(0),
                    target = graphList.get(i).get(1);

           Graphs.addEdgeWithVertices(graph,source,target);
        }
    }
}
