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
        Graph graph = GKAFileManager.importGraph("BIG.gka");
        System.out.println("---- BIG.gka Kruskal-----");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Kruskal.kruskalAlgorithm(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

//        System.out.println(graph);
//        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
//        assertTrue(true);
    }

    @Test
    public void kruskalAlgorithm_graph4_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph4_a3.gka");
        System.out.println("---- graph4_a3.gka Kruskal-----");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Kruskal.kruskalAlgorithm(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

//        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }

    @Test
    public void kruskalAlgorithm_graph5_a3_test() {
        Graph graph = GKAFileManager.importGraph("graph5_a3.gka");
        System.out.println("---- graph5_a3.gka Kruskal-----");

        final Long nanosecond = System.nanoTime();
        Graph minimalGraph = Kruskal.kruskalAlgorithm(graph);
        final Long usedNanosecond = System.nanoTime() - nanosecond;
        System.out.println("Nanosekunden: " + usedNanosecond + " Millisekunden: " + usedNanosecond / 1000000);

//        System.out.println(minimalGraph);

        assertTrue(minimalGraph.edgeSet().size() == minimalGraph.vertexSet().size() - 1);
    }
}
