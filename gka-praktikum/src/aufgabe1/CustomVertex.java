package aufgabe1;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 26.10.12
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class CustomVertex {
    public int step;
    public List<String> adjacentStringVertexes;
    public String label;

    public CustomVertex(String label, int steps, List<String> adjacentStringVertexes) {
        this.step = steps;
        this.label = label;
        this.adjacentStringVertexes = adjacentStringVertexes;
    }

    public CustomVertex(String label) {
        this.label = label;
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
}
