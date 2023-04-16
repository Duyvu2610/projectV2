package model;

public interface PathFindingStrategy {
	public String[] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex);
}
