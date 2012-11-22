package aufgabe2;

import aufgabe1.CustomVertex;
import aufgabe1.GKAFileManager;
import org.junit.Test;
import static aufgabe2.OptimalWay.*;

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
        System.out.println("\n");
        System.out.println("--- Dijkstra ---");
        AttributedGraph attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph3_a2.gka");
        Map<String,CustomVertex> husumHamburg = OptimalWay.dijkstra(attriGraph, "Husum", "Hamburg");
            System.out.println("dijkstra - Zugriffe auf dem Weg von Husum -> Hamburg: " + OptimalWay.dijkstraCounter);
        Map<String,CustomVertex> mindenHamburg = OptimalWay.dijkstra(attriGraph, "Minden", "Hamburg");
            System.out.println("dijkstra - Zugriffe auf dem Weg von Minden -> Hamburg: " + OptimalWay.dijkstraCounter);
        Map<String,CustomVertex> muensterHamburg = OptimalWay.dijkstra(attriGraph, "Münster", "Hamburg");
            System.out.println("dijkstra - Zugriffe auf dem Weg von Münster -> Hamburg: " + OptimalWay.dijkstraCounter);
//        for (CustomVertex vertex : husumHamburg.values()) {
//            System.out.println(vertex);
//        }

        getPathInSout("Husum","Hamburg",husumHamburg);
        getPathInSout("Minden","Hamburg",mindenHamburg);
        getPathInSout("Münster","Hamburg",muensterHamburg);

        System.out.println("\n");
        attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph_alex.gka");
        Map<String,CustomVertex> dublinHokkaido = OptimalWay.dijkstra(attriGraph, "Dublin", "Hokkaido");
        System.out.println("dijkstra - Zugriffe auf dem Weg von Dublin -> Hokkaido: " + OptimalWay.dijkstraCounter);
        getPathInSout("Dublin","Hokkaido",dublinHokkaido);
    }

    @Test
    public void testaaaaaStern(){
        System.out.println("\n");
        System.out.println("--- aaaaStern ---");
        AttributedGraph attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph3_a2.gka");
        Map<String,CustomVertex> husumHamburg = OptimalWay.aaaaaaStern(attriGraph,"Husum","Hamburg");
            System.out.println("aStern - Zugriffe auf dem Weg von Husum -> Hamburg: " + OptimalWay.aSternCounter);
        Map<String,CustomVertex> mindenHamburg = OptimalWay.aaaaaaStern(attriGraph, "Minden","Hamburg");
            System.out.println("aStern - Zugriffe auf dem Weg von Minden -> Hamburg: " + OptimalWay.aSternCounter);
        Map<String,CustomVertex> muensterHamburg = OptimalWay.aaaaaaStern(attriGraph, "Münster","Hamburg");
            System.out.println("aStern - Zugriffe auf dem Weg von Münster -> Hamburg: " + OptimalWay.aSternCounter);
//        for (CustomVertex vertex : husumHamburg.values()) {
//            System.out.println(vertex);
//        }

        getPathInSout("Husum","Hamburg",husumHamburg);
        getPathInSout("Minden","Hamburg",mindenHamburg);
        getPathInSout("Münster","Hamburg",muensterHamburg);

        System.out.println("\n");
        attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph_alex.gka");
        Map<String,CustomVertex> dublinHokkaido = OptimalWay.aaaaaaStern(attriGraph, "Dublin", "Hokkaido");
        System.out.println("aStern - Zugriffe auf dem Weg von Dublin -> Hokkaido: " + OptimalWay.aSternCounter);
        getPathInSout("Dublin","Hokkaido",dublinHokkaido);
    }

}
