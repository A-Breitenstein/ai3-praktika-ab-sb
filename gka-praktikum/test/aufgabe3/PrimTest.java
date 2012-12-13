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
        Graph graph = GKAFileManager.importGraph("BIG_MAMMAS_GRAPH.gka");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Prim.primAlgorithm(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

        System.out.println(graph);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }

    @Test
    public void primAlgorithmFiboHeap_test() {
        Graph graph = GKAFileManager.importGraph("BIG_MAMMAS_GRAPH.gka");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Prim.primAlgorithmFiboHeap(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

        System.out.println(graph);
        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
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
