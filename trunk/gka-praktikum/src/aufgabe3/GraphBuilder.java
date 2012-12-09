package aufgabe3;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;
import services.RandomManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 07.12.12
 * Time: 14:12
 */
public class GraphBuilder {

    private static final int ZERO = 0;
    private static String vertexName = "vertex";

    private String sourceVertex,
                   targetVertex;

    private double upperBound = 0d,
                   lowerBound = 0d;

    private int numberOfVertices = 0, numberOfEdges = 0;

    private GraphBuilder() {
    }

    public static GraphBuilder create() {
        return new GraphBuilder();
    }

    public GraphBuilder setGraphStructure(int numberOfVertices, int numberOfEdges){
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;

        return this;
    }

    public GraphBuilder setEdgeWeightRange(double upperBound, double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;

        return this;
    }

    public Graph generate() {

        if(this.numberOfVertices == 0)
            throw new IllegalArgumentException("The Number of Vertices is lower than 1, numberOfVertices: " + numberOfVertices);


        Graph generatedGraph = new WeightedPseudograph(DefaultWeightedEdge.class);
        List<String> generatedVerticesNames = new ArrayList<String>();
//        List<DefaultWeightedEdge> generatedEdges = new ArrayList<DefaultWeightedEdge>();

        double edgeWeight;

        sout_generateVertices(numberOfVertices);

        for (int i = 0; i < numberOfVertices; i++) {
            generatedVerticesNames.add(vertexName + i);
        }

        sout_addVertices(numberOfVertices);

        //Add all Vertices to Graph
        addAllVertices(generatedGraph,generatedVerticesNames);

        for (int i = 0; i < numberOfEdges; i++) {
            int
            sourceVertexIndex = RandomManager.intNumber(numberOfVertices,ZERO),
            targetVertexIndex = RandomManager.intNumber(numberOfVertices,ZERO);

            sourceVertex = generatedVerticesNames.get(sourceVertexIndex);
            targetVertex = generatedVerticesNames.get(targetVertexIndex);

            edgeWeight = RandomManager.doubleNumber(upperBound, lowerBound);

            Graphs.addEdgeWithVertices(generatedGraph,sourceVertex,targetVertex,edgeWeight);
        }

        sout_graphGenerated(generatedGraph);

        return generatedGraph;
    }

    private static void sout_graphGenerated(Graph graph) {
        System.out.println("graph was generated with " + graph.vertexSet().size() + " Vertices and " + graph.edgeSet().size() + " Edges");
    }

    private static void addAllVertices(Graph graph, Collection objects){

        for (Object object : objects) {
            graph.addVertex(object);
        }
    }

    private static void sout_addVertices(int numberOfVertices) {
        System.out.println(numberOfVertices + " vertices are being added to new Graph");
    }

    private static void sout_generateVertices(int numberOfVertices) {
        System.out.println(numberOfVertices + " vertices are being generated");
    }
}
