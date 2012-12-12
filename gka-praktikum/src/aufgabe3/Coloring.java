package aufgabe3;

import aufgabe1.CustomVertex;
import aufgabe1.GKAFileManager;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 12.12.12
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class Coloring {
    public static void colorMe(JGraphModelAdapter graphModelAdapter, Graph minimalGeruest) {
        DefaultGraphCell cell;
        Map attrisVertex, attrisEdge;
        DefaultEdge edge;
        for (String vertex : (Set<String>) minimalGeruest.vertexSet()) {
            cell = graphModelAdapter.getVertexCell(vertex);
            attrisVertex = cell.getAttributes();

//            GraphConstants.setBackground(attrisVertex, new Color(63, 232, 93));


            Iterator<DefaultEdge> iter = graphModelAdapter.edges(cell.getChildren().get(0));
            while (iter.hasNext()) {
                edge = iter.next();
                String target = ((DefaultPort) edge.getTarget()).getParent().toString();
                String source = ((DefaultPort) edge.getSource()).getParent().toString();
                if (containsEdge(source, target, minimalGeruest)) {
                    attrisEdge = edge.getAttributes();
                    GraphConstants.setLineColor(attrisEdge, new Color(255, 39, 221));
                    GraphConstants.setLineWidth(attrisEdge, 5);
                }

            }

        }
    }

    private static boolean containsEdge(String source, String target, Graph minimalGeruest) {
        return (minimalGeruest.getEdge(source, target) != null || minimalGeruest.getEdge(target, source) != null);
    }

}

