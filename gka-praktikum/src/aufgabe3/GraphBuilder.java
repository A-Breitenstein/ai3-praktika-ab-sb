package aufgabe3;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;
import services.RandomManager;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 07.12.12
 * Time: 14:12
 */
public class GraphBuilder {

    private static final int ZERO = 0;
    private static String vertexName = "vertex";

    private String sourceVertex,
            targetVertex;

    private double upperBound = 0d,
            lowerBound = 0d;

    private int numberOfVertices = 0, numberOfEdges = 0;

    private boolean multiEdges = false;
    private boolean loopsAllowed = false;

    private GraphBuilder() {
    }

    public static GraphBuilder create() {
        return new GraphBuilder();
    }

    public GraphBuilder setGraphStructure(int numberOfVertices, int numberOfEdges) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;

        return this;
    }

    public GraphBuilder setEdgeWeightRange(double upperBound, double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;

        return this;
    }

    public GraphBuilder allowMultiEdges() {
        this.multiEdges = true;

        return this;
    }

    public GraphBuilder allowLoops() {
        this.loopsAllowed = true;

        return this;
    }

    public Graph generate() {

        if (this.numberOfVertices == 0)
            throw new IllegalArgumentException("The Number of Vertices is lower than 1, numberOfVertices: " + numberOfVertices);


        Graph generatedGraph = new WeightedPseudograph(DefaultWeightedEdge.class);
        List<String> generatedVerticesNames = new ArrayList<String>();

        double edgeWeight;

        Set<String> addedEdgeSet = new HashSet<String>();

        sout_generateVertices(numberOfVertices);

        for (int i = 0; i < numberOfVertices; i++) {
            generatedVerticesNames.add(vertexName + i);
        }

        sout_addVertices(numberOfVertices);

        while (generatedGraph.vertexSet().size() < generatedVerticesNames.size()) {

            for (int i = 1; i < generatedVerticesNames.size(); i++) {
                sourceVertex = generatedVerticesNames.get(i - 1);
                targetVertex = generatedVerticesNames.get(i);
                edgeWeight = RandomManager.doubleNumber(upperBound, lowerBound);

                Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, targetVertex, edgeWeight);

                addedEdgeSet.add(sourceVertex + targetVertex);
                addedEdgeSet.add(targetVertex + sourceVertex);
            }
        }

        boolean edgeCheck, equalVertices;

        for (int i = generatedGraph.edgeSet().size(); i < numberOfEdges; i++) {
            do {
                int
                        sourceVertexIndex = RandomManager.intNumber(numberOfVertices, ZERO),
                        targetVertexIndex = RandomManager.intNumber(numberOfVertices, ZERO);

                sourceVertex = generatedVerticesNames.get(sourceVertexIndex);
                targetVertex = generatedVerticesNames.get(targetVertexIndex);


                equalVertices = sourceVertex.equals(targetVertex);
                if (multiEdges) {
                    edgeCheck = equalVertices;
                } else {
                    edgeCheck = equalVertices && (addedEdgeSet.contains(sourceVertex + targetVertex) || addedEdgeSet.contains(targetVertex + sourceVertex));
                }
            } while (edgeCheck);


            edgeWeight = RandomManager.doubleNumber(upperBound, lowerBound);
            Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, targetVertex, edgeWeight);
        }

        sout_graphGenerated(generatedGraph);

        return generatedGraph;
    }

    private static void sout_graphGenerated(Graph graph) {
        System.out.println("graph was generated with " + graph.vertexSet().size() + " Vertices and " + graph.edgeSet().size() + " Edges");
    }

    private static void addAllVertices(Graph graph, Collection objects) {

        for (Object object : objects) {
            graph.addVertex(object);
        }
    }

    private static void sout_addVertices(int numberOfVertices) {
        System.out.println(numberOfVertices + " vertices are being added to new Graph");
    }

    private static void sout_generateVertices(int numberOfVertices) {
        System.out.println(numberOfVertices + " vertices are being generated");
    }

    /**
     *  Erzeugt einen zusammenhängenden ungerichteten Graphen mit geradem Knoten Grad
     *
     *  Dies wird dadurch erreicht, dass die Knoten zuerst wie eine liste zusammen gehangen werden, danach
     *  wird der Anfangs Knoten mit dem Endknoten verbunden dadurch hat das Grundgerüst einen geraden Knotengrad,
     *  nun werden die zufällig weitere Edges eingefügt. Dabei wird darauf geachtet ob Mehrfachkanten und Schlaufen
     *  erlaubt sind oder nicht. Ist nun die Anzahl an einzufügenden Kanten erreicht wird überprüft wie viele von den
     *  Knoten ungreade Knotengrade haben, diese ungeraden Knoten werden dann untereinander verbunden.
     *
     * hätte man noch schöner bauen können, aber der code von dir war schon fast fertig xD
     *
     * @return   zusammenhängenden ungerichteten Graphen mit geradem Knoten Grad
     */
    public Graph generateConnectedUndirectedGraphWithEvenVertexGrade() {
        Graph generatedGraph = new Pseudograph(DefaultEdge.class);

        List<String> generatedVerticesNames = new ArrayList<String>();

        Set<String> addedEdgeSet = new HashSet<String>();


        sout_generateVertices(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            generatedVerticesNames.add(vertexName + i);
        }


        sout_addVertices(numberOfVertices);
        // Graph zusammenhängend machen
        while (generatedGraph.vertexSet().size() < generatedVerticesNames.size()) {

            for (int i = 1; i < generatedVerticesNames.size(); i++) {
                sourceVertex = generatedVerticesNames.get(i - 1);
                targetVertex = generatedVerticesNames.get(i);

                Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, targetVertex);

                addedEdgeSet.add(sourceVertex + targetVertex);
                addedEdgeSet.add(targetVertex + sourceVertex);
            }
        }
        // Zudiesem Zeitpunkt sollten alle Knoten bis auf den Start und End Knoten
        // einen geraden Grad haben nun werden abschließen noch der Start und End Knoten verbunden
        // damit diese auch einen geraden Grad bebekommen
        sourceVertex = generatedVerticesNames.get(0);
        targetVertex = generatedVerticesNames.get(generatedVerticesNames.size() - 1);

        Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, targetVertex);

        addedEdgeSet.add(sourceVertex + targetVertex);
        addedEdgeSet.add(targetVertex + sourceVertex);


        // restliche edges einfügen
        boolean equalVertices, edgeCheck;
        int sourceVertexIndex, targetVertexIndex;

        for (int i = generatedGraph.edgeSet().size(); i < numberOfEdges; i++) {
            do {

                sourceVertexIndex = RandomManager.intNumber(numberOfVertices, ZERO);
                targetVertexIndex = RandomManager.intNumber(numberOfVertices, ZERO);

                sourceVertex = generatedVerticesNames.get(sourceVertexIndex);
                targetVertex = generatedVerticesNames.get(targetVertexIndex);


                equalVertices = sourceVertex.equals(targetVertex);
                if (multiEdges) {
                    if (loopsAllowed)
                        edgeCheck = false;
                    else
                        edgeCheck = equalVertices;
                } else {
                    if (loopsAllowed)
                        edgeCheck = (addedEdgeSet.contains(sourceVertex + targetVertex) || addedEdgeSet.contains(targetVertex + sourceVertex));
                    else
                        edgeCheck = equalVertices || (addedEdgeSet.contains(sourceVertex + targetVertex) || addedEdgeSet.contains(targetVertex + sourceVertex));

                }
            } while (edgeCheck);

            Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, targetVertex);

            addedEdgeSet.add(sourceVertex + targetVertex);
            addedEdgeSet.add(targetVertex + sourceVertex);
        }


        // letzte phase, anpassen der restlichen knotengrade
        List<String> oddVertecies = getVerteciesWithOddVertexGrad(generatedGraph);
        int oddVerteciesCount = oddVertecies.size();
        System.out.println("Found " + oddVerteciesCount + " Vertecies with odd grade");
        if (oddVerteciesCount > 0) {
            if (oddVerteciesCount % 2 == 0) {
                while(oddVerteciesCount > 0) {
                    do {

                        sourceVertexIndex = RandomManager.intNumber(oddVerteciesCount, ZERO);
                        targetVertexIndex = RandomManager.intNumber(oddVerteciesCount, ZERO);

                        sourceVertex = oddVertecies.get(sourceVertexIndex);
                        targetVertex = oddVertecies.get(targetVertexIndex);


                        equalVertices = sourceVertex.equals(targetVertex);
                        if (multiEdges) {
                            if (loopsAllowed)
                                edgeCheck = false;
                            else
                                edgeCheck = equalVertices;
                        } else {
                            if (loopsAllowed)
                                edgeCheck = (addedEdgeSet.contains(sourceVertex + targetVertex) || addedEdgeSet.contains(targetVertex + sourceVertex));
                            else {
                                edgeCheck = equalVertices || (addedEdgeSet.contains(sourceVertex + targetVertex) || addedEdgeSet.contains(targetVertex + sourceVertex));
                                // no loops fix
                                if (oddVerteciesCount == 2 && !equalVertices) {
                                    Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, "noLoopsFixVertex");
                                    Graphs.addEdgeWithVertices(generatedGraph, targetVertex, "noLoopsFixVertex");

                                    System.out.println("had to fix graph, added noLoopsFixVertex to the graph to ensure the even vertex grade");
                                    return generatedGraph;
                                }
                            }

                        }
                    } while (edgeCheck);

                    Graphs.addEdgeWithVertices(generatedGraph, sourceVertex, targetVertex);

                    addedEdgeSet.add(sourceVertex + targetVertex);
                    addedEdgeSet.add(targetVertex + sourceVertex);

                    oddVertecies = getVerteciesWithOddVertexGrad(generatedGraph);
                    oddVerteciesCount = oddVertecies.size();
                }
            } else {
                throw new IllegalArgumentException("fail... try again");
            }
        }

        System.out.println(
                (getVerteciesWithOddVertexGrad(generatedGraph).size() == 0) ?
                        ("Alle Knoten besitzen einen geraden Grad ") :
                        ("Es gibt einen Knoten der einen ungeraden Grad hat ... versuchs nochmal"
                        ));
        return generatedGraph;


    }

    /**
     * <b>Hilfsfunktion</b> zu generateConnectedUndirectedGraphWithEvenVertexGrade(...) <br>
     *  Bestimmt die Knoten mit den ungeraden Knotengrad in einem übergebenen Graphen
     * @param g
     * @return liste von Strings mit den Knoten Namen
     */
    private List<String> getVerteciesWithOddVertexGrad(Graph g) {
        List<String> oddVertecies = new ArrayList<String>();
        for (String vertex : (Collection<String>) g.vertexSet()) {
            if (g.edgesOf(vertex).size() % 2 == 1)
                oddVertecies.add(vertex);
        }
        return oddVertecies;
    }
}
