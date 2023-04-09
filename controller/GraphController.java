package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Graph;
import model.GraphModel;
import model.Observer;
import model.PathFindingStrategy;
import model.Subject;
import model.Vertex;
import view.App;
import view.GraphView;

public class GraphController  implements Subject {
	private GraphModel model;
	private App view;
	private List<Observer> observers;
	private int codeExcute;

	public GraphController(GraphModel model) {
		this.model = model;
		this.observers = new ArrayList<>();
	}

	public void setView(App view) {
		this.view = view;
	}

//	public void setStartVertex(Vertex startVertex) {
//		model.setStartVertex(startVertex);
//	}
//
//	public void setEndVertex(Vertex endVertex) {
//		model.setEndVertex(endVertex);
//	}
//
//	public void setPathFindingStrategy(PathFindingStrategy pathFindingStrategy) {
//		model.setPathFindingStrategy(pathFindingStrategy);
//	}

//	public List<Vertex> getShortestPath() {
//		return model.getShortestPath();
//	}
	public int[][] getMatrix(){
		return model.getMatrix();
	}
	public List<Vertex> getVertices(){
		return model.getVertices();
	}
	public void addVertex(Vertex v) {
		model.addVertex(v);
		notifyObservers();
		updateNotify("Nhập tên của đỉnh");
	}
	public void removeVertex(Vertex vertex) {
		System.out.println("dinh xoa la " + vertex.getName());
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
            observer.updateGraph(model.getGraph());
        }
    
    }
    public Graph getGraph() {
    	return model.getGraph();
    }
    public void updateNotify(String s) {
    	view.updateNotify(s);
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
    		}
		}
    }
//	public Vertex getVertexAt(Point location) {
//		Vertex vertex = null;
//		for (int i = 0; i < getVertices().size(); i++) {
//			if (getVertices().get(i).isClickAt(location)) {
//				vertex = getVertices().get(i);
//    		}
//			
//		}
//		return vertex;
//	}

    
}
