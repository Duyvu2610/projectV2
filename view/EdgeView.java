package view;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class EdgeView extends JPanel {

	public EdgeView() {
	}
	// vẽ cạnh cho đồ thị vô hướng
	public void drawLine(Graphics2D g, GradientPaint  colorPoint, int stX, int stY, int desX, int desY, String weight) {
		Line2D.Float el = new Line2D.Float(stX, stY, desX, desY);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		// sử dụng phương thức setPaint() để thiết lập GradientPaint
		g.setPaint(colorPoint);

		g.setStroke(new BasicStroke(2));
		g.drawLine(stX, stY, desX, desY);

		// set font and color for label

		// get font metrics
		FontMetrics fm = g.getFontMetrics();

		// calculate label position
		int xLabel = (int) (el.getX1() + (el.getX2() - el.getX1()) / 2 - fm.stringWidth(weight) / 2) + 8;
		int yLabel = (int) (el.getY1() + (el.getY2() - el.getY1()) / 2 + fm.getAscent() / 2) - 8;

		// draw label
		g.setPaint(colorPoint);
		g.drawString(weight, xLabel, yLabel);

	}
	// vẽ cạnh cho đồ thị có hướng
	public void drawLine(Graphics2D g, GradientPaint colorPoint, int stX, int stY, int desX, int desY, int arrowX1,
			int arrowY1, int arrowX2, int arrowY2, String weight) {
		Line2D.Float el = new Line2D.Float(stX, stY, desX, desY);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.setPaint(colorPoint);

		g.setStroke(new BasicStroke(2));
		g.drawLine(stX, stY, desX, desY);

		g.drawLine(desX, desY, arrowX1, arrowY1);
		g.drawLine(desX, desY, arrowX2, arrowY2);

		// get font metrics
		FontMetrics fm = g.getFontMetrics();

		// calculate label position
		int xLabel = (int) (el.getX1() + (el.getX2() - el.getX1()) / 2 - fm.stringWidth(weight) / 2) + 8;
		int yLabel = (int) (el.getY1() + (el.getY2() - el.getY1()) / 2 + fm.getAscent() / 2) - 8;

		// draw label
		g.setPaint(colorPoint);
		g.drawString(weight, xLabel, yLabel);

	}

}
