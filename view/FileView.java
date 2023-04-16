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
				String rowString = "";
				String result = "";
				int[][] matrix = controller.getGraph().getAdjacencyMatrix();
				int size = matrix.length;
				for (int rowIndex = 0; rowIndex <size; rowIndex++) {
					for (int colIndex = 0; colIndex < size; colIndex++) {
						if (colIndex == size-1) {
							rowString += String.valueOf(matrix[rowIndex][colIndex]);
						} else {
							rowString += String.valueOf(matrix[rowIndex][colIndex]) + " ";
						}
					}
					if (rowIndex == size-1) {
						result += rowString;
					} else {
						result += rowString + "\n";
					}
					rowString = "";
				}

		
				JFileChooser fileChooser = new JFileChooser();
				int chooser = fileChooser.showSaveDialog(null);
				if (chooser == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fileWriter = new FileWriter(fileChooser.getSelectedFile() + ".txt");
						fileWriter.write(result);
						fileWriter.close();

					} catch (IOException e1) {
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
				fileChooser.showOpenDialog(null);
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
