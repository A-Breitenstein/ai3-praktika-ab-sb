package aufgabe1;

import aufgabe2.AttributedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.10.12
 * Time: 20:06
 */
public class GKAFileManager {

    final private static String GERICHTET = "#gerichtet";
    final private static String UNGERICHTET = "#ungerichtet";
    final private static String GEWICHTET = "#gewichtet";
    final private static String ATTRIBUTIERT = "#attributiert";

    /**
     * Führt IO Operationen auf dem gegebenen GKA file aus
     * und liefert die Informationen als Liste von Listen mit Strings
     *  <p>
     * Example GKA File:
     * #gerichtet
     * #gewichtet
     * Hamburg,Bremen,123
     * Bremen,Hamburg,123
     * Hamburg,Berlin,289
     * </p>
     * =>
     * <p>
     *  List<List<String>>
     * [ [#gerichtet,#gewichtet],
     *  [Hamburg,Bremen,123],
     *  [Bremen,Hamburg,123],
     *  [Hamburg,Berlin,289] ]
     * </p>
     * @param path  FilePath
     * @return    List of List of Strings
     */
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

    /**
     * Importiert einen Graphen aus dem GKA Datei Format nach JGraphT Graph
     *
     * @param path Pfad wo sich die GKA Datei befindet
     * @return  JGraphT Graph
     */
    public static Graph importGraph(String path){
        List<List<String>> graphList = importGKAFile(path);
        Graph graph = null;
        List<String> graphAttributes = graphList.get(0);

        if(graphAttributes.contains(UNGERICHTET)){
            if(graphAttributes.contains(GEWICHTET)){
                graph = new WeightedPseudograph(DefaultWeightedEdge.class);
                addVertexesAndEdgesWeighted(graph, graphList);

                if(graphAttributes.contains(ATTRIBUTIERT))
                    graph = AttributedGraph.create(graph,createAttributeMap(graphList));

            }else{
                graph = new Pseudograph(DefaultEdge.class);
                addVertexesAndEdges(graph, graphList);
                if(graphAttributes.contains(ATTRIBUTIERT))
                    graph = AttributedGraph.create(graph,createAttributeMap(graphList));


            }
        }else if(graphAttributes.contains(GERICHTET)){
            if(graphAttributes.contains(GEWICHTET)){
                graph = new ListenableDirectedWeightedGraph(DefaultWeightedEdge.class);
                addVertexesAndEdgesWeighted(graph,graphList);

                if(graphAttributes.contains(ATTRIBUTIERT))
                    graph = AttributedGraph.create(graph,createAttributeMap(graphList));
            }else{
                graph = new DirectedPseudograph(DefaultEdge.class);
                addVertexesAndEdges(graph, graphList);

                if(graphAttributes.contains(ATTRIBUTIERT))
                    graph = AttributedGraph.create(graph,createAttributeMap(graphList));
            }
        }else{
            System.out.println("Fehler, Graph kann nicht importiert werden:");
            System.out.println(graphAttributes+ " at: "+path);
            System.exit(1);
        }
        return graph;
    }
    /**
     * Fügt in einen gegebenen Graphen die Knoten,Kanten und Gewichte ein
     *
     * @param graph  der Graph in den die Knoten und Kanten eingefügt werden sollen
     * @param graphList  die Knoten,Kanten und Gewicht Informationen aus dem GKA file
     */
    private static void addVertexesAndEdgesWeighted(Graph graph, List<List<String>> graphList){
        //Fix für attributierte Graphen
        if((graphList.get(1).size()==3)){
            for (int i = 1; i < graphList.size(); i++) {
                String source = graphList.get(i).get(0),
                        target = graphList.get(i).get(1);
                double weight = Double.parseDouble(graphList.get(i).get(2));

                Graphs.addEdgeWithVertices(graph,source,target,weight);
            }

        }else if((graphList.get(1).size()==5)){
            for (int i = 1; i < graphList.size(); i++) {
                String source = graphList.get(i).get(0),
                        target = graphList.get(i).get(2);
                double weight = Double.parseDouble(graphList.get(i).get(4));

                Graphs.addEdgeWithVertices(graph,source,target,weight);
            }
        }
    }

