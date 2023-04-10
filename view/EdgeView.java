package view;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import model.Edge;

public class EdgeView extends JPanel{
	private Edge model;

    public EdgeView(Edge model) {
		this.model = model;
	}

	public void drawLine(Graphics2D g, Color colorPoint,int stX, int stY,int desX, int desY, String weight) {
		Line2D.Float el = new Line2D.Float(stX, stY, desX, desY);
		g.drawLine(stX, stY, desX, desY);
		
		// set font and color for label
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.setColor(Color.BLACK);

		// get font metrics
		FontMetrics fm = g.getFontMetrics();

		// calculate label position
		int xLabel = (int) (el.getX1() + (el.getX2() - el.getX1()) / 2 - fm.stringWidth(weight) / 2) + 8;
		int yLabel = (int) (el.getY1() + (el.getY2() - el.getY1()) / 2 + fm.getAscent() / 2) - 8;
 
		
		// draw label
		g.drawString(weight, xLabel, yLabel);
		
	}

}
