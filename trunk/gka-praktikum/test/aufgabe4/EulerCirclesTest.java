package aufgabe4;

import aufgabe1.GKAFileManager;
import aufgabe3.GraphBuilder;
import junit.framework.Assert;
import org.jgrapht.Graph;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.12.12
 * Time: 17:43
 */
public class EulerCirclesTest {
    // http://de.wikipedia.org/wiki/Algorithmus_von_Hierholzer
    // http://de.wikipedia.org/wiki/Eulerkreisproblem
    // http://www-i1.informatik.rwth-aachen.de/~algorithmus/algo9.php
    // der eulertour jgraph ist von da, es ist das Haus vom Nikolaus gefixed, d.h.
    // es ist eine Eulertour möglich dadurch das die beiden ungeraden Knoten mit einem
    // neuen knoten verbunden wurden
    @Test
    public void testFleury() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("eulertour.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury(eulertour);
        System.out.println("ElapsedTime Fleury: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == 11);

    }
    @Test
    public void testFleury_adjazenzmatrix() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("eulertour.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury_adjazenMatrix(eulertour);
        System.out.println("ElapsedTime Fleury Adjazenzmatrix: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == 11);

    }
    @Test
    public void testHierholzer() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("eulertour.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.hierholzer(eulertour);
        System.out.println("ElapsedTime Hierholzer: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(EulerCircles.hierholzer(eulertour).size() == 11);
    }

    // Warnung! Dieser Test dauert ewig ... liegt an 10000 Kanten ² und Tiefensuche  ...
    // im ungünstigsten fall wird pro Knoten und jede seiner ausgehende Kanten Tiefensuche ausgeführt
    @Test
    @Ignore
    public void testFleury_EULERBIG() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_BIG_v2.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury(eulertour);
        System.out.println("ElapsedTime Fleury EULERBIG: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);

    }
    // Warnung! Dieser Test dauert ewig ... liegt an 10000 Kanten ² und Tiefensuche  ...
    // im ungünstigsten fall wird pro Knoten und jede seiner ausgehende Kanten Tiefensuche ausgeführt
    @Test
    @Ignore
    public void testFleury_adjazenzmatrix_EULERBIG() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_BIG_v2.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury_adjazenMatrix(eulertour);
        System.out.println("ElapsedTime Fleury adjazenzmatrix EULERBIG: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);

    }

    @Test
    public void testHierholzer_EULERBIG() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_BIG_v2.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.hierholzer(eulertour);
        System.out.println("ElapsedTime Hierholzer EULERBIG: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);
    }
    @Test
    public void testHierholzer_EULER_MIDSIZE() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_MIDSIZE.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.hierholzer(eulertour);
        System.out.println("ElapsedTime Hierholzer EULER_MIDSIZE: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);
    }
    @Test
    public void testFleury_EULER_MIDSIZE() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_MIDSIZE.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury(eulertour);
        System.out.println("ElapsedTime Fleury EULER_MIDSIZE: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);

    }
    @Test
    public void testFleury_adjazenzmatrix_EULER_MIDSIZE() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_MIDSIZE.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury_adjazenMatrix(eulertour);
        System.out.println("ElapsedTime Fleury adjazenzmatrix EULER_MIDSIZE: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);

    }
    @Test
    public void testFleury_vs_test_EULER_MIDSIZE() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_MIDSIZE.gka");
        System.out.println("############## PATH EQUALS TEST ###############");
        System.out.println("######### fleury DFS vs fleury matrix #########");
        System.out.println("######### vertecies:"+eulertour.vertexSet().size()+" edges: "+eulertour.edgeSet().size()+" #############");
        long start = System.nanoTime();
        List<String> path_matrix = EulerCircles.fleury_adjazenMatrix(eulertour);
        System.out.println("ElapsedTime Fleury adjazenzmatrix EULER_MIDSIZE: " + String.format("%f", ((System.nanoTime() - start) / 1000000d)) + " ms");
        System.out.println(path_matrix.toString());
        Assert.assertTrue(path_matrix.size() == eulertour.edgeSet().size()+1);


        start = System.nanoTime();
        List<String> path_normal = EulerCircles.fleury(eulertour);
        System.out.println("ElapsedTime Fleury  EULER_MIDSIZE: " + String.format("%f", ((System.nanoTime() - start) / 1000000d)) + " ms");
        System.out.println(path_normal.toString());
        Assert.assertTrue(path_normal.size() == eulertour.edgeSet().size()+1);

        Assert.assertTrue(path_matrix.toString().equals(path_normal.toString()));

    }


}
