package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import util.VertexComParator;

public class Graph{
	// 1 graph se bao gom cac dinh va cac canh tuong ung cua no
	private Map<Vertex, List<Edge>> adjacencyList;
	private int[][] adjacencyMatrix;


	// khoi tao graph
	/**
	 * 
	 */
	public Graph() {
		this.adjacencyList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
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
		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(adjacencyList);
		
	    subList.remove(vertex);
		adjacencyList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		adjacencyList.putAll(subList);
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
		addEdgeMatrix();
	}
	
	private void addEdgeMatrix() {
		Map<Vertex, Integer> list = new LinkedHashMap<Vertex, Integer>();
		int i=0;
		for (Vertex vertex : adjacencyList.keySet()) {
			list.put(vertex,  i++);
		}

		for (Vertex vertex : adjacencyList.keySet()) {
			for(Edge edge: adjacencyList.get(vertex)) {
				adjacencyMatrix[list.get(vertex)][list.get(edge.getDestination())] = edge.getWeight();
				adjacencyMatrix[list.get(edge.getDestination())][list.get(vertex)] = edge.getWeight();
			}
		} 

	}
	
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}
	public void addEdge(Edge edge) {
		adjacencyList.get(edge.getSource()).add(edge);
		setAdjacencyMatrix();
	}
	public void removeAll() {
		adjacencyList.clear();
		
		setAdjacencyMatrix();
	}

}
