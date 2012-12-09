package aufgabe3;

import org.jgrapht.Graph;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Qiqi
 * Date: 09.12.12
 * Time: 02:02
 * To change this template use File | Settings | File Templates.
 */
public class GraphBuilderTest {

    @Test
    public void generate_test() {
        Graph generatedGraph;
        GraphBuilder gBuilder = GraphBuilder.create();

        int numberOfVertices = 200,
            numberOfEdges = 800;

        double maxEdgeWeight = 45.3,
               minEdgeWeight = 9.23;

        generatedGraph = gBuilder.setGraphStructure(numberOfVertices,numberOfEdges).setEdgeWeightRange(maxEdgeWeight,minEdgeWeight).generate();

        boolean equalVertices = (generatedGraph.vertexSet().size() == numberOfVertices),
                equalEdges = (generatedGraph.edgeSet().size() == numberOfEdges);

        assertTrue(equalVertices);
        assertTrue(equalEdges);
    }
}
