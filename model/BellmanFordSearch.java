package model;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BellmanFordSearch implements PathFindingStrategy{

	@Override
	public String[] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex) {

		StringBuffer resReverseStr = new StringBuffer("");
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

		
		int[] listParentNode = helper(graph, startVertex, endVertex);
		int[] result = null;
		
		int index = 0;
		if (listParentNode != null) {

			int currentNode = goalNode;
			do {
				resReverseStr.append( currentNode + " " );
				currentNode = listParentNode[currentNode];
				if (currentNode == rootNode) {
					resReverseStr.append( currentNode + " " );
				}
			} while (currentNode != rootNode);

			String[] resStr = resReverseStr.reverse().substring(1).split(" ");
			result = new int[resStr.length];
			for (String character: resStr) {
				result[index++] = Integer.valueOf(character);
			}
		}

		String[] lastRes = null;
		if (result != null) {
			lastRes = new String[result.length];
			Map<Integer, Vertex> list = new TreeMap<Integer, Vertex>();
			int i=0;
			for (Vertex vertex : graph.getAdjacencyList().keySet()) {
				list.put(i++, vertex);
			}
			
			for (int j = 0; j < result.length; j++) {
			
				lastRes[j] = list.get(result[j]).getName();
			}
			
		}
		System.out.println(Arrays.toString(lastRes));
		return lastRes; 
	}
	
	private int[] helper(Graph graph, Vertex startVertex, Vertex endVertex) {
		int[][] matrix = graph.getAdjacencyMatrix();
		int indeOfRoot = 0;
		int rootNode = 0;
		int size = matrix.length;
		int[] result = new int[size];
		int[] pathCostOfNode = new int[size];
		
		// find root node
		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			if (vertex.equals(startVertex)) {
				rootNode = indeOfRoot;
			}
			indeOfRoot++;
		}

		// initial pathcost of all node (root node is 0, another is MAX_VALUE)
		for (int i = 0; i < size; i++) {
			pathCostOfNode[i] = (i == rootNode) ? 0 : Integer.MAX_VALUE;
		}

		// loop through all node
		for (int numNode = 1; numNode <= size; numNode++) {

			// loop through all edge the graph has
			
			for (int[] edge: graph.getEdges()) {
				int beginNode = edge[0];
				int endNode = edge[1];
				int weight = edge[2];
				int pathCostBeginNode = pathCostOfNode[beginNode];
				int pathCostEndNode = pathCostOfNode[endNode];
				int pathCostwillChange = pathCostBeginNode + weight;
				if (pathCostwillChange < pathCostEndNode) {
					// check negative cycle exists
					if (numNode == size) {
						return null;
					}
					pathCostOfNode[endNode] = pathCostwillChange;
					result[endNode] = beginNode;
				}
			}
		}
		
		return result;
	}

}
