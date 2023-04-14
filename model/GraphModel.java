package model;

import java.util.List;

public class GraphModel{
	private Graph graph;
	private Vertex startVertex;
	private Vertex endVertex;
	private PathFindingStrategy pathFindingStrategy;
	private String[] shortestPath;
	

	public GraphModel(Graph graph) {
		this.graph = graph;
		pathFindingStrategy = new DijkstraPathFindingStrategy();
		
	}

	public void setStartVertex(Vertex startVertex) {
		this.startVertex = startVertex;
		shortestPath = null;
//		setChanged();
//		notifyObservers();
	}

	public void setEndVertex(Vertex endVertex) {
		this.endVertex = endVertex;
		shortestPath = null;
//		setChanged();
//		notifyObservers();
	}

	public void setPathFindingStrategy(PathFindingStrategy pathFindingStrategy) {
		this.pathFindingStrategy = pathFindingStrategy;
		shortestPath = null;
//		setChanged();
//		notifyObservers();
	}

	public String[] getShortestPath() {
		if (shortestPath == null) {
			shortestPath = pathFindingStrategy.findShortestPath(graph, startVertex, endVertex);
		}
		return shortestPath;
	}
	public int[][] getMatrix(){
		return graph.getAdjacencyMatrix();
	}
	public List<Vertex> getVertices(){
		return graph.getVertices();
	}



	public Graph getGraph() {
		return graph;
	}

	public void addVertex(Vertex vertex) {
		graph.addVertex(vertex);
//		setChanged();
//		notifyObservers();
	}
	public void removeVertex(Vertex vertex) {
	
		graph.removeVertex(vertex);
	}

    public void addEdge(Edge edge) {
		graph.addEdge(edge);
    }

	public void removeAll() {
		graph.removeAll();
	}

	
}
