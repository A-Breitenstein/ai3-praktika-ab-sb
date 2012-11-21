package samples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.util.*;

import javax.swing.JApplet;
import java.awt.geom.Rectangle2D;

import aufgabe1.CustomVertex;
import aufgabe1.GKAFileManager;
import aufgabe2.AttributedGraph;
import aufgabe2.OptimalWay;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;


/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
public class JGraphAdapterDemo extends JApplet {
    private static final Color   DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension(925, 900);
    private static Set<CustomVertex> path;
    int kante = 25, hoehe = 800 , breite = 800;

    //
    private JGraphModelAdapter m_jgAdapter;

    /**
     * @see java.applet.Applet#init().
     */
    public void init(  ) {
        // create a JGraphT graph
        Graph g = GKAFileManager.importGraph("E:\\Projekte\\IntelliJ\\AI3\\GKA\\gka-praktikum\\graph_alex.gka");
        String start = "New London", target = "Hokkaido";

        Map<String,CustomVertex> pathMap = OptimalWay.aaaaaaStern((AttributedGraph)g,start,target);
        path = OptimalWay.getPathInSout(start,target,pathMap);
        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );

        JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings(jgraph);
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );

        OptimalWay.colorMe(m_jgAdapter,path);
        Set<String> stringSet = g.vertexSet();
        Random rand = new Random();
        List<Point2D> pointList = new ArrayList<Point2D>();
        Point2D newPoint, point;

        for(String s : stringSet){

            int x = rand.nextInt(breite)+kante,
                y = rand.nextInt(hoehe)+kante;

            newPoint = new Point2D(x,y);

            for(int i = 0; i < pointList.size(); i++){

                point = pointList.get(i);

                for (Point2D point2D : pointList) {
                    if(newPoint.xRange(point) && newPoint.yRange(point))
                        i = 0;
                }

                while(newPoint.xRange(point) && newPoint.yRange(point)){
                    //System.out.println("-same-");
                    newPoint.x = rand.nextInt(breite)+kante;
                    if(newPoint.xRange(point)){
                        newPoint.y = rand.nextInt(hoehe)+kante;
                    }

                    System.out.println("newPoint: "+newPoint + " point: "+ point );

                }
            }

            pointList.add(newPoint);
            System.out.println("-added-: " + s);

            positionVertexAt(s, newPoint.x, newPoint.y);
        }
    }


    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
        catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }


    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D       b    = GraphConstants.getBounds(attr);

        GraphConstants.setBounds( attr, new Rectangle( x, y, (int)b.getWidth(),(int) b.getHeight()) );

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
    }
}

class Point2D{
    public int x;
    public int y;
    private static double margin = 100d;

    Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean xRange(Point2D newPoint){
        Point2D oldPoint = (Point2D)this;
        double marXP = oldPoint.x + margin,
                marXM = oldPoint.x - margin,
                newX = newPoint.x;

        return !((newX > marXP) || (marXM > newX));
    }

    public boolean yRange(Point2D newPoint){
        Point2D oldPoint = (Point2D)this;
        double marYP = oldPoint.y + margin,
                marYM = oldPoint.y - margin,
                newY = newPoint.y;

        return !((newY > marYP) || (marYM > newY));
    }

    public boolean same(Point2D newP2d){
        Point2D p2d = (Point2D)this;
        double margin = 2d;

        return !((newP2d.x < p2d.x*margin || newP2d.x > p2d.x*margin) && (newP2d.y < p2d.y*margin || newP2d.y > p2d.y*margin));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2D point2D = (Point2D) o;
        double upperBound_x,lowerBound_x,
               upperBound_y,lowerBound_y,up = 1.50,down = 0.50;

        upperBound_x = x*up;
        lowerBound_x = x*down;

        upperBound_y = y*up;
        lowerBound_y = y*down;

        if(!between(point2D.x,lowerBound_x,upperBound_x)) return false;

        if(!between(point2D.y,lowerBound_y,upperBound_y)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
    private boolean between(double val,double startIntervall,double endIntervall){
        return val>= startIntervall && val<=endIntervall;
    }
}