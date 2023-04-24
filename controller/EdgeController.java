package controller;

import java.awt.Color;
import java.awt.Graphics2D;

import model.DirectedGraph;
import model.Edge;
import model.UndirectedGraph;
import model.Vertex;
import view.EdgeView;

public class EdgeController {
	private Edge model;
	private EdgeView view;

	public EdgeController(Edge edge) {
		super();
		this.model = edge;
		this.view = new EdgeView();
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

	public void updateView(Graphics2D g, Class typeOfGraph) {
		int stSrcX = (int) model.getSource().getLocation().getX() >= (int) model.getDestination().getLocation()
				.getX()
						? (int) model.getSource().getLocation().getX()
						: (int) model.getSource().getLocation().getX() + 2 * Vertex.R;
		int stSrcY = (int) model.getSource().getLocation().getY() + Vertex.R;
		int desDesX = (int) model.getDestination().getLocation().getX() >= (int) model.getSource().getLocation()
				.getX()
						? (int) model.getDestination().getLocation().getX()
						: (int) model.getDestination().getLocation().getX() + 2 * Vertex.R;
		int desDesY = (int) model.getDestination().getLocation().getY() + Vertex.R;

		if (typeOfGraph == UndirectedGraph.class) {
			view.drawLine(g, model.getColor(), stSrcX, stSrcY, desDesX, desDesY, String.valueOf(model.getWeight()));
		} else if (typeOfGraph == DirectedGraph.class) {

			// Draw the arrowhead at the end of the line
			double angle = Math.atan2(desDesY - stSrcY, desDesX - stSrcX);
			int length = 15;
			int arrowX1 = desDesX - (int) (length * Math.cos(angle - Math.PI / 6));
			int arrowY1 = desDesY - (int) (length * Math.sin(angle - Math.PI / 6));
			int arrowX2 = desDesX - (int) (length * Math.cos(angle + Math.PI / 6));
			int arrowY2 = desDesY - (int) (length * Math.sin(angle + Math.PI / 6));
			view.drawLine(g, model.getColor(), stSrcX, stSrcY, desDesX, desDesY, arrowX1, arrowY1, arrowX2, arrowY2,  String.valueOf(model.getWeight()));
		}
	}

	public void drawLine(Graphics2D g, Color colorPoint, int stX, int stY, int desX, int desY, String weight) {
		view.drawLine(g, colorPoint, stX, stY, desX, desY, weight);
	}

	public void drawLine(Graphics2D g, Color colorPoint, int stX, int stY, int desX, int desY,int arrowX1,int arrowY1,int arrowX2,int arrowY2, String weight) {
		view.drawLine(g, colorPoint, stX, stY, desX, desY,arrowX1, arrowY1, arrowX2, arrowY2, weight);
	}
	

}
