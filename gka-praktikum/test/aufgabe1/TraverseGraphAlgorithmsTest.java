package aufgabe1;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;
import static aufgabe1.TraverseGraphAlgorithms.*;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 28.10.12
 * Time: 09:59
 */
public class TraverseGraphAlgorithmsTest {

    Graph g1, g2, g3;
    String bf_g1String, bf_g2String, bf_g3String,
           df_g1String, df_g2String, df_g3String;

    @Before
    public void setUp() throws Exception {

        g1 = GKAFileManager.importGraph("graph1.gka");
        g2 = GKAFileManager.importGraph("graph2.gka");
        g3 = GKAFileManager.importGraph("graph_k5.gka");

        bf_g1String = "[[a, 0]:[b, c, k, h], [b, 1]:[j, k, i], [c, 1]:[d, a], [k, 1]:[g, d, c], [h, 1]:[b, c], [j, 2]:[b, c, a, k], [i, 2]:[c, a], [d, 2]:[e, a, k], [g, 2]:[d, e, b], [e, 3]:[f, b, c], [f, 4]:[g, c]]";
        bf_g2String = "[[a, 0]:[d, b, c, j, k, h, i], [d, 1]:[g, e, c, a, k], [b, 1]:[g, e, a, j, k, h, i], [c, 1]:[f, d, e, a, j, k, h, i], [j, 1]:[b, c, a, k], [k, 1]:[g, d, b, c, a, j], [h, 1]:[b, c, a], [i, 1]:[b, c, a], [g, 2]:[f, d, e, b, k], [e, 2]:[f, g, d, b, c], [f, 2]:[g, e, c]]";
        bf_g3String = "[[a, 0]:[d, e, b, c], [d, 1]:[e, b, c, a], [e, 1]:[d, b, c, a], [b, 1]:[d, e, c, a], [c, 1]:[d, e, b, a]]";

//        df_g1String = "[[f, 4]:[g, c], [g, 2]:[d, e, b], [d, 2]:[e, a, k], [e, 3]:[f, b, c], [b, 1]:[j, k, i], [c, 1]:[d, a], [a, 0]:[b, c, k, h], [j, 2]:[b, c, a, k], [k, 1]:[g, d, c], [h, 1]:[b, c], [i, 2]:[c, a]]";
//        df_g2String = "[[f, 2]:[g, e, c], [g, 2]:[f, d, e, b, k], [d, 1]:[g, e, c, a, k], [e, 2]:[f, g, d, b, c], [b, 1]:[g, e, a, j, k, h, i], [c, 1]:[f, d, e, a, j, k, h, i], [a, 0]:[d, b, c, j, k, h, i], [j, 1]:[b, c, a, k], [k, 1]:[g, d, b, c, a, j], [h, 1]:[b, c, a], [i, 1]:[b, c, a]]";
//        df_g3String = "[[d, 1]:[e, b, c, a], [e, 1]:[d, b, c, a], [b, 1]:[d, e, c, a], [c, 1]:[d, e, b, a], [a, 0]:[d, e, b, c]]";


/*
        List<CustomVertex> g1_vertexList = new ArrayList<CustomVertex>(
                Arrays.asList(
                    new CustomVertex("a",0,Arrays.asList("b","c", "k", "h")),
                    new CustomVertex("b",1,Arrays.asList("j","k","i")),
                    new CustomVertex("c",1,Arrays.asList("d","a")),
                    new CustomVertex("k",1,Arrays.asList("g", "d", "c")),
                    new CustomVertex("h",1,Arrays.asList("b", "c")),
                    new CustomVertex("j",2,Arrays.asList("b", "c", "a","k")),
                    new CustomVertex("i", 2,Arrays.asList("c", "a")),
                    new CustomVertex("d", 2,Arrays.asList("e", "a", "k")),
                    new CustomVertex("g", 2,Arrays.asList("d", "e", "b")),
                    new CustomVertex("e", 3,Arrays.asList("f", "b", "c")),
                    new CustomVertex("f", 4,Arrays.asList("g", "c"))
                )
        );
*/
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testBreadthFirst() throws Exception {
        assertEquals(breadthFirst(g1, "a", "f").toString(), bf_g1String);
        assertEquals(breadthFirst(g2, "a", "f").toString(), bf_g2String);
        assertEquals(breadthFirst(g3, "a", "d").toString(), bf_g3String);
    }

    @Test
    public void testDepthFirst() throws Exception {

        List<CustomVertex> listGraph1 = depthFirst(g1, "a", "f");
        List<CustomVertex> listGraph2 = depthFirst(g2,"a","f");
        List<CustomVertex> listGraph3 = depthFirst(g3,"a","d");

        List<String> pathsGraph1 = findAllPaths(listGraph1,CustomVertex.getVertex(listGraph1,"f"));
        List<String> pathsGraph2 = findAllPaths(listGraph2,CustomVertex.getVertex(listGraph2,"f"));
        List<String> pathsGraph3 = findAllPaths(listGraph3,CustomVertex.getVertex(listGraph3,"d"));

        assertEquals(pathsGraph1.toString(),"[acdef, akdef, akgef]");
        assertEquals(pathsGraph2.toString(),"[acf]");
        assertEquals(pathsGraph3.toString(),"[ad]");

        assertEquals(getPathLength(pathsGraph1),4);
        assertEquals(getPathLength(pathsGraph2),2);
        assertEquals(getPathLength(pathsGraph3),1);

    }
    @Test
    public void testGraphK5DepthFirst(){
        List<CustomVertex> listGraphK5 = TraverseGraphAlgorithms.depthFirst(g3,"a","d");
        System.out.println("Zugriffe auf vollstaendigen Graphen K5: "+getAccessCounter());
        List<String> vertexNames = new ArrayList<String>(g3.vertexSet());
        vertexNames.remove("a");
        int lengthFromVertexAtoTargetVertex;
        for (String targetVertex : vertexNames ){
            lengthFromVertexAtoTargetVertex = getPathLength(findAllPaths(listGraphK5,CustomVertex.getVertex(listGraphK5,targetVertex)));
            assertEquals(lengthFromVertexAtoTargetVertex,1);
        }
    }
    @Test
    public void testFindAllPaths() throws Exception {

    }
    @Test
    public void testExportGraph() throws Exception {
        GKAFileManager.exportGraph(g1,"g1Export");
        GKAFileManager.exportGraph(g2,"g2Export");
        GKAFileManager.exportGraph(g3,"g3Export");
    }
}
