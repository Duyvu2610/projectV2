package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Graph;
import model.Vertex;

public class MatrixView extends JPanel{
	private Graph graph;
	private int size;
	private JLabel label;

	public MatrixView(Graph graph) {
		this.graph = graph;
		ArrayList<Vertex> vertices = graph.getVertices();
		int size = vertices.size() + 1;
		setLayout(new GridLayout(size, size));
		setPreferredSize(new Dimension(400, 350));
		setBorder(BorderFactory.createTitledBorder("Adjacency matrix"));
		int[][] matrix1 = new int[size][size];
		// Tạo và thêm các ô vào panel
		for (int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {
				// In hàng đầu tiên và cột đầu tiên là tên của các đỉnh
				if ((i == 0 && j > 0)) label = new JLabel(vertices.get(j-1).getName());
				else if ((i >0  && j == 0)) label = new JLabel(vertices.get(i-1).getName());
				else {
					label = new JLabel(
							(matrix1[i][j] == 0) ? "" : Integer.toString(matrix1[i][j]));
				}
				
				JPanel cell = new JPanel(new BorderLayout());
				cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				label.setHorizontalAlignment(JLabel.CENTER);
				cell.add(label, BorderLayout.CENTER);
				add(cell);
			}
		}
	}
}
