package controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Edge;
import model.Graph;
import model.Observer;
import model.Subject;
import model.Vertex;
import view.GraphView;

public class GraphController implements Subject {
	private Graph model;
	private GraphView view;
	private List<Observer> observers;
	private int codeExcute;

	public GraphController(Graph model) {
		this.model = model;
		this.observers = new ArrayList<>();
	}

	public void setView(GraphView view) {
		this.view = view;
	}

	public List<Vertex> getVertices() {
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

	public void setCodeExcute(int code) {
		codeExcute = code;
	}

	public int getCodeExcute() {
		return this.codeExcute;
	}

	public void handleAddVertex(Point location) {
		// Hiển thị hộp thoại yêu cầu nhập tên đỉnh
		String name = JOptionPane.showInputDialog(null, "Enter vertex name:");
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
				String name = JOptionPane.showInputDialog(null, "Enter new vertex name:");
				getVertices().get(i).setName(name);
				notifyObservers();
				break;
			}
		}

	}

	public void handleAddEdge(Point currentClick, Vertex sourcVertex) {

		String weight = JOptionPane.showInputDialog(null, "Enter weight edge:");

		addEdge(sourcVertex, findVertex(currentClick), weight == null ? 1 : Integer.valueOf(weight));
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

	public void drawPath(String[] res) {
		view = new GraphView(this);
		for (Vertex vertex : getVertices()) {
			for (String ver : res) {
				if (vertex.getName().equals(ver)) {
					vertex.setColor(Color.RED);
					view.updateView();

				}
			}
		}

	}

}
