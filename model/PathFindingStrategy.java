package model;

import java.util.List;

public interface PathFindingStrategy {
	public String[] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex);
}
