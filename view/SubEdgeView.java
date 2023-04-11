package view;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import model.Edge;
import model.Vertex;

public class SubEdgeView extends JPanel {
	private Vertex model;
	private Point point;

    public SubEdgeView(Vertex model, Point point) {
		this.model = model;
		this.point = point;
	}

	public void drawLine(Graphics2D g, Color colorPoint,int stX, int stY,int desX, int desY) {
	
		g.drawLine(stX, stY, desX, desY);
		
		// set font and color for label
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.setColor(Color.BLACK);

	}

}
