package aufgabe4;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 23.12.12
 * Time: 17:36
 */
public class MatrixFeatures {

    /**
     * Matrix Multiplikation
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Ergebnismatrix
     */
    public static int[][] multiplyMatrices(int[][] m1, int[][] m2) {
        int[][] ergebnismatrix = null;

        if (m1[0].length == m2.length) {
            int zeilenm1 = m1.length;
            int spaltenm1 = m1[0].length;
            int spalenm2 = m2[0].length;

            ergebnismatrix = new int[zeilenm1][spalenm2];

            for (int i = 0; i < zeilenm1; i++) {
                for (int j = 0; j < spalenm2; j++) {
                    ergebnismatrix[i][j] = 0;
                    for (int k = 0; k < spaltenm1; k++) {
                        ergebnismatrix[i][j] += m1[i][k] * m2[k][j];
                    }
                }
            }
        } else {
            int zeilen = m1.length;
            int spalten = m1[0].length;

            ergebnismatrix = new int[zeilen][spalten];
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m1[0].length; j++) {
                    ergebnismatrix[i][j] = 0;
                }
            }
        }
        return ergebnismatrix;
    }

    public static int[][] createAdjazenzMatrix(Graph graph,Map<String,Integer> indeceMap) {
        Set<String> vertexSet = graph.vertexSet();
        int vertexSetSize =  vertexSet.size();
        int[][] resultMatrix =  new int[vertexSetSize][vertexSetSize];
        int zeile,spalte = 0;
        int i = 0;

        for (String vertex : vertexSet) {
            indeceMap.put(vertex,i);
            i++;
        }


        if (graph instanceof DirectedGraph) {
        }else if (graph instanceof UndirectedGraph) {


            for (String vertex : vertexSet) {
                for (Object edge : graph.edgesOf(vertex)) {
                    if(graph.getEdgeSource(edge).equals(vertex)){
                       zeile = indeceMap.get(vertex);
                       spalte= indeceMap.get(graph.getEdgeTarget(edge));
                       resultMatrix[zeile][spalte] += 1;
                       resultMatrix[spalte][zeile] += 1;
                    }
                }
            }
        }

        return  resultMatrix;
    }
    public static boolean isThereAWay(int[][] adjazenzMatrix,int sourceVertexIndex,int targetVertexIndex) {
        final int vertecieCount = adjazenzMatrix.length;
        final int max_n = vertecieCount - 1;
       int n = 0;
        for (n = 0; n < max_n; n++) {

            adjazenzMatrix = multiplyMatrices(adjazenzMatrix,adjazenzMatrix);
            if(adjazenzMatrix[sourceVertexIndex][targetVertexIndex]>0){
                System.out.println("n = "+n);
                return true;
            }
        }
        return false;
    }
    public static void removeUndirectedEdge(int[][] adjazenzMatrix,int sourceVertex,int targetVertex) {
        adjazenzMatrix[sourceVertex][targetVertex] = 0;
        adjazenzMatrix[targetVertex][sourceVertex] = 0;
    }
    public static void addUndirectedEdge(int[][] adjazenzMatrix,int sourceVertex,int targetVertex) {
        adjazenzMatrix[sourceVertex][targetVertex] = 1;
        adjazenzMatrix[targetVertex][sourceVertex] = 1;
    }
}
