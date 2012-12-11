package aufgabe3;

import aufgabe1.GKAFileManager;
import org.jgrapht.Graph;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * User: Qiqi
 * Date: 09.12.12
 * Time: 22:56
 */
public class KruskalTest {

    @Test
    public void testKruskalAlgorithm() throws Exception {
        Graph generatedGraph,
                minimalGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
                numberOfEdges = 8000;

        double maxEdgeWeight = 20000d,
                minEdgeWeight = 1d;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices,numberOfEdges).setEdgeWeightRange(maxEdgeWeight,minEdgeWeight).generate();

        minimalGraph = Kruskal.kruskalAlgorithm(generatedGraph);

        System.out.println(generatedGraph);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == numberOfVertices - 1);
    }

    @Test
    public void kruskalAlgorithm_graph4_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph4_a3.gka");

        Graph minimalGraph = Kruskal.kruskalAlgorithm(graph);

        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }
}
