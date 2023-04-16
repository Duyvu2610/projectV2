package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraPathFindingStrategy implements PathFindingStrategy {

	public String[][] findShortestPath(Graph graph, Vertex startVertex, Vertex endVertex) {
		Map<Vertex, Integer> distances = new HashMap<>();
		Map<Vertex, Vertex> previousVertices = new HashMap<>();
		List<Vertex> unvisitedVertices = new ArrayList<>();
		for (Vertex vertex : graph.getVertices()) {
			if (vertex == startVertex) {
				distances.put(vertex, 0);
			} else {
				distances.put(vertex, Integer.MAX_VALUE);
			}
			previousVertices.put(vertex, null);
			unvisitedVertices.add(vertex);
		}
		while (!unvisitedVertices.isEmpty()) {
			Vertex currentVertex = getClosestVertex(unvisitedVertices, distances);
			if (currentVertex == endVertex) {
				break;
			}
			unvisitedVertices.remove(currentVertex);
			for (Edge edge : graph.getEdges(currentVertex)) {
				Vertex neighbor = getNeighbor(currentVertex, edge);
				int alternateDistance = distances.get(currentVertex) + edge.getWeight();
				if (alternateDistance < distances.get(neighbor)) {
					distances.put(neighbor, alternateDistance);
					previousVertices.put(neighbor, currentVertex);
				}
			}
		}
		List<Vertex> shortestPath = new ArrayList<>();
		Vertex vertex = endVertex;
		while (vertex != null) {
			shortestPath.add(vertex);
			vertex = previousVertices.get(vertex);
		}
		return null;
	}

	private Vertex getClosestVertex(List<Vertex> vertices, Map<Vertex, Integer> distances) {
		Vertex closestVertex = null;
		int shortestDistance = Integer.MAX_VALUE;
		for (Vertex vertex : vertices) {
			int distance = distances.get(vertex);
			if (distance < shortestDistance) {
				closestVertex = vertex;
				shortestDistance = distance;
			}
		}
		return closestVertex;
	}

	private Vertex getNeighbor(Vertex vertex, Edge edge) {
		if (edge.getSource() == vertex) {
			return edge.getDestination();
		} else {
			return edge.getSource();
		}
	}

}
