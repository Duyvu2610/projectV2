package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import utils.VertexComParator;

public class Graph {
	// 1 graph se bao gom cac dinh va cac canh tuong ung cua no
	private Map<Vertex, List<Edge>> adjacencyList;
	private int[][] adjacencyMatrix;

	// khoi tao graph
	public Graph() {
		this.adjacencyList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		setAdjacencyMatrix();
		// this.adjacencyMatrix = new int[0][0];
	}

	public Graph(Map<Vertex, List<Edge>> adjacencyList, int[][] adjacencyMatrix) {
		this.adjacencyList = adjacencyList;
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public Graph(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	// them 1 dinh
	public void addVertex(Vertex vertex) {
		adjacencyList.put(vertex, new ArrayList<>());
		setAdjacencyMatrix();

	}

	// Xóa 1 đỉnh
	public void removeVertex(Vertex vertex) {

		// Xóa đỉnh này khỏi adjacency list.
		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(adjacencyList);

		subList.remove(vertex);

		for (Vertex vertexs : subList.keySet()) {
			for (int i = 0; i < subList.get(vertexs).size(); i++) {
				if (subList.get(vertexs).get(i).getDestination().equals(vertex)) {
					subList.get(vertexs).remove(subList.get(vertexs).get(i));
				}
			}
		}

		adjacencyList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		adjacencyList.putAll(subList);
		// Cập nhật adjacency matrix.

		setAdjacencyMatrix();
		// Nếu có view hiển thị đồ thị thì cũng cần xóa vertex view tương ứng.
		// ...

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

	// this medthod is used for ungraph
	public int countEdges() {
		int res = 0;
		int sizeMatrix = adjacencyMatrix.length;
		ArrayList<Integer> exceptList = new ArrayList<Integer>();
		for (int row = 0; row < sizeMatrix; row++) {
			for (int column = 0; column < sizeMatrix; column++) {
				if (adjacencyMatrix[row][column] != 0 && !exceptList.contains(column)) {
					res++;
				}
			}
			exceptList.add(row);
		}
		return res;
	}

	// this medthod is used for ungraph
	public int[][] getEdges() {
		int[][] res = new int[this.countEdges()][3];
		int sizeMatrix = adjacencyMatrix.length;
		ArrayList<Integer> exceptList = new ArrayList<Integer>();
		int index = 0;
		for (int row = 0; row < sizeMatrix; row++) {
			for (int column = 0; column < sizeMatrix; column++) {
				if (adjacencyMatrix[row][column] != 0 && !exceptList.contains(column)) {
					int[] edge = { row, column, adjacencyMatrix[row][column] };
					res[index++] = edge;
				}
			}
			exceptList.add(row);
		}
		return res;
	}

	public void setAdjacencyMatrix() {

		int size = adjacencyList.size();
		adjacencyMatrix = new int[size][size];

		// Thêm trọng số của cạnh vào ma trận liền kề

		// đánh index cho từng vertex
		Map<Vertex, Integer> list = new LinkedHashMap<Vertex, Integer>();
		int i = 0;
		for (Vertex vertex : adjacencyList.keySet()) {
			list.put(vertex, i++);
		}

		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(adjacencyList);

		for (Vertex vertex : subList.keySet()) {

			for (Edge edge : subList.get(vertex)) {
				if (subList.containsKey(edge.getDestination())) {

					// cập nhật trọng số cho vertex nguồn và đich của edge vừa mới được thêm
					adjacencyMatrix[list.get(vertex)][list.get(edge.getDestination())] = edge.getWeight();
					adjacencyMatrix[list.get(edge.getDestination())][list.get(vertex)] = edge.getWeight();
				}
			}
		}

	}

	public int[][] getAdjacencyMatrix() {

		return adjacencyMatrix;
	}

	public void addEdge(Edge edge) {
		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(adjacencyList);
		subList.get(edge.getSource()).add(edge);
		adjacencyList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		adjacencyList.putAll(subList);
		setAdjacencyMatrix();
	}

	public void removeAll() {
		adjacencyList.clear();
		setAdjacencyMatrix();
	}

	public boolean isConnected() {
		return connectedHelper();
	}

	public int deg(int vertex) {
		int size = adjacencyMatrix.length;
		int res = 0;

		for (int col = 0; col < size; col++) {
			if (adjacencyMatrix[vertex][col] != 0) {
				res++;
			}
		}
		return res;

	}

	private boolean connectedHelper() {
		int size = adjacencyMatrix.length;
		for (int row = 0; row < size; row++) {
			if (deg(row) == 0) return false;
		}
		return true;
	}

	public void setGraph(Map<Vertex, List<Edge>> adjacencyList) {
		this.adjacencyList = adjacencyList;
		setAdjacencyMatrix();
	}

}
