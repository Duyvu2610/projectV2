package controller;

import java.awt.Graphics2D;
import java.awt.Point;
import model.Vertex;
import view.VertexView;

public class VertexController {
	private Vertex model;
	private VertexView view;

	public VertexController(Vertex vertex) {
		super();
		this.model = vertex;
		this.view = new VertexView();
	}

	public int getX() {
		return model.getLocation().x;
	}

	public int getY() {
		return model.getLocation().y;
	}

	public String getName() {
		return model.getName();
	}

	public Vertex getModel() {
		return model;
	}

	public void updateView(Graphics2D g) {
		view.drawPoint(g, getModel().getColor(), getX(), getY(), Vertex.R, getName());
	}

	public void move(int x, int y) {
		model.move(x, y);
	}

	public boolean isClick(Point location) {
		return model.isClickAt(location);
	}

}
