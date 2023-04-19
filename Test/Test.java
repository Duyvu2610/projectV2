package Test;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

import model.BellmanFordSearch;
import model.Edge;
import model.Graph;
import model.Vertex;
import utils.VertexComParator;

public class Test {
    public static void main(String[] args) {
        // TreeMap<Vertex, List<Edge>> list = new TreeMap<Vertex, List<Edge>>(new
        // VertexComParator());
        // Point p = new Point(0, 0);
        // Vertex a = new Vertex("a", p);
        // Vertex b = new Vertex("b", p);
        // Vertex c = new Vertex("c", p);
        // // Vertex d = new Vertex("d", p);
        // // Vertex e = new Vertex("e", p);

        // Edge ab = new Edge(a, b, 2);
        // Edge ac = new Edge(a, c, 3);
        // // Edge ad = new Edge(a, d, 7);
        // List<Edge> listA = new ArrayList<>(List.of(ab, ac));

        // // Edge bc = new Edge(b, d, 3);
        // // Edge bd = new Edge(b, e, 5);
        // // List<Edge> listB = new ArrayList<>(List.of(bc, bd));

        // // Edge cd = new Edge(c, d, -2);
        // // List<Edge> listC = new ArrayList<>(List.of( cd));

        // // Edge de = new Edge(d, e, 2);
        // // List<Edge> listD = new ArrayList<>(List.of(de));

        // list.put(a, listA);
        // list.put(b, new ArrayList<>());
        // list.put(c, new ArrayList<>());
        // // list.put(d, listD);
        // // list.put(e, listD);

        // int[][] matrix = { { 0, 2, 3, 7, 0 }, { 2, 0, 0, 3, 5 }, { 3, 0, 0, -2, 0 },
        // { 7, 3, -2, 0, 2 },
        // { 0, 5, 0, 2, 0 } };
        // int[][] matrix1 = { { 0, 8, 8 }, { 8, 0, 0 }, { 8, 0, 0 } };

        // Graph graph = new Graph(list, matrix1);

        // BellmanFordSearch search = new BellmanFordSearch();
        // search.findShortestPath(graph, a, c);

        Map<Vertex, List<Edge>> sublist = new TreeMap<>(new VertexComParator());
        sublist.put(new Vertex("a", new Point(0, 0)), new ArrayList<>());

        System.out.println(sublist.get(new Vertex("b", new Point(0, 0))));
       

    }
}
