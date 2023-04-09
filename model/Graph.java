package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Graph{
	// 1 graph se bao gom cac dinh va cac canh tuong ung cua no
	private Map<Vertex, List<Edge>> adjacencyList;
	private int[][] adjacencyMatrix;


	// khoi tao graph
	public Graph() {
		this.adjacencyList = new LinkedHashMap<>();
		this.adjacencyMatrix = new int[0][0];
	}
	// them 1 dinh
	public void addVertex(Vertex vertex) {
		adjacencyList.put(vertex, new ArrayList<>());
		setAdjacencyMatrix();
		
	}
	// Xóa 1 đỉnh
	public void removeVertex(Vertex vertex) {
	    // Xóa tất cả các cạnh liên quan đến đỉnh này (cả ở adjacency list và adjacency matrix).
//	    List<Edge> edgesToRemove = new ArrayList<>();
//	    for (List<Edge> edges : adjacencyList.values()) {
//	        for (Edge edge : edges) {
//	            if (edge.connects(vertex)) {
//	                edgesToRemove.add(edge);
//	            }
//	        }
//	    }
//	    for (Edge edge : edgesToRemove) {
//	        removeEdge(edge);
//	    }
	    // Xóa đỉnh này khỏi adjacency list.
//		System.out.println("vi tri click " + vertex);
//		System.out.println("vertex list " + adjacencyList.keySet() );
		Map<Vertex, List<Edge>> subList = new LinkedHashMap<Vertex, List<Edge>>(adjacencyList);
	    subList.remove(vertex);
		adjacencyList = new LinkedHashMap<Vertex, List<Edge>>(subList);
	    // Cập nhật adjacency matrix.
	    setAdjacencyMatrix();
	    // Nếu có view hiển thị đồ thị thì cũng cần xóa vertex view tương ứng.
	    // ...
	}

	private void removeEdge(Edge edge) {
	    Vertex source = edge.getSource();
	    Vertex destination = edge.getDestination();
	    adjacencyList.get(source).remove(edge);
	    adjacencyList.get(destination).remove(edge);
	}
	// them 1 canh
	public void addEdge(Vertex source, Vertex destination, int weight) {
		Edge edge = new Edge(source, destination, weight);
		adjacencyList.get(source).add(edge);
		adjacencyList.get(destination).add(edge);
	}

	public List<Edge> getEdges(Vertex vertex) {
		return adjacencyList.get(vertex);
	}

	public Map<Vertex, List<Edge>> getAdjacencyList() {
		return adjacencyList;
	}
	public ArrayList<Vertex> getVertices() {
		return new ArrayList<>(adjacencyList.keySet());
	}
	public void setAdjacencyMatrix() {
		int size = adjacencyList.size();
		adjacencyMatrix = new int[size][size];
	}
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	
	

}
