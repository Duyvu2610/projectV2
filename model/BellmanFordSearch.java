package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BellmanFordSearch implements PathFindingStrategy {
	@Override
	public String[][] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex) {
		StringBuffer resReverseStr = new StringBuffer("");
		StringBuffer weightReverseStr = new StringBuffer("");
		int rootNode = 0;
		int goalNode = 0;
		int indexOfRoot = 0;
		int indexOfGoal = 0;

		// find root node and goal node
		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			if (vertex.equals(startVertex)) {
				rootNode = indexOfRoot;
			}
			if (vertex.equals(endVertex)) {
				goalNode = indexOfGoal;
			}
			indexOfRoot++;
			indexOfGoal++;
		}

		int[][] listParentNode = helper(graph, startVertex, endVertex);
		int[][] result = null;

		int index = 0;
		if (listParentNode != null) {

			int currentNode = goalNode;
			
			int currentWeight = listParentNode[currentNode][1];
			
			do {
				resReverseStr.append(currentNode + " ");
				weightReverseStr.append(currentWeight + " ");
				currentNode = listParentNode[currentNode][0];
				currentWeight = listParentNode[currentNode][1];
				if (currentNode == rootNode) {
					resReverseStr.append(currentNode + " ");
					weightReverseStr.append(currentWeight + " ");
					break;
				}
				
			} while (currentNode != rootNode && currentNode != -1);

			String[] resStr = resReverseStr.reverse().substring(1).split(" ");
			String[] weightStr = weightReverseStr.reverse().substring(1).split(" ");
			result = new int[resStr.length][2];
			for (String character : resStr) {
				result[index++][0] = Integer.valueOf(character);
			}
			index =0;
			for (String character : weightStr) {
				result[index++][1] = Integer.valueOf(character);
			}
		}

		String[][] lastRes = null;
		if (result != null) {
			lastRes = new String[result.length][2];
			Map<Integer, Vertex> list = new TreeMap<Integer, Vertex>();
			int i = 0;
			for (Vertex vertex : graph.getAdjacencyList().keySet()) {
				list.put(i++, vertex);
			}

			for (int j = 0; j < result.length; j++) {

				lastRes[j][0] = list.get(result[j][0]).getName();
				lastRes[j][1] = String.valueOf(result[j][1]);
			}

		}
		return lastRes;
	}

	/*
	 *  Ý tưởng của thuật toán: 
	 *   - Ta thực hiện duyệt n lần, với n là số đỉnh của đồ thị.
	 *	 - Với mỗi lần duyệt, ta tìm tất cả các cạnh mà đường đi qua cạnh đó sẽ rút ngắn đường đi ngắn nhất từ đỉnh gốc tới một đỉnh khác.
     * 	 - Ở lần duyệt thứ n, nếu còn bất kỳ cạnh nào có thể rút ngắn đường đi, điều đó chứng tỏ đồ thị có chu trình âm, và ta kết thúc thuật toán.
	 */
	private int[][] helper(Graph graph, Vertex startVertex, Vertex endVertex) {
		if (!graph.isConnected()) {
			System.out.println("Đồ thị không liên thông");
			return null;
		}

		System.out.println("The graph: \t" );
		graph.printMatrix();
		System.out.println();
		System.out.print("the graph has edges: \t" );
		graph.printEdge();
		System.out.println();
		System.out.println("algorithm implementation:" );
		System.out.println("---------------------------" );
		System.out.print("\t" );
		graph.printNode();
		System.out.println();
		Map<String, Map<String, Integer>> nodes = new TreeMap<String, Map<String, Integer>>();
		String s = "\t";
		

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
		 * explain this result: -1 (has index 0) is represent of node 0 -> the parent of 0 is -1 (has no parent) 
		 * 						 0 (has index 1) is represent of node 1  -> the parent of 1 is 0 
		 * 						 0 (has index 2) is represent of node 2 -> the parent of 2 is 0 
		 */
		int[] parentOfNode = new int[size];
		/*
		 * pastCodeOf node is contain the past cost of node
		 * exmple pastCostOfNode: {0, 5, 3}
		 * 						   0 (has index 0) is represent of node 0 ->  the past cost of node 0 is 0
		 * 						   5 (has index 1) is represent of node 1 -> the past cost of node 1 is 5
		 * 						   3 (has index 2) is represent of node 2 -> the past cost of node 2 is 3
		 */
		int[] pastCostOfNode = new int[size];

		/*
		 * result is contain the parent node and tha past cost of node
		 * example result = {{-1,0}, {0, 5}, {0, 2}}
		 * explain this result: {-1,0} (has index 0) is represent of node 0 -> the parent of node 0 is -1 (has no parent) and the past cost of node 0 is 0
		 * 						{0,5} (has index 1) is represent of node 1  -> the parent of node 1 is 0 and the past cost of node 1 is 5
		 * 						{0,3} (has index 2) is represent of node 2 -> the parent of node 2 is 0 and the past cost of node 2 is 3		
		 */
		int[][] result = new int[size][2];

		// init parent of node (-1 means this node hasn't parent)
		for (int i = 0; i < size; i++) {
			parentOfNode[i] = -1;
		}

		// find root node (must find because the root node hasn't alway the first node in matrix )
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
		
		// loop through all node
		for (int numNode = 1; numNode <= size; numNode++) {
			s = "\t";
			// loop through all edge the graph has
			for (int[] edge : graph.getEdges()) {
				int beginNode = edge[0];
				int endNode = edge[1];
				int weight = edge[2];
				int pathCostBeginNode = pastCostOfNode[beginNode];
				int pathCostEndNode = pastCostOfNode[endNode];
				int pathCostwillChange = pathCostBeginNode + weight;
				if (pathCostwillChange < pathCostEndNode) {
					// check negative cycle exists
					if (numNode == size) {
						System.out.println("Đồ thị có chu trình âm");
						return null;
					}
					pastCostOfNode[endNode] = pathCostwillChange;
					parentOfNode[endNode] = beginNode;

				}
			}

			for (int i = 0; i < parentOfNode.length; i++) {
				Vertex v = (Vertex) graph.getAdjacencyList().keySet().toArray()[i];
				nodes.put(v.getName(), new TreeMap<>());
				Map<String, Integer> map = new TreeMap<String, Integer>();
				if (parentOfNode[i] != -1) {
					Vertex x = (Vertex) graph.getAdjacencyList().keySet().toArray()[parentOfNode[i]];
					map.put(x.getName(), pastCostOfNode[i]);
				} else {
					map.put("-1",  pastCostOfNode[i]);
				}
				nodes.get(v.getName()).putAll(map);
			}
			
			String str = "";
			for (String v : nodes.keySet()) {
				str += "(" + nodes.get(v).keySet().toArray()[0] + ", " +nodes.get(v).get(nodes.get(v).keySet().toArray()[0])  + ")\t";
				s += str;
				str = "";
			}
			System.out.println(s);
		}


		for (int row = 0; row < result.length; row++) {
			/*
			 * index of row is represent of node
			 */
			for (int column = 0; column < result[row].length; column++) {
				/*
				 * column has two value
				 * the first value has index 0, it's represent the parent of node
				 * the second value has index 1, it's represent the past code of node
				 * 
				 * in condition of If statement below : the condition in If is represent the first value
				 * 									    the condition in Else is represent the second value
				 */
				if (column == 0) {
					result[row][column] = parentOfNode[row];
				} else {
					result[row][column] = pastCostOfNode[row];
				}
			}
		}
		return result;
	}
	

}
