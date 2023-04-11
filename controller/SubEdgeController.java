package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JOptionPane;

import model.Edge;
import model.Vertex;
import view.EdgeView;
import view.SubEdgeView;
import view.VertexView;

public class SubEdgeController {
	private Vertex model;
	private Point point;
	private SubEdgeView view;

	public SubEdgeController(Vertex edge, Point point) {
		super();
		this.model = edge;
		this.point = point;
		this.view = new SubEdgeView(model, point);
	}
	
	
	public Vertex getModel() {
		return model;
	}


	public void setModel(Vertex model) {
		this.model = model;
	}


	public Point getPoint() {
		return point;
	}


	public void setPoint(Point point) {
		this.point = point;
	}


	@Override
	public String toString() {
		return "SubEdgeController [model=" + model + ", point=" + point + ", view=" + view + "]";
	}


	public SubEdgeView getView() {
		return view;
	}


	public void setView(SubEdgeView view) {
		this.view = view;
	}


	public void updateView(Graphics2D g, Color colorPoint) {
		int stSrcX = (int) model.getLocation().getX() >= (int) point.getX()? (int) model.getLocation().getX()  : (int) model.getLocation().getX() + 2*Vertex.R;
		int stSrcY = (int) model.getLocation().getY() + Vertex.R;
		int desDesX = (int) point.getX();
		int desDesY = (int) point.getY();
		
		view.drawLine(g, colorPoint,stSrcX, stSrcY, desDesX, desDesY);
	}
	

	
//	public Vertex vertexClicked(Point location) {
//		if (isClick(location)) return model;
//		System.out.println("ko nhan vao " + model);
//		return null;
//	}

}
