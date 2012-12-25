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

    @Before
    public void setUp() throws Exception {
        g1 = GKAFileManager.importGraph("graph1.gka");
        g2 = GKAFileManager.importGraph("graph2.gka");
        g3 = GKAFileManager.importGraph("graph_k5.gka");
   }

    @Test
    public void testBreadthFirst() throws Exception {
        initializeAccessCounter();

        List<CustomVertex> listGraph1 = breadthFirst(g1, "a", "f");
        System.out.println("Zugriffe auf Graph1.gka mit breadthFirst: " + getAccessCounter(""));
        List<CustomVertex> listGraph2 = breadthFirst(g2, "a", "f");
        System.out.println("Zugriffe auf Graph2.gka mit breadthFirst: " + getAccessCounter(""));
        List<CustomVertex> listGraph3 = breadthFirst(g3, "a", "d");
        System.out.println("Zugriffe auf vollstaendigen Graphen K5 mit breadthFirst: " + getAccessCounter(""));

        List<String> pathsGraph1 = findAllPaths(listGraph1, CustomVertex.getVertex(listGraph1, "f"));
        List<String> pathsGraph2 = findAllPaths(listGraph2, CustomVertex.getVertex(listGraph2, "f"));
        List<String> pathsGraph3 = findAllPaths(listGraph3, CustomVertex.getVertex(listGraph3, "d"));

        assertEquals(pathsGraph1.toString(), "[acdef, akdef, akgef]");
        assertEquals(pathsGraph2.toString(), "[acf]");
        assertEquals(pathsGraph3.toString(), "[ad]");

        assertEquals(getPathLength(pathsGraph1), 4);
        assertEquals(getPathLength(pathsGraph2), 2);
        assertEquals(getPathLength(pathsGraph3), 1);

    }

    @Test
    public void testDepthFirst() throws Exception {
        initializeAccessCounter();
        long start = System.nanoTime();
        List<CustomVertex> listGraph1 = depthFirst(g1, "a", "f");
        System.out.println("ElapsedTime g1:" +(System.nanoTime() - start));
        System.out.println("Zugriffe auf Graph1.gka mit depthFirst: " + getAccessCounter(""));

        List<CustomVertex> listGraph2 = depthFirst(g2, "a", "f");
        System.out.println("Zugriffe auf Graph2.gka mit depthFirst: " + getAccessCounter(""));

        List<CustomVertex> listGraph3 = depthFirst(g3, "a", "d");
        System.out.println("Zugriffe auf Graph_K5.gka mit depthFirst: " + getAccessCounter(""));

        List<String> pathsGraph1 = findAllPaths(listGraph1, CustomVertex.getVertex(listGraph1, "f"));
        List<String> pathsGraph2 = findAllPaths(listGraph2, CustomVertex.getVertex(listGraph2, "f"));
        List<String> pathsGraph3 = findAllPaths(listGraph3, CustomVertex.getVertex(listGraph3, "d"));

        assertEquals(pathsGraph1.toString(), "[acdef, akdef, akgef]");
        assertEquals(pathsGraph2.toString(), "[acf]");
        assertEquals(pathsGraph3.toString(), "[ad]");

        assertEquals(getPathLength(pathsGraph1), 4);
        assertEquals(getPathLength(pathsGraph2), 2);
        assertEquals(getPathLength(pathsGraph3), 1);

        List<String> list = new ArrayList<String>();
        System.out.println(TraverseGraphAlgorithms.depthFirstSearch(g3,"a","d",list));
        System.out.println(list);
        list.clear();
        System.out.println(TraverseGraphAlgorithms.depthFirstSearch(g2,"a","f",list));
        System.out.println(list);
        list.clear();
        System.out.println(TraverseGraphAlgorithms.depthFirstSearch(g1, "a", "f", list));
        System.out.println(list);
        list.clear();
        start = System.nanoTime();
        TraverseGraphAlgorithms.depthFirstSearch(g1,"a","f",list);
        System.out.println("ElapsedTime g1:" +(System.nanoTime() - start));
        System.out.println(list);
        list.clear();

    }

    @Test
    public void testGraphK5DepthFirst() {
        initializeAccessCounter();

        List<CustomVertex> listGraphK5 = TraverseGraphAlgorithms.depthFirst(g3, "a", "d");
        System.out.println("Zugriffe auf vollstaendigen Graphen K5 mit depthFirst: " + getAccessCounter(""));
        List<String> vertexNames = new ArrayList<String>(g3.vertexSet());
        vertexNames.remove("a");
        int lengthFromVertexAtoTargetVertex;
        for (String targetVertex : vertexNames) {
            lengthFromVertexAtoTargetVertex = getPathLength(findAllPaths(listGraphK5, CustomVertex.getVertex(listGraphK5, targetVertex)));
            assertEquals(lengthFromVertexAtoTargetVertex, 1);
        }
    }

    @Test
    public void testGraphK5BreadthFirst() {
        initializeAccessCounter();

        List<CustomVertex> listGraphK5 = TraverseGraphAlgorithms.breadthFirst(g3, "a", "d");
        System.out.println("Zugriffe auf vollstaendigen Graphen K5 mit breadthFirst: " + getAccessCounter(""));
        List<String> vertexNames = new ArrayList<String>(g3.vertexSet());
        vertexNames.remove("a");
        int lengthFromVertexAtoTargetVertex;
        for (String targetVertex : vertexNames) {
            lengthFromVertexAtoTargetVertex = getPathLength(findAllPaths(listGraphK5, CustomVertex.getVertex(listGraphK5, targetVertex)));
            assertEquals(lengthFromVertexAtoTargetVertex, 1);
        }
    }



    @Test
    public void testExportGraph() throws Exception {

        GKAFileManager.exportGraph(g1, "g1Export");
        GKAFileManager.exportGraph(g2, "g2Export");
        GKAFileManager.exportGraph(g3, "g3Export");

        Graph reImportedGraph1 = GKAFileManager.importGraph("g1Export");
        Graph reImportedGraph2 = GKAFileManager.importGraph("g2Export");
        Graph reImportedGraph3 = GKAFileManager.importGraph("g3Export");
        List<CustomVertex> listGraph1 = depthFirst(reImportedGraph1, "a", "f");
        List<CustomVertex> listGraph2 = depthFirst(reImportedGraph2, "a", "f");
        List<CustomVertex> listGraph3 = depthFirst(reImportedGraph3, "a", "d");

        List<String> pathsGraph1 = findAllPaths(listGraph1, CustomVertex.getVertex(listGraph1, "f"));
        List<String> pathsGraph2 = findAllPaths(listGraph2, CustomVertex.getVertex(listGraph2, "f"));
        List<String> pathsGraph3 = findAllPaths(listGraph3, CustomVertex.getVertex(listGraph3, "d"));

        assertEquals(pathsGraph1.toString(), "[acdef, akdef, akgef]");
        assertEquals(pathsGraph2.toString(), "[acf]");
        assertEquals(pathsGraph3.toString(), "[ad]");
    }
}
