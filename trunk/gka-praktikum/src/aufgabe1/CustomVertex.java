package aufgabe1;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 26.10.12
 * Time: 18:24
 */
public class CustomVertex {
    public int step;
    public List<String> adjacentStringVertexes;
    public String label;
    private boolean checked;
    private String predecessor;
    private double distance;
    private double attribute;
    private double heuristic;

    public CustomVertex(String label, int steps, List<String> adjacentStringVertexes) {
        this.step = steps;
        this.label = label;
        this.adjacentStringVertexes = adjacentStringVertexes;
        this.checked = false;
    }
    public CustomVertex(double distance,double attribute,double heuristic, List<String> adjacentStringVertexes, String label, boolean checked, String predecessor){
        this(distance,adjacentStringVertexes, label, checked, predecessor);
        this.attribute = attribute;
        this.heuristic = heuristic;
    }
    public CustomVertex(double distance, List<String> adjacentStringVertexes, String label, boolean checked, String predecessor) {
        this.distance = distance;
        this.adjacentStringVertexes = adjacentStringVertexes;
        this.label = label;
        this.checked = checked;
        this.predecessor = predecessor;
    }

    public CustomVertex(String label) {
        this.label = label;
        this.checked = false;
    }

    public boolean isChecked(){
            return this.checked;
    }

    public void checked(){
        this.checked = true;
    }

    public CustomVertex setDistance(double distance){
        this.distance = distance;
        return this;
    }

    public CustomVertex setPredecessor(String predecessor){
        this.predecessor = predecessor;
        return this;
    }

    public CustomVertex setOK(){
        this.checked();
        return this;
    }

    public String getPredecessor(){
        return predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isOK(){
        return isChecked();
    }
    public CustomVertex setAttribute(double attribute){
        this.attribute = attribute;
        return  this;
    }
    public double getAttribute(){
        return attribute;
    }
    public CustomVertex setHeuristic(double heuristic){
        this.heuristic = heuristic;
        return this;
    }
    public double getHeuristic(){
        return heuristic;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof CustomVertex && ((CustomVertex) obj).label.equals(this.label);
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<String>();
        strings.add(label);
        strings.add(String.valueOf(step));
        strings.add("predecessor: "+String.valueOf(predecessor));
        strings.add("distance: "+String.valueOf(distance) );
        strings.add("attribute: "+String.valueOf(attribute));
        strings.add("heuristicValue: "+String.valueOf(heuristic));
        strings.add("Flagged: "+String.valueOf(checked));

        return strings.toString() + ":" + adjacentStringVertexes.toString();
    }
    public static CustomVertex getVertex(List<CustomVertex> list,String name){
        for(CustomVertex vertex : list){
            if(vertex.label.equals(name)){
                return vertex;
            }
        }
        return  null;
    }
}
