package utils;

import java.util.Comparator;

import model.Vertex;

public class VertexComParator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex vertex1, Vertex vertex2) {
        return vertex1.getName().compareTo(vertex2.getName());
    }

}
