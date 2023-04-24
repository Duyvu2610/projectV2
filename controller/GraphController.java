package controller;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import model.Edge;
import model.Graph;
import model.Observer;
import model.PathFindingStrategy;
import model.Subject;
import model.Vertex;
import view.GraphView;

public class GraphController implements Subject {
	private Graph model;
	private GraphView view;
	private List<Observer> observers;
	private int codeExcute;

	public GraphController() {
		this.observers = new ArrayList<>();
	}

	public void setView(GraphView view) {
		this.view = view;
	}

	public ArrayList<Vertex> getVertices() {
		return model.getVertices();
	}

	public void addVertex(Vertex v) {
		model.addVertex(v);
		notifyObservers();
	}

	public void removeVertex(Vertex vertex) {

		model.removeVertex(vertex);
		notifyObservers();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.updateGraph(model);
		}

	}

	public Graph getGraph() {
		return model;
	}

	public Vertex getStartVertex() {
		return model.getStartVertex();
	}

	public Vertex getEndVertex() {
		return model.getEndVertex();
	}

	public void setCodeExcute(int code) {
		codeExcute = code;
	}

	public int getCodeExcute() {
		return this.codeExcute;
	}

	public void handleAddVertex(Point location) {
		// Hiển thị hộp thoại yêu cầu nhập tên đỉnh
		String name = JOptionPane.showInputDialog(null, "Nhập tên đỉnh:");
		if (name != null && !name.isEmpty()) {
			Vertex vertex = new Vertex(name, location);
			addVertex(vertex);

		}
	}

	public void handleRemoveVertex(Point location) {

		for (int i = 0; i < getVertices().size(); i++) {
			if (getVertices().get(i).isClickAt(location)) {
				removeVertex(getVertices().get(i));
				break;
			}

		}
	}

	public void renameVertex(Point currentClick) {
		for (int i = 0; i < getVertices().size(); i++) {
			if (getVertices().get(i).isClickAt(currentClick)) {
				String name = JOptionPane.showInputDialog(null, "Nhập tên đỉnh mới:");
				getVertices().get(i).setName(name);
				notifyObservers();
				break;
			}
		}

	}

	public void handleAddEdge(Point currentClick, Vertex sourcVertex) {

		String weight = JOptionPane.showInputDialog(null, "Nhập trọng số:");

		if (weight != null) {
			addEdge(sourcVertex, findVertex(currentClick), weight.equals("") ? 1 : Integer.valueOf(weight));
		}

	}

	private void addEdge(Vertex sourcVertex, Vertex findVertex, int i) {
		Edge edge = new Edge(sourcVertex, findVertex, i);

		model.addEdge(edge);

		notifyObservers();
	}

	public Vertex findVertex(Vertex that) {
		for (int i = 0; i < getVertices().size(); i++) {
			if (getVertices().get(i).equals(that)) {
				return getVertices().get(i);
			}
		}
		return null;
	}

	public Vertex findVertex(Point that) {
		for (int i = 0; i < getVertices().size(); i++) {
			if (getVertices().get(i).isClickAt(that)) {
				return getVertices().get(i);
			}
		}
		return null;
	}

	public void removeAllGraph() {

		model.removeAll();
		notifyObservers();
	}

	public void drawPath(String[][] res) {
		setDefaultGraph();
		if (res != null) {
			List<Vertex> vertices = new ArrayList<Vertex>();
			List<Edge> edges = new ArrayList<Edge>();
			view = new GraphView(this);

			for (String[] arrsStrings : res) {
				for (Vertex vertex : getVertices()) {
					if (vertex.getName().equals(arrsStrings[0])) {
						vertices.add(vertex);
						break;
					}
				}
			}

			for (int i = 0; i < vertices.size() - 1; i++) {
				int index = i;
				Vertex beginNode = vertices.get(index);
				Vertex endNode = vertices.get(index + 1);
				for (Vertex vertex : vertices) {
					for (Edge edge : model.getAdjacencyList().get(vertex)) {
						if ((edge.getSource().equals(beginNode) && edge.getDestination().equals(endNode))
								|| (edge.getDestination().equals(beginNode) && edge.getSource().equals(endNode))) {
							edges.add(edge);
						}
					}

				}

			}
			// vẽ lại toàn bộ các đỉnh thành màu xanh trước khi chuyển màu đỏ
			getVertices().forEach(vertex -> {
				vertex.setColor(Color.GREEN);
			});
			// vẽ lại toàn bộ các cạnh thành màu đen trước khi chuyển màu đỏ
			getVertices().forEach(vertex -> {
				model.getAdjacencyList().get(vertex).forEach(edge -> {
					edge.setColor(Color.BLACK);
				});
			});

			for (Vertex vertex : vertices) {
				vertex.setColor(Color.RED);
			}

			for (Edge edge : edges) {
				System.out.println(edge);
				edge.setColor(Color.RED);
			}

			view.updateView();
			notifyObservers();

		}

	}

	public void setDefaultGraph() {
		for (Vertex vertex : getVertices()) {
			vertex.setDefaultColor();
			for (Edge edge : model.getAdjacencyList().get(vertex)) {
				edge.setDefaultColor();
			}
		}

	}

	// Chọn đỉnh bắt đầu
	public void setStartVertex(String name) {
		model.getVertices().forEach(e -> {
			if (e.getVertex(name))
				model.setStartVertex(e);
		});
	}

	// Chọn đỉnh kết thúc
	public void setEndVertex(String name) {
		model.getVertices().forEach(e -> {
			if (e.getVertex(name))
				model.setEndVertex(e);
		});
	}

	public String[][] pathFinding() {

		return model.pathFinding();
	}

	public void setPathFindingStrategy(PathFindingStrategy p) {
		model.setPath(p);
	}

	public void setModel(Graph instance) {
		this.model = instance;
	}

	public Class getTypeModel() {
		return this.model != null ? this.model.getClass() : null;
	}

	public int[][] getAdjacencyMatrix() {
		return model.getAdjacencyMatrix();
	}

}
