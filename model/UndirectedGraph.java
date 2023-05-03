package model;

import java.util.*;

import utils.VertexComParator;

public class UndirectedGraph extends Graph {
	private static UndirectedGraph instance;

	public static UndirectedGraph getInstance() {
		if (instance == null) {
			synchronized (Graph.class) {
				if (instance == null) {
					instance = new UndirectedGraph();
				}
			}
		}
		return instance;
	}

	@Override
	public int countEdges() {
		int res = 0;
		int sizeMatrix = this.getAdjacencyMatrix().length;
		ArrayList<Integer> exceptList = new ArrayList<Integer>();
		for (int row = 0; row < sizeMatrix; row++) {
			for (int column = 0; column < sizeMatrix; column++) {
				if (this.getAdjacencyMatrix()[row][column] != 0 && !exceptList.contains(column)) {
					res++;
				}
			}
			exceptList.add(row);
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
		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(this.getAdjacencyList());

		this.setAdjacencyList(subList);
		for (Vertex vertex : this.getAdjacencyList().keySet()) {
			list.put(vertex, i++);
		}

		for (Vertex vertex : subList.keySet()) {

			for (Edge edge : subList.get(vertex)) {
				if (subList.containsKey(edge.getDestination())) {
					// cập nhật trọng số cho vertex nguồn và đich của edge vừa mới được thêm
					this.getAdjacencyMatrix()[list.get(vertex)][list.get(edge.getDestination())] = edge.getWeight();
					this.getAdjacencyMatrix()[list.get(edge.getDestination())][list.get(vertex)] = edge.getWeight();
				}
			}
		}
	}

	@Override
	public int[][] getEdges(int rootNode) {
		int[][] res = new int[this.countEdges()][3];
		int sizeMatrix = this.getAdjacencyMatrix().length;
		ArrayList<Integer> exceptList = new ArrayList<Integer>();
		Queue<Integer> nextNode = new LinkedList<Integer>();
		int index = 0;
		for (int column = 0; column < sizeMatrix; column++) {
			if (this.getAdjacencyMatrix()[rootNode][column] != 0) {
				int[] edge = { rootNode, column, this.getAdjacencyMatrix()[rootNode][column] };
				res[index++] = edge;
				nextNode.offer(column);
			}
		}
		exceptList.add(rootNode);

		while (!nextNode.isEmpty()) {
			int currentNode = nextNode.poll();
			for (int column = 0; column < sizeMatrix; column++) {
				if (this.getAdjacencyMatrix()[currentNode][column] != 0 && !exceptList.contains(column)) {
					int[] edge = { currentNode, column, this.getAdjacencyMatrix()[currentNode][column] };
					res[index++] = edge;
					if (!nextNode.contains(column)) {
						nextNode.offer(column);
					}
				}
			}
			exceptList.add(currentNode);
		}

		return res;
	}

	@Override
	public void addEdge(Edge edge) {
		Edge edge1 = new Edge(edge.getDestination(), edge.getSource(), edge.getWeight());
		Map<Vertex, List<Edge>> subList = new TreeMap<Vertex, List<Edge>>(new VertexComParator());
		subList.putAll(this.getAdjacencyList());
		subList.get(edge.getSource()).add(edge);
		subList.get(edge.getDestination()).add(edge1);
		setAdjacencyList(new TreeMap<Vertex, List<Edge>>(new VertexComParator()));
		this.getAdjacencyList().putAll(subList);
		setAdjacencyMatrix();
	}
	
}
