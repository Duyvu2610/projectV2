package Test;

import java.awt.List;
import java.awt.Point;
import java.util.*;

import model.Vertex;
import util.VertexComParator;

public class Test {
    public static void main(String[] args) {
       TreeMap<Vertex, Integer> map = new TreeMap<Vertex, Integer>( new VertexComParator());
       Point p = new Point(0,0);
       map.put(new Vertex("a", p), 1);
       map.put(new Vertex("c", p), 1);
       map.put(new Vertex("b", p), 1);
       System.out.println(map);

    }
}
