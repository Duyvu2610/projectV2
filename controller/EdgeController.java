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
		int centerSrcX = (int) model.getSource().getLocation().getX() + Vertex.R ;
		int centerSrcY = (int) model.getSource().getLocation().getY() + Vertex.R;
		int centerDesX = (int) model.getDestination().getLocation().getX() + Vertex.R;
		int centerDesY = (int) model.getDestination().getLocation().getY() + Vertex.R;

		int adjacent = (int) Math.abs(model.getSource().getLocation().getX() - model.getDestination().getLocation().getX());
		int opposite = (int) Math.abs(model.getSource().getLocation().getY() - model.getDestination().getLocation().getY());

		int degree  = (int) Math.toDegrees(Math.atan((double) opposite/adjacent)) ;
		int dx = (int) (Vertex.R * Math.cos(Math.toRadians(degree)));
		int dy = (int) (Vertex.R * Math.sin(Math.toRadians(degree)));

		int stSrcX = centerSrcX <= centerDesX ? centerSrcX + dx: centerSrcX - dx  ;
		int stSrcY = centerSrcY <= centerDesY ? centerSrcY + dy: centerSrcY - dy;
		int desDesX = centerSrcX <= centerDesX ? centerDesX - dx : centerDesX + dx ;
		int desDesY = centerSrcY <= centerDesY ? centerDesY - dy: centerDesY + dy;

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
