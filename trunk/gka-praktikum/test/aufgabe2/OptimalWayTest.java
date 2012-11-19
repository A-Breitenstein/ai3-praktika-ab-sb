package aufgabe2;

import aufgabe1.GKAFileManager;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 19.11.12
 * Time: 18:23
 */
public class OptimalWayTest {
    @Test
    public void testDijkstra() throws Exception {
        AttributedGraph attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph3_a2.gka");
        OptimalWay.dijkstra(attriGraph,"Hamburg","Kiel");
    }
}
