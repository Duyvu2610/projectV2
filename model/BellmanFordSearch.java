package model;

import java.util.*;

public class BellmanFordSearch implements PathFindingStrategy {

	private boolean isNegativeCycle = false;
	private Vertex stV;
	private Vertex edV;

	public Vertex getStV() {
		return stV;
	}

	public void setStV(Vertex stV) {
		this.stV = stV;
	}

	public Vertex getEdV() {
		return edV;
	}

	public void setEdV(Vertex edV) {
		this.edV = edV;
	}

	public boolean isNegativeCycle() {
		return isNegativeCycle;
	}

	public void setNegativeCycle(boolean isNegativeCycle) {
		this.isNegativeCycle = isNegativeCycle;
	}

	@Override
	public Vertex[] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex) {
		setStV(startVertex);
		setEdV(endVertex);
		int[] listParentNode = helper(graph, stV, edV);

		Vertex[] result = null;
		int rootNode = findNodeInMatrix(graph, stV);
		int goalNode = findNodeInMatrix(graph, edV);

		ArrayList<Integer> pathListIntType = new ArrayList<Integer>();
		if (!isNegativeCycle) {
			pathListIntType.add(goalNode);
		}
		int currentNode = listParentNode[goalNode];
		do {
			if (currentNode == -1) {
				break;
			}
			pathListIntType.add(currentNode);

			currentNode = listParentNode[currentNode];
			if (currentNode == rootNode) {
				pathListIntType.add(currentNode);
			}
		} while (currentNode != rootNode && currentNode != -1);
		Collections.reverse(pathListIntType);
		ArrayList<Vertex> pathListVertexType = new ArrayList<Vertex>();
		for (int indexVertex : pathListIntType) {
			pathListVertexType.add(findVertex(graph, indexVertex));
		}
		result = new Vertex[pathListVertexType.size()];
		int index = 0;
		for (Vertex v : pathListVertexType) {
			result[index++] = v;
		}

		if (!isNegativeCycle) {
			if (!result[0].equals(stV) || !result[result.length - 1].equals(edV)) {
				return null;
			}
		}
		return result;
	}

	private Vertex findVertex(Graph graph, int currentIndexNode) {
		int indexNode = 0;
		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			if (indexNode == currentIndexNode) {
				return vertex;
			}
			indexNode++;
		}
		return null;
	}

	private int findNodeInMatrix(Graph graph, Vertex startVertex) {
		int result = 0;
		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			if (vertex.equals(startVertex)) {
				return result;
			}
			result++;
		}
		return -1;
	}

	/*
	 * Ý tưởng của thuật toán:
	 * - Ta thực hiện duyệt n lần, với n là số đỉnh của đồ thị.
	 * - Với mỗi lần duyệt, ta tìm tất cả các cạnh mà đường đi qua cạnh đó sẽ rút
	 * ngắn đường đi ngắn nhất từ đỉnh gốc tới một đỉnh khác.
	 * - Ở lần duyệt thứ n, nếu còn bất kỳ cạnh nào có thể rút ngắn đường đi, điều
	 * đó chứng tỏ đồ thị có chu trình âm, và ta kết thúc thuật toán.
	 */
	private int[] helper(Graph graph, Vertex startVertex, Vertex endVertex) {

		int[][] matrix = graph.getAdjacencyMatrix();
		/*
		 * indexOfRoot is use for find the root node in the graph
		 */
		int indexOfRoot = 0;
		/*
		 * init the root node is the first node in the matrix (or in the graph)
		 */
		int rootNode = 0;
		int size = matrix.length;
		/*
		 * parentOfNode is contain the parent of the node
		 * example parentOfNode = {-1, 0, 0}
		 * explain this result: -1 (has index 0) is represent of node 0 -> the parent of
		 * 0 is -1 (has no parent)
		 * 0 (has index 1) is represent of node 1 -> the parent of 1 is 0
		 * 0 (has index 2) is represent of node 2 -> the parent of 2 is 0
		 */
		int[] parentOfNode = new int[size];
		/*
		 * pastCodeOf node is contain the past cost of node
		 * exmple pastCostOfNode: {0, 5, 3}
		 * 0 (has index 0) is represent of node 0 -> the past cost of node 0 is 0
		 * 5 (has index 1) is represent of node 1 -> the past cost of node 1 is 5
		 * 3 (has index 2) is represent of node 2 -> the past cost of node 2 is 3
		 */
		int[] pastCostOfNode = new int[size];

		// init parent of node (-1 means this node hasn't parent)
		for (int i = 0; i < size; i++) {
			parentOfNode[i] = -1;
		}

		// find root node (must find because the root node hasn't alway the first node
		// in matrix )
		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			if (vertex.equals(startVertex)) {
				rootNode = indexOfRoot;
			}
			indexOfRoot++;
		}

		// initial pathcost of all node (root node is 0, another is MAX_VALUE)
		for (int i = 0; i < size; i++) {
			pastCostOfNode[i] = (i == rootNode) ? 0 : Integer.MAX_VALUE;
		}

		// System.out.println("The graph: \t" );
		// graph.printMatrix();
		// System.out.println();
		// System.out.print("the graph has edges: \t" );
		// graph.printEdge(rootNode);
		// System.out.println();
		// System.out.println("algorithm implementation:" );
		// System.out.println("---------------------------" );
		// System.out.print("\t" );
		// graph.printNode();
		// System.out.println();
		// Map<String, Map<String, Integer>> nodes = new TreeMap<String, Map<String,
		// Integer>>();
		// String s = "\t";

		// loop through all node
		for (int numNode = 1; numNode <= size; numNode++) {
			// s = "\t";
			// loop through all edge the graph has
			for (int[] edge : graph.getEdges(rootNode)) {
				int beginNode = edge[0];
				int endNode = edge[1];
				int weight = edge[2];
				int pathCostBeginNode = pastCostOfNode[beginNode];
				int pathCostEndNode = pastCostOfNode[endNode];
				int pathCostwillChange = pathCostBeginNode == Integer.MAX_VALUE ? Integer.MAX_VALUE
						: pathCostBeginNode + weight;
				if (pathCostwillChange < pathCostEndNode) {
					// check negative cycle exists
					if (numNode == size) {
						System.out.println("Đồ thị có chu trình âm");
						setNegativeCycle(true);
						setStV(findVertex(graph, findVertexNegativeCylce(graph, parentOfNode, beginNode)[0]));
						setEdV(findVertex(graph, findVertexNegativeCylce(graph, parentOfNode,
								beginNode)[findVertexNegativeCylce(graph, parentOfNode, beginNode).length - 1]));
						return parentOfNode;
					}
					pastCostOfNode[endNode] = pathCostwillChange;
					parentOfNode[endNode] = beginNode;
				}
			}

			// for (int i = 0; i < parentOfNode.length; i++) {
			// Vertex v = (Vertex) graph.getAdjacencyList().keySet().toArray()[i];
			// nodes.put(v.getName(), new TreeMap<>());
			// Map<String, Integer> map = new TreeMap<String, Integer>();
			// if (parentOfNode[i] != -1) {
			// Vertex x = (Vertex)
			// graph.getAdjacencyList().keySet().toArray()[parentOfNode[i]];
			// map.put(x.getName(), pastCostOfNode[i]);
			// } else {
			// map.put("-1", pastCostOfNode[i]);
			// }
			// nodes.get(v.getName()).putAll(map);
			// }

			// String str = "";
			// for (String v : nodes.keySet()) {
			// str += "(" + nodes.get(v).keySet().toArray()[0] + ", "
			// +nodes.get(v).get(nodes.get(v).keySet().toArray()[0]) + ")\t";
			// s += str;
			// str = "";
			// }
			// System.out.println(s);

		}

		return parentOfNode;
	}

	private int[] findVertexNegativeCylce(Graph graph, int[] parentOfNode, int beginNode) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(beginNode);
		int currentVertex = parentOfNode[beginNode];

		while (!res.contains(currentVertex)) {
			res.add(currentVertex);
			currentVertex = parentOfNode[currentVertex];
		}
		return res.stream().mapToInt(x -> x).toArray();
	}

}
