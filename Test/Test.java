package Test;


import java.awt.Point;
import java.util.*;

import model.BellmanFordSearch;
import model.Edge;
import model.Graph;
import model.PathFindingStrategy;
import model.Vertex;
import util.VertexComParator;

public class Test {
    public static void main(String[] args) {
        TreeMap<Vertex, List<Edge>> list = new TreeMap<Vertex, List<Edge>>( new VertexComParator());
        Point p = new Point(0,0);
         Vertex a = new Vertex("a", p);
         Vertex b = new Vertex("b", p);
         Vertex c = new Vertex("c", p);
         Vertex d = new Vertex("d", p);
         Vertex e = new Vertex("e", p);
       

         Edge ab = new Edge(a, b, 2);
         Edge ac = new Edge(a, c, 3);
         Edge ad = new Edge(a, d, 7);
         List<Edge> listA = new ArrayList<>(List.of(ab, ac, ad));

       
         Edge bc = new Edge(b, d, 3);
         Edge bd = new Edge(b, e, 5);
         List<Edge> listB = new ArrayList<>(List.of(bc, bd));
        

       
      
         Edge cd = new Edge(c, d, -2);
         List<Edge> listC = new ArrayList<>(List.of( cd));

       
         Edge de = new Edge(d, e, 2);
         List<Edge> listD = new ArrayList<>(List.of(de));


         list.put(a, listA);
         list.put(b, listB);
         list.put(c, listC);
         list.put(d, listD);
         list.put(e, listD);
       

         int[][] matrix = {{0,2,3,7,0}, {2,0,0,3,5}, {3,0,0,-2,0}, {7,3,-2,0,2}, {0,5,0,2,0}};
        
          Graph graph = new Graph(list, matrix);
        
         BellmanFordSearch search = new BellmanFordSearch();
//         for(Vertex vertex : graph.getVertices()) {
//             System.out.println(vertex.getName());
//         }
//         System.out.println(Arrays.toString(search.findShortestPath(graph, a, e)));
//    TreeMap<Vertex, List<Edge>> list = new TreeMap<Vertex, List<Edge>>( new VertexComParator());
//    Point p = new Point(0,0);
//    Vertex a = new Vertex("c", p);
//    Vertex b = new Vertex("b", p);
//    list.put(a, null);
//    list.put(b, null);
   
      

    }
}
