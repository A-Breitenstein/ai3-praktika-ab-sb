package aufgabe4;

import aufgabe1.GKAFileManager;
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
}
