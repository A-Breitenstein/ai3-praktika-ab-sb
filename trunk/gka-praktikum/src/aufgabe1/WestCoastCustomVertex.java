package aufgabe1;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 26.10.12
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class WestCoastCustomVertex {
    public int step;
    public List nachbarn;
    public String vertex;
    public WestCoastCustomVertex(String vertex,int steps,List nachbarn){
        this.step = steps;
        this.vertex = vertex;
        this.nachbarn = nachbarn;
    }
    public WestCoastCustomVertex(String vertex){
        this.vertex = vertex;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof WestCoastCustomVertex)) return false;
        return ((WestCoastCustomVertex)obj).vertex.equals(this.vertex);


    }
}
