package util;

import java.util.Comparator;

import model.Vertex;

public class VertexComParator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        
       return o1.getName().compareTo(o2.getName());
    }
    
}
