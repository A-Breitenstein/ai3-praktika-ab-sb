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

    public CustomVertex(String label, int steps, List<String> adjacentStringVertexes) {
        this.step = steps;
        this.label = label;
        this.adjacentStringVertexes = adjacentStringVertexes;
        this.checked = false;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CustomVertex)) return false;
        return ((CustomVertex) obj).label.equals(this.label);
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<String>();
        strings.add(label);
        strings.add(String.valueOf(step));

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
