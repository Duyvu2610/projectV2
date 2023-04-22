package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GraphController;
import model.Graph;
import model.Vertex;

public class MatrixView extends JPanel {
	private GraphController graphController;
	private JLabel label;

	public MatrixView(GraphController graphController) {
		this.graphController = graphController;
		ArrayList<Vertex> vertices = graphController.getVertices();
		int size = vertices.size() + 1;
		setLayout(new GridLayout(size, size));
		setPreferredSize(new Dimension(400, 350));
		setBorder(BorderFactory.createTitledBorder("Adjacency matrix"));
		setOpaque(false);

		// Tạo và thêm các ô vào panel
		for (int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {
				// In hàng đầu tiên và cột đầu tiên là tên của các đỉnh
				if ((i == 0 && j > 0)) {
					label = new JLabel(vertices.get(j - 1).getName());
					label.setFont(new Font("Arial", Font.PLAIN, 16)); // đặt kích thước font là 16
				} else if ((i > 0 && j == 0)) {
					label = new JLabel(vertices.get(i - 1).getName());
					label.setFont(new Font("Arial", Font.PLAIN, 16)); // đặt kích thước font là 16
				} else if ((i == 0 && j == 0)) {
					label = new JLabel("");
					label.setFont(new Font("Arial", Font.PLAIN, 16)); // đặt kích thước font là 16
				} else {
					label = new JLabel(
							(graphController.getAdjacencyMatrix()[i - 1][j - 1] == 0) ? "0"
									: Integer.toString(graphController.getAdjacencyMatrix()[i - 1][j - 1]));
					label.setFont(new Font("Arial", Font.PLAIN, 16)); // đặt kích thước font là 16
				}

				JPanel cell = new JPanel(new BorderLayout());
				cell.setOpaque(false);
				cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setHorizontalAlignment(JLabel.CENTER);
				cell.add(label, BorderLayout.CENTER);
				add(cell);
			}
		}
	}

	// Ghi đè phương thức paintComponent để vẽ nền trong suốt
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Vẽ màu sắc trong suốt
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
