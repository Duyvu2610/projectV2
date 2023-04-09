package model;

import java.util.List;

public interface PathFindingStrategy {
	public List<Vertex> findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex);
}
