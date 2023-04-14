package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GraphController;
import model.Graph;
import model.Observer;
import model.Vertex;
import util.VertexComParator;

public class MatrixView extends JPanel{
	private GraphController graphController;
	private JLabel label;

	public MatrixView(GraphController graphController) {
		
		this.graphController = graphController;
		Graph graph = graphController.getGraph();
		
		ArrayList<Vertex> vertices = graph.getVertices();
	
		
		int size = vertices.size() + 1;
		setLayout(new GridLayout(size, size));
		setPreferredSize(new Dimension(400, 350));
		setBorder(BorderFactory.createTitledBorder("Adjacency matrix"));
		
		
		// Tạo và thêm các ô vào panel
		for (int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {
				// In hàng đầu tiên và cột đầu tiên là tên của các đỉnh
				if ((i == 0 && j > 0)) label = new JLabel(vertices.get(j-1).getName());
				else if ((i >0  && j == 0)) label = new JLabel(vertices.get(i-1).getName());
				else if ((i ==0  && j == 0)) label = new JLabel("");
				else {
					label = new JLabel(
							(graph.getAdjacencyMatrix()[i-1][j-1] == 0) ? "0" : Integer.toString(graph.getAdjacencyMatrix()[i-1][j-1]));
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
