package aufgabe3;

import aufgabe1.GKAFileManager;
import org.jgrapht.Graph;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Qiqi
 * Date: 09.12.12
 * Time: 02:02
 */
public class GraphBuilderTest {

    @Test
    public void generate_test() {
        Graph generatedGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
                numberOfEdges = 8000;

        double maxEdgeWeight = 45.3,
                minEdgeWeight = 9.23;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices, numberOfEdges).setEdgeWeightRange(maxEdgeWeight, minEdgeWeight).generate();

        boolean equalVertices = (generatedGraph.vertexSet().size() == numberOfVertices),
                equalEdges = (generatedGraph.edgeSet().size() >= numberOfEdges);

        GKAFileManager.exportGraph(generatedGraph, "BIG.gka");

        assertTrue(equalVertices);
        assertTrue(equalEdges);
    }
}
