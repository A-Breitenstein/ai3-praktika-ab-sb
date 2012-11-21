package aufgabe2;

import aufgabe1.CustomVertex;
import aufgabe1.GKAFileManager;
import org.junit.Test;
import static aufgabe2.OptimalWay.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<String,CustomVertex> husumHamburg = OptimalWay.dijkstra(attriGraph, "Husum", "Hamburg");
        Map<String,CustomVertex> mindenHamburg = OptimalWay.dijkstra(attriGraph, "Minden", "Hamburg");
        Map<String,CustomVertex> muensterHamburg = OptimalWay.dijkstra(attriGraph, "Münster", "Hamburg");

//        for (CustomVertex vertex : husumHamburg.values()) {
//            System.out.println(vertex);
//        }
        System.out.println("--- Dijkstra ---");
        getPathInSout("Husum","Hamburg",husumHamburg);
        getPathInSout("Minden","Hamburg",mindenHamburg);
        getPathInSout("Münster","Hamburg",muensterHamburg);

    }

    @Test
    public void testaaaaaStern(){
        AttributedGraph attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph3_a2.gka");
        Map<String,CustomVertex> husumHamburg = OptimalWay.aaaaaaStern(attriGraph,"Husum","Hamburg");
        Map<String,CustomVertex> mindenHamburg = OptimalWay.aaaaaaStern(attriGraph, "Minden","Hamburg");
        Map<String,CustomVertex> muensterHamburg = OptimalWay.aaaaaaStern(attriGraph, "Münster","Hamburg");

//        for (CustomVertex vertex : husumHamburg.values()) {
//            System.out.println(vertex);
//        }
        System.out.println("--- aaaaStern ---");
        getPathInSout("Husum","Hamburg",husumHamburg);
        getPathInSout("Minden","Hamburg",mindenHamburg);
        Set<CustomVertex> list = getPathInSout("Münster","Hamburg",muensterHamburg);
        System.out.println(list);
    }

}
