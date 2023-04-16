package controller;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Edge;
import model.Vertex;
import view.EdgeView;

public class EdgeController {
	private Edge model;
	private EdgeView view;

	public EdgeController(Edge edge) {
		super();
		this.model = edge;
		this.view = new EdgeView(model);
	}

	public Edge getModel() {
		return model;
	}

	public void setModel(Edge model) {
		this.model = model;
	}

	public EdgeView getView() {
		return view;
	}

	public void setView(EdgeView view) {
		this.view = view;
	}

	public void updateView(Graphics2D g, Color colorPoint) {
		int stSrcX = (int) model.getSource().getLocation().getX() >= (int) model.getDestination().getLocation().getX()
				? (int) model.getSource().getLocation().getX()
				: (int) model.getSource().getLocation().getX() + 2 * Vertex.R;
		int stSrcY = (int) model.getSource().getLocation().getY() + Vertex.R;
		int desDesX = (int) model.getDestination().getLocation().getX() >= (int) model.getSource().getLocation().getX()
				? (int) model.getDestination().getLocation().getX()
				: (int) model.getDestination().getLocation().getX() + 2 * Vertex.R;
		int desDesY = (int) model.getDestination().getLocation().getY() + Vertex.R;

		view.drawLine(g, colorPoint, stSrcX, stSrcY, desDesX, desDesY, String.valueOf(model.getWeight()));
	}

	public void drawLine(Graphics2D g, Color colorPoint, int stX, int stY, int desX, int desY, String weight) {
		view.drawLine(g, colorPoint, stX, stY, desX, desY, weight);
	}

}
