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
    // der eulertour graph ist von da, es ist das Haus vom Nikolaus gefixed, d.h.
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
    public void testFleury_EULERBIG() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_BIG.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.fleury(eulertour);
        System.out.println("ElapsedTime Fleury: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);

    }
    @Test
    public void testHierholzer_EULERBIG() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("EULER_BIG.gka");
        long start = System.nanoTime();
        List<String> path = EulerCircles.hierholzer(eulertour);
        System.out.println("ElapsedTime Hierholzer: "+String.format("%f",((System.nanoTime()-start)/1000000d))+" ms");
        System.out.println(path);
        Assert.assertTrue(path.size() == eulertour.edgeSet().size()+1);
    }




}
