package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import model.Vertex;

public class VertexView extends JPanel {

	public VertexView() {
	}

	public void drawPoint(Graphics2D g, Color colorPoint, int x, int y, int r, String name) {
		Ellipse2D.Float el = new Ellipse2D.Float(x, y, r * 2, r * 2);
		g.setColor(colorPoint);
		g.fill(el);

		// set font and color for label
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.setColor(Color.BLACK);

		// get font metrics
		FontMetrics fm = g.getFontMetrics();

		// calculate label position
		int xLabel = (int) (el.getX() + el.getWidth() / 2 - fm.stringWidth(name) / 2);
		int yLabel = (int) (el.getY() + el.getHeight() / 2 + fm.getAscent() / 2);

		// draw label
		g.drawString(name, xLabel, yLabel);
	}

}
