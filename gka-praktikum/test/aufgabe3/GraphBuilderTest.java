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


    @Test
    public void testCreateEULERBIG() throws Exception {
        Graph generatedGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
                numberOfEdges = 10000;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices, numberOfEdges).generateConnectedUndirectedGraphWithEvenVertexGrade();

        boolean equalVertices = (generatedGraph.vertexSet().size() >= numberOfVertices),
                equalEdges = (generatedGraph.edgeSet().size() >= numberOfEdges);

        GKAFileManager.exportGraph(generatedGraph, "EULER_BIG_v2.gka");
        System.out.println(generatedGraph.edgeSet().size());
        assertTrue(equalVertices);
        assertTrue(equalEdges);
    }
    @Test
    public void testCreateEULER_MIDSIZE() {
        Graph generatedGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 50,
                numberOfEdges = 625;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices, numberOfEdges).generateConnectedUndirectedGraphWithEvenVertexGrade();

        boolean equalVertices = (generatedGraph.vertexSet().size() >= numberOfVertices),
                equalEdges = (generatedGraph.edgeSet().size() >= numberOfEdges);

        GKAFileManager.exportGraph(generatedGraph, "EULER_MIDSIZE.gka");
        System.out.println(generatedGraph.edgeSet().size());
        assertTrue(equalVertices);
        assertTrue(equalEdges);
    }

    @Test
    public void testCreateEULER_mid2_and_reimport() {
        Graph generatedGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 30,
                numberOfEdges = 250;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices, numberOfEdges).generateConnectedUndirectedGraphWithEvenVertexGrade();


        GKAFileManager.exportGraph(generatedGraph, "EULER_mid2.gka");
        System.out.println(generatedGraph.edgeSet().size());
        Graph reimport = GKAFileManager.importGraph("EULER_mid2.gka");
        boolean equalVertices = (generatedGraph.vertexSet().size() == reimport.vertexSet().size()),
                equalEdges = (generatedGraph.edgeSet().size() == reimport.edgeSet().size());
        assertTrue(equalVertices);
        assertTrue(equalEdges);
    }
}
