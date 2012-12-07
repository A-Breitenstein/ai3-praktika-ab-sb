package aufgabe3;

import aufgabe1.CustomVertex;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 07.12.12
 * Time: 14:12
 */
public class Prim {

    Queue<CustomVertex> vertexQueue = new PriorityQueue<CustomVertex>();


    public void PrimAlgorithm(Graph graph){

        List edges = (List)graph.edgeSet();

        Object smallestEdge = edges.get(0);

        Graph graphT = new WeightedPseudograph(DefaultWeightedEdge.class);

        Collection<Object> graphTvertexes = graphT.vertexSet();

//        Map<String,String> sourceTo

        while (graphTvertexes.size() < graph.vertexSet().size()) {
            for(Object e : edges){
                if((graph.getEdgeWeight(e) <= graph.getEdgeWeight(smallestEdge)) &&)
                    smallestEdge = e;
            }

            Object source = graph.getEdgeSource(smallestEdge),
                   target = graph.getEdgeTarget(smallestEdge);

            if (graphTvertexes.contains(source) || graphTvertexes.contains(target)) {
                graphT.addVertex(source);
                graphT.addVertex(target);
                graphT.addEdge(source,target);
            }


        }
    }



    class PriorityQueueComparator implements Comparator<CustomVertex>{

        @Override
        public int compare(CustomVertex o1, CustomVertex o2) {

            return Double.compare(o1.getShortesConnectionVertex(), o2.getShortesConnectionVertex());

        }
    }

}
