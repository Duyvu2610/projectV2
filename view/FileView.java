package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import controller.GraphController;

public class FileView extends JPanel {
	private JButton saveFileButton = new JButton("Lưu File");
	private JButton openFileButton = new JButton("Mở File");
	private GraphController controller;

	public FileView(GraphController controller) {
		this.controller = controller;
		setLayout(new FlowLayout(FlowLayout.LEADING));
		// save file
		saveFileButton.setPreferredSize(new Dimension(120, 60));
		saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// create matrix (String type)
				int[][] matrix = controller.getGraph().getAdjacencyMatrix();
				int sizeMatrix = matrix.length;
				String rowString = "";
				String result = "";

				for (int row = 0; row < sizeMatrix; row++) {
					for (int col = 0; col < sizeMatrix; col++) {
						if (col == sizeMatrix-1) {
							rowString += String.valueOf(matrix[row][col]);
						} else {
							rowString += String.valueOf(matrix[row][col]) + " ";
						}
					}
					if (row == sizeMatrix-1) {
						result += rowString;
					} else {
						result += rowString +"\n";
					}
					rowString = "";
				}

				JFileChooser fileChooser = new JFileChooser();
				int chooser = fileChooser.showSaveDialog(null);
				if (chooser == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".txt");
						fw.write(result);
						fw.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		// open file
		openFileButton.setPreferredSize(new Dimension(120, 60));
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int chooser = fileChooser.showOpenDialog(null);
				if (chooser == JFileChooser.APPROVE_OPTION) {
					// Khởi tạo ma trận đã có từ file vừa mở
				}
			}
		});

		add(openFileButton);
		add(saveFileButton);
		setBorder(BorderFactory.createTitledBorder("Menu"));
		setOpaque(false);
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
