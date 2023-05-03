package model;

import java.util.*;

import utils.VertexComParator;

public class DirectedGraph extends Graph {
	private static DirectedGraph instance;

	public static DirectedGraph getInstance() {
		if (instance == null) {
			synchronized (Graph.class) {
				if (instance == null) {
					instance = new DirectedGraph();
				}
			}
		}
		return instance;
	}

	@Override
	public int countEdges() {
		int res = 0;
		int sizeMatrix = this.getAdjacencyMatrix().length;
		for (int row = 0; row < sizeMatrix; row++) {
			for (int column = 0; column < sizeMatrix; column++) {
				if (this.getAdjacencyMatrix()[row][column] != 0) {
					res++;
				}
			}
		}
		return res;
	}

	@Override
	public void setAdjacencyMatrix() {
		TreeMap<Vertex, List<Edge>> sublistTreeMap = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		sublistTreeMap.putAll(this.getAdjacencyList());
		this.getAdjacencyList().clear();
		this.getAdjacencyList().putAll(sublistTreeMap);
		int size = this.getAdjacencyList().size();
		this.setAdjacencyMatrix(new int[size][size]);
		// đánh index cho từng vertex
		Map<Vertex, Integer> list = new LinkedHashMap<Vertex, Integer>();
		int i = 0;
		for (Vertex vertex : this.getAdjacencyList().keySet()) {
			list.put(vertex, i++);
		}

		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(this.getAdjacencyList());

		for (Vertex vertex : subList.keySet()) {

			for (Edge edge : subList.get(vertex)) {
				if (subList.containsKey(edge.getDestination())) {
					// cập nhật trọng số cho vertex nguồn và đich của edge vừa mới được thêm
					this.getAdjacencyMatrix()[list.get(vertex)][list.get(edge.getDestination())] = edge.getWeight();
				}
			}
		}
	}

	@Override
	public int[][] getEdges(int rootNode) {
		int[][] res = new int[this.countEdges()][3];
		int index = 0;
		for (int i = 0; i < this.getAdjacencyMatrix().length; i++) {
			for (int j = 0; j < this.getAdjacencyMatrix().length; j++) {
				if (this.getAdjacencyMatrix()[i][j] != 0) {
					int[] edge = { i, j, this.getAdjacencyMatrix()[i][j] };
					res[index++] = edge;
				}
			}
		}
		return res;
	}

	@Override
	public void addEdge(Edge edge) {
		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(this.getAdjacencyList());
		subList.get(edge.getSource()).add(edge);
		setAdjacencyList(new TreeMap<Vertex, List<Edge>>(new VertexComParator()));
		this.getAdjacencyList().putAll(subList);
		setAdjacencyMatrix();
	}
}
