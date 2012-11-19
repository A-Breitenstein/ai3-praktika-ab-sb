package aufgabe2;

import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 16.11.12
 * Time: 12:05
 */
public class AttributedGraphImpl implements AttributedGraph {
    private Graph graph;
    private Map<String,Integer> attributeMap;

    private AttributedGraphImpl(Graph graph, Map<String, Integer> attributeMap) {
        this.graph = graph;
        this.attributeMap = attributeMap;
    }

    public static AttributedGraphImpl create(Graph grap, Map<String, Integer> attributeMap) {
        return new AttributedGraphImpl(grap, attributeMap);
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ METHODS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public int getAttribute(String vertex){
        final Integer result = attributeMap.get(vertex);
        if(result != null)
            return result.intValue();
        else{
            System.out.println("Vertex: "+vertex+" doesn´t exists!");
            System.exit(1);
        }
        return 0;
    }
    public boolean isWeighted(){
        return graph instanceof WeightedGraph;
    }
    public boolean isDirected(){
        return !(graph instanceof UndirectedGraph);
    }

    @Override
    public String toString() {
        return "AttributedGraphImpl{" +
                "graph=" + graph +
                ", attributeMap=" + attributeMap +
                '}';
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ GETTER @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public Graph getGraph() {
        return graph;
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ DELEGATED INTERFACE METHODS @@@@@@@@@@@@@@@@@@@
    @Override
    public Set getAllEdges(Object sourceVertex, Object targetVertex) {
        return graph.getAllEdges(sourceVertex, targetVertex);
    }

    @Override
    public Object getEdge(Object sourceVertex, Object targetVertex) {
        return graph.getEdge(sourceVertex, targetVertex);
    }

    @Override
    public EdgeFactory getEdgeFactory() {
        return graph.getEdgeFactory();
    }

    @Override
    public Object addEdge(Object sourceVertex, Object targetVertex) {
        return graph.addEdge(sourceVertex, targetVertex);
    }

    @Override
    public boolean addEdge(Object sourceVertex, Object targetVertex, Object o) {
        return graph.addEdge(sourceVertex, targetVertex, o);
    }

    @Override
    public boolean addVertex(Object o) {
        return graph.addVertex(o);
    }

    @Override
    public boolean containsEdge(Object sourceVertex, Object targetVertex) {
        return graph.containsEdge(sourceVertex, targetVertex);
    }

    @Override
    public boolean containsEdge(Object o) {
        return graph.containsEdge(o);
    }

    @Override
    public boolean containsVertex(Object o) {
        return graph.containsVertex(o);
    }

    @Override
    public Set edgeSet() {
        return graph.edgeSet();
    }

    @Override
    public Set edgesOf(Object vertex) {
        return graph.edgesOf(vertex);
    }

    @Override
    public boolean removeAllEdges(Collection edges) {
        return graph.removeAllEdges(edges);
    }

    @Override
    public Set removeAllEdges(Object sourceVertex, Object targetVertex) {
        return graph.removeAllEdges(sourceVertex, targetVertex);
    }

    @Override
    public boolean removeAllVertices(Collection vertices) {
        return graph.removeAllVertices(vertices);
    }

    @Override
    public Object removeEdge(Object sourceVertex, Object targetVertex) {
        return graph.removeEdge(sourceVertex, targetVertex);
    }

    @Override
    public boolean removeEdge(Object o) {
        return graph.removeEdge(o);
    }

    @Override
    public boolean removeVertex(Object o) {
        return graph.removeVertex(o);
    }

    @Override
    public Set vertexSet() {
        return graph.vertexSet();
    }

    @Override
    public Object getEdgeSource(Object o) {
        return graph.getEdgeSource(o);
    }

    @Override
    public Object getEdgeTarget(Object o) {
        return graph.getEdgeTarget(o);
    }

    @Override
    public double getEdgeWeight(Object o) {
        return graph.getEdgeWeight(o);
    }
}
