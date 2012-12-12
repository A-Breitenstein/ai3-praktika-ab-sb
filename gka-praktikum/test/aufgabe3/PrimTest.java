package aufgabe3;

import aufgabe1.GKAFileManager;
import org.jgrapht.Graph;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * User: Alex
 * Date: 09.12.12
 * Time: 12:49
 */
public class PrimTest {

    @Test
    public void primAlgorithm_test(){
        Graph generatedGraph,
              minimalGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
                numberOfEdges = 8000;

        double maxEdgeWeight = 20000d,
                minEdgeWeight = 1d;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices,numberOfEdges).setEdgeWeightRange(maxEdgeWeight,minEdgeWeight).generate();

        minimalGraph = Prim.primAlgorithm(generatedGraph);

        System.out.println(generatedGraph);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == numberOfVertices - 1);
    }

    @Test
    public void primAlgorithm_graph4_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph4_a3.gka");

        Graph minimalGraph = Prim.primAlgorithm(graph);

        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }

    @Test
    public void primAlgorithm_graph5_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph5_a3.gka");

        Graph minimalGraph = Prim.primAlgorithm(graph);

        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }
}
