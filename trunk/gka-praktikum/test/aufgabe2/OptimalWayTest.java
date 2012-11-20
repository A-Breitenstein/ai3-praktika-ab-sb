package aufgabe2;

import aufgabe1.CustomVertex;
import aufgabe1.GKAFileManager;
import org.junit.Test;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 19.11.12
 * Time: 18:23
 */
public class OptimalWayTest {
    @Test
    public void testDijkstra() throws Exception {
        String start = "Hamburg", target="Husum";
        AttributedGraph attriGraph = (AttributedGraph) GKAFileManager.importGraph("graph3_a2.gka");
        Map<String,CustomVertex> vertexMap = OptimalWay.dijkstra(attriGraph,start,target);

        for (CustomVertex vertex : vertexMap.values()) {
            System.out.println(vertex);
        }


        while(!start.equals(target)){
            System.out.println(target);
            target = vertexMap.get(target).getPredecessor();
            if(start.equals(target))
                System.out.println(target);
        }


    }
}
