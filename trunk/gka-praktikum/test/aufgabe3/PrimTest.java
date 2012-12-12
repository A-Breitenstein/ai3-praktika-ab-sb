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
    public void primAlgorithm_test() {
        Graph generatedGraph,
                minimalGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
                numberOfEdges = 8000;

        double maxEdgeWeight = 20000d,
                minEdgeWeight = 1d;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices, numberOfEdges).setEdgeWeightRange(maxEdgeWeight, minEdgeWeight).generate();

        final Long nanosecond = System.nanoTime();
        minimalGraph = Prim.primAlgorithm(generatedGraph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("NOFI -> Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);


        final Long nanosecondF = System.nanoTime();
        minimalGraph = Prim.primAlgorithmFiboHeap(generatedGraph);
        final Long usedNanosecondF = System.nanoTime() - nanosecondF;
        System.out.println("FIBO -> Nanosekunden: " + usedNanosecondF + " Millisekunden: " + usedNanosecondF / 1000000);

        System.out.println(generatedGraph);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == numberOfVertices - 1);
    }

    @Test
    public void primAlgorithmFiboHeap_test() {
        Graph generatedGraph,
                minimalGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
                numberOfEdges = 8000;

        double maxEdgeWeight = 20000d,
                minEdgeWeight = 1d;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices, numberOfEdges).setEdgeWeightRange(maxEdgeWeight, minEdgeWeight).generate();

        final Long nanosecond = System.nanoTime();
        minimalGraph = Prim.primAlgorithmFiboHeap(generatedGraph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

        System.out.println(generatedGraph);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == numberOfVertices - 1);
    }

    @Test
    public void primAlgorithm_graph4_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph4_a3.gka");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Prim.primAlgorithm(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }

    @Test
    public void primAlgorithm_graph5_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph5_a3.gka");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Prim.primAlgorithm(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;

        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }

    @Test
    public void primAlgorithmFiboHeap_graph4_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph4_a3.gka");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Prim.primAlgorithmFiboHeap(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;

        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }

    @Test
    public void primAlgorithmFiboHeap_graph5_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph5_a3.gka");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Prim.primAlgorithmFiboHeap(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;

        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }
}
