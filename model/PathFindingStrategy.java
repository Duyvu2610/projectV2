package model;

public interface PathFindingStrategy {
	public Vertex[] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex);
}