    private static Map<String,Integer> createAttributeMap(List<List<String>> graphList){
        Map<String,Integer> result = new HashMap<String, Integer>();
        List<List<String>> graphList2 = graphList.subList(1, graphList.size() - 1);
       for (List<String> s : graphList2) {
            result.put(s.get(0),Integer.valueOf(s.get(1)));
            result.put(s.get(2),Integer.valueOf(s.get(3)));
        }

        return result;
    }

    public static void main(String[] args) {
        Graph g = importGraph("graph3_a2.gka");
        System.out.println(g);
    }
    /**
     * Fügt in einen gegebenen Graphen die Knoten und Kanten ein
     *
     * @param graph  der Graph in den die Knoten und Kanten eingefügt werden sollen
     * @param graphList  die Knoten und Kanten informationen aus dem GKA file
     */
    private static void addVertexesAndEdges(Graph graph, List<List<String>> graphList){
        if((graphList.get(1).size()==4)){
            for (int i = 1; i < graphList.size(); i++) {
                String source = graphList.get(i).get(0),
                        target = graphList.get(i).get(2);

                Graphs.addEdgeWithVertices(graph,source,target);
            }
        }else{
            for (int i = 1; i < graphList.size(); i++) {
                String source = graphList.get(i).get(0),
                        target = graphList.get(i).get(1);

                Graphs.addEdgeWithVertices(graph,source,target);
            }
        }
    }

    /**
     *  Exportiert einen gegeben Graphen in das gka file Format
     *
     * @param graph ein Graph der das Graph <JGraphT> interface implementiert
     * @param path  in welches Verzeichnis exportiert werden soll
     */
   public static void exportGraph(Graph graph,String path){
       try{
            File file = new File(path);
           FileWriter fw = new FileWriter(file);
           if(graph instanceof Pseudograph){
               fw.write(UNGERICHTET+"\n");

               for(Object sourceVertex : graph.vertexSet()){
                   for (Object targetVertex : graph.vertexSet()) {
                       if (graph.getEdge(sourceVertex, targetVertex) != null) {
                           fw.write(sourceVertex+","+targetVertex+"\n");
                       }
                   }
               }
           }else if(graph instanceof WeightedPseudograph){
               fw.write(UNGERICHTET+"\n");
               fw.write(GEWICHTET+"\n");
               for(Object sourceVertex : graph.vertexSet()){
                   for (Object targetVertex : graph.vertexSet()) {
                         Object obj = graph.getEdge(sourceVertex,targetVertex);
                       if (obj != null) {
                           fw.write(sourceVertex+","+targetVertex+","+graph.getEdgeWeight(obj)+"\n");
                       }
                   }
               }
           }else if(graph instanceof ListenableDirectedWeightedGraph ){
               fw.write(GERICHTET+"\n");
               fw.write(GEWICHTET+"\n");
               for(Object sourceVertex : graph.vertexSet()){
                   for (Object targetVertex : graph.vertexSet()) {
                       Object obj = graph.getEdge(sourceVertex,targetVertex);
                       if (obj != null) {
                           fw.write(sourceVertex+","+targetVertex+","+graph.getEdgeWeight(obj)+"\n");
                       }
                   }
               }
           }else if(graph instanceof  DirectedPseudograph){
               fw.write(GERICHTET+"\n");
               for(Object sourceVertex : graph.vertexSet()){
                   for (Object targetVertex : graph.vertexSet()) {
                       if (graph.getEdge(sourceVertex, targetVertex) != null) {
                           fw.write(sourceVertex+","+targetVertex+"\n");
                       }
                   }
               }
           }else {
               System.out.println("Couldn´t export graph: "+graph.getClass().toString());
               fw.close();
               System.exit(1);
           }

           fw.close();
       }catch (IOException e){
           e.printStackTrace();
       }
   }
}
