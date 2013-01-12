package aufgabe4;

import aufgabe1.GKAFileManager;
import aufgabe1.TraverseGraphAlgorithms;
import org.jgrapht.Graph;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.12.12
 * Time: 13:37
 */
public class MatrixFeaturesTest {
    @Test
    public void testCreateAdjazenzMatrix() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("eulertour.gka");
        Map<String,Integer> indeceMap = new HashMap<String,Integer>();
        int[][] adjazenzMatrix;
        adjazenzMatrix = MatrixFeatures.createAdjazenzMatrix(eulertour,indeceMap);

        StringBuilder sb = new StringBuilder();
        for (String vertex : (Collection<String>)eulertour.vertexSet()) {
            sb.append(vertex+" ");
        }
        sb.append("\n");
        for (int[] ints : adjazenzMatrix) {
            for (int spalte = 0; spalte < ints.length; spalte++) {
               sb.append(ints[spalte]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    @Test
    public void testMatrixMultiplication() {
        Graph eulertour = GKAFileManager.importGraph("eulertour.gka");
        Map<String,Integer> indeceMap = new HashMap<String,Integer>();
        eulertour.addVertex("z");
        int[][] adjazenzMatrix;
        adjazenzMatrix = MatrixFeatures.createAdjazenzMatrix(eulertour,indeceMap);

        adjazenzMatrix = MatrixFeatures.multiplyMatrices(adjazenzMatrix,adjazenzMatrix);


        StringBuilder sb = new StringBuilder();
        for (int[] ints : adjazenzMatrix) {
            for (int i = 0; i < ints.length; i++) {
                sb.append(ints[i]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    @Test
    public void testIsThereAWay() throws Exception {
        Graph eulertour = GKAFileManager.importGraph("eulertour.gka");
        eulertour.addVertex("z");
        Map<String,Integer> indeceMap = new HashMap<String,Integer>();
        int[][] adjazenzMatrix;
        adjazenzMatrix = MatrixFeatures.createAdjazenzMatrix(eulertour,indeceMap);

        System.out.println(MatrixFeatures.isThereAWay(adjazenzMatrix, indeceMap.get("c"), indeceMap.get("z")));
    }


    @Test
    public void testIsThereAWay_EULERBIG() throws Exception {
        System.out.println("Importiere Graph");
        Graph eulertour = GKAFileManager.importGraph("EULER_BIG.gka");
        Map<String,Integer> indeceMap = new HashMap<String,Integer>();
        int[][] adjazenzMatrix;
        System.out.println("Erstelle AdjazenzMatrix");
        adjazenzMatrix = MatrixFeatures.createAdjazenzMatrix(eulertour,indeceMap);
        String start = "vertex28",ziel = "noLoopsFixVertex";

        System.out.println("Beginne Tiefensuche");
        long startZeit = System.currentTimeMillis();
        boolean result = TraverseGraphAlgorithms.depthFirstSearch(eulertour, start, ziel, null);
        System.out.println("ElapsedTime tiefensuche: " + (System.currentTimeMillis() - startZeit)+" ms");
        System.out.println("result: "+result);

        System.out.println("Beginne Matrixmultiplikation");
        startZeit = System.currentTimeMillis();
        result = MatrixFeatures.isThereAWay(adjazenzMatrix, indeceMap.get(start), indeceMap.get(ziel));
        System.out.println("ElapsedTime matrixmultiplikation: " + (System.currentTimeMillis() - startZeit)+" ms");
        System.out.println("result: "+result);

//        StringBuilder sb = new StringBuilder();
//        for (int[] ints : adjazenzMatrix) {
//            for (int i = 0; i < ints.length; i++) {
//                sb.append(ints[i]+" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);

    }





}
