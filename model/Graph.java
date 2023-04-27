package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import utils.VertexComParator;

public abstract class Graph {
	// 1 graph se bao gom cac dinh va cac canh tuong ung cua no
	private Map<Vertex, List<Edge>> adjacencyList;
	private int[][] adjacencyMatrix;
	private PathFindingStrategy path;
	private Vertex startVertex;
	private Vertex endVertex;

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

	public List<Edge> getEdges(Vertex vertex) {
		return adjacencyList.get(vertex);
	}

	public Map<Vertex, List<Edge>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(Map<Vertex, List<Edge>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public ArrayList<Vertex> getVertices() {
		return new ArrayList<Vertex>(this.adjacencyList.keySet());
	}

	public void setStartVertex(Vertex startVertex) {
		this.startVertex = startVertex;
	}

	public void setEndVertex(Vertex endVertex) {
		this.endVertex = endVertex;
	}

	public Vertex getStartVertex() {
		return startVertex;
	}

	public Vertex getEndVertex() {
		return endVertex;
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

	public abstract int countEdges();

	public abstract int[][] getEdges(int rootNode);

	public abstract void setAdjacencyMatrix();

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public abstract void addEdge(Edge edge);

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
			if (deg(row) == 0)
				return false;
		}
		return true;
	}

	public void setGraph(Map<Vertex, List<Edge>> adjacencyList) {
		this.adjacencyList = adjacencyList;
		setAdjacencyMatrix();
	}

	public void setPath(PathFindingStrategy path) {
		this.path = path;
	}

	public Vertex[] pathFinding() {
		return path.findShortestPath(this, startVertex, endVertex);
	}

	public boolean checkUnGraph() {

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				if (adjacencyMatrix[i][j] != adjacencyMatrix[j][i])
					return false;
			}
		}
		return true;
	}

	public void printMatrix() {
		int size = this.adjacencyMatrix.length + 1;
		int index = 0;
		int index1 = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0 && j == 0) {
					System.out.print("\t");
				} else if (i == 0 && j != 0) {
					Vertex x = (Vertex) adjacencyList.keySet().toArray()[index++];
					System.out.print(x.getName() + "\t");
				} else if (i != 0 && j == 0) {
					Vertex x = (Vertex) adjacencyList.keySet().toArray()[index1++];
					System.out.print(x.getName() + "\t");
				} else {
					System.out.print(adjacencyMatrix[i - 1][j - 1] + "\t");
				}
			}
			System.out.println();
		}
	}

	public void printEdge(int rootNode) {
		String s = "";
		String edge = "";

		for (int i = 0; i < getEdges(rootNode).length; i++) {
			edge += "(";
			for (int j = 0; j < getEdges(rootNode)[i].length; j++) {
				if (j == getEdges(rootNode)[i].length - 1) {
					break;
				}
				Vertex x = (Vertex) adjacencyList.keySet().toArray()[getEdges(rootNode)[i][j]];
				if (j == 0) {
					edge += x.getName() + "-";
				} else {
					edge += x.getName();
				}

			}
			edge += ")";
			s += edge + "\t";
			edge = "";
		}
		System.out.println(s);
	}

	public void printNode() {
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			Vertex x = (Vertex) adjacencyList.keySet().toArray()[i];

			System.out.print(x.getName() + "\t");
		}
	}

	public void rename(Vertex vertex, String name) {
		vertex.setName(name);
		setAdjacencyMatrix();
	}

	public int pathCostOfFind(Vertex[] vertexes) {
		int result = 0;
		if (vertexes != null) {
			Edge edge = new Edge(null, null, 0);
			for (int i = 0; i < vertexes.length - 1; i++) {
				int index = i;
				edge.setSource(vertexes[index]);
				edge.setDestination(vertexes[index + 1]);
				for (Edge e : this.getAdjacencyList().get(vertexes[index])) {
					if (e.getSource().equals(edge.getSource()) && e.getDestination().equals(edge.getDestination())) {
						result += e.getWeight();
					}
				}
			}
		}
		return result;
	}

}
