package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GraphController;
import model.DirectedGraph;
import model.Edge;
import model.UndirectedGraph;
import model.Vertex;
import utils.VertexComParator;

public class FileView extends JPanel {
	private JButton saveFileButton = new JButton("Lưu File");
	private JButton openFileButton = new JButton("Mở File");
	private GraphController controller;
	private File currentFile = null;

	public String getCurrentFile() {
		return currentFile != null ? currentFile.getName() : null;
	}

	public FileView(GraphController controller) {
		this.controller = controller;
		setLayout(new FlowLayout(FlowLayout.LEADING));
		// save file
		saveFileButton.setPreferredSize(new Dimension(100, 50));
		saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// create list node and edges
				String verticesString = "";
				int numNode = 0;
				int totalNode = controller.getGraph().getAdjacencyList().keySet().size();
				for (Vertex vertex : controller.getGraph().getAdjacencyList().keySet()) {
					verticesString += vertex.getName() + " " + (int) vertex.getLocation().getX() + " "
							+ (int) vertex.getLocation().getY() + "\n";
					for (Edge edge : controller.getGraph().getAdjacencyList().get(vertex)) {
						verticesString += edge.getDestination().getName() + " "
								+ (int) edge.getDestination().getLocation().getX() + " "
								+ (int) edge.getDestination().getLocation().getY() + " " + edge.getWeight() + "\n";
					}
					if (numNode++ != totalNode - 1) {
						verticesString += "##########\n";
					}

				}

				// create matrix (String type)
				int[][] matrix = controller.getGraph().getAdjacencyMatrix();
				int sizeMatrix = matrix.length;
				String rowString = "";
				String result = "";

				for (int row = 0; row < sizeMatrix; row++) {
					for (int col = 0; col < sizeMatrix; col++) {
						if (col == sizeMatrix - 1) {
							rowString += String.valueOf(matrix[row][col]);
						} else {
							rowString += String.valueOf(matrix[row][col]) + " ";
						}
					}
					if (row == sizeMatrix - 1) {
						result += rowString;
					} else {
						result += rowString + "\n";
					}
					rowString = "";
				}

				if (currentFile != null) {
					try {
						FileWriter fw = new FileWriter(currentFile);
						fw.write(result);
						fw.close();
						String pathFile = "C:\\FindShortPathApp\\vertexData" + "\\"
								+ currentFile.getName().replaceFirst("[.][^.]+$", "") + "_"
								+ currentFile.getParentFile().getName() + ".txt";
						FileWriter fw1 = new FileWriter(pathFile);
						fw1.write(verticesString);
						fw1.close();
						JOptionPane.showMessageDialog(null, "Lưu lại file thành công", "Lưu file",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else {
					JFileChooser fileChooser = new JFileChooser();
					int chooser = fileChooser.showSaveDialog(null);
					if (chooser == JFileChooser.APPROVE_OPTION) {
						try {
							File vertexFile = new File("C:\\FindShortPathApp\\vertexData");
							String pathFile = vertexFile.getAbsolutePath() + "\\"
									+ fileChooser.getSelectedFile().getName()
									+ "_" + fileChooser.getSelectedFile().getParentFile().getName() + ".txt";
							if (vertexFile.exists()) {
								File file = new File(pathFile);
								file.createNewFile();
								FileWriter fw = new FileWriter(file);
								fw.write(verticesString);
								fw.close();
							} else {
								vertexFile.mkdirs();
								File file = new File(pathFile);
								FileWriter fw = new FileWriter(file);
								fw.write(verticesString);
								fw.close();
							}
							FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".txt");

							fw.write(result);
							fw.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}

		});
		// open file
		openFileButton.setPreferredSize(new Dimension(100, 50));
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int chooser = fileChooser.showOpenDialog(null);
				if (chooser == JFileChooser.APPROVE_OPTION) {
					// Khởi tạo ma trận đã có từ file vừa mở
					String matrixFileLocation = fileChooser.getSelectedFile().toString();
					String vertexFileLocation = "C:\\FindShortPathApp\\vertexData\\"
							+ fileChooser.getSelectedFile().getName().replaceFirst("[.][^.]+$", "") + "_"
							+ fileChooser.getSelectedFile().getParentFile().getName() + ".txt";
					File matrixFile = new File(matrixFileLocation);
					currentFile = matrixFile;
					File vertexFile = new File(vertexFileLocation);

					int size = 0;
					try {
						Scanner scMatrix = new Scanner(matrixFile);
						while (scMatrix.hasNextLine()) {
							size++;
							scMatrix.nextLine();
						}
						scMatrix.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					int[][] matrix = new int[size][size];
					Map<Vertex, List<Edge>> list = new TreeMap<Vertex, List<Edge>>(new VertexComParator());

					// create matrix
					int row = 0;
					try {
						Scanner scMatrix = new Scanner(matrixFile);
						while (scMatrix.hasNextLine()) {
							matrix[row++] = Arrays.stream(scMatrix.nextLine().split(" ")).mapToInt(Integer::parseInt)
									.toArray();
						}
						scMatrix.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					// create list
					boolean isNode = true;
					Vertex sourceNode = new Vertex(null, null);
					Vertex desNode = new Vertex(null, null);
					

					try {
						Scanner scMatrix1 = new Scanner(vertexFile);

						while (scMatrix1.hasNextLine()) {
							String data = scMatrix1.nextLine();
							if (data.equals("##########")) {
								isNode = true;
								continue;
							}
							if (isNode) {
								sourceNode = new Vertex(data.split(" ")[0], new Point(
										Integer.valueOf(data.split(" ")[1]), Integer.valueOf(data.split(" ")[2])));

								list.put(sourceNode, new ArrayList<Edge>());

								isNode = false;
							}
						}
						scMatrix1.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					isNode = true;
					try {
						Scanner scMatrix2 = new Scanner(vertexFile);

						while (scMatrix2.hasNextLine()) {
							String data = scMatrix2.nextLine();
							if (data.equals("##########")) {
								isNode = true;
								continue;
							}
							if (isNode) {
								sourceNode = new Vertex(data.split(" ")[0], new Point(
										Integer.valueOf(data.split(" ")[1]), Integer.valueOf(data.split(" ")[2])));
								for (Vertex v : list.keySet()) {
									if (v.equals(sourceNode)) {
										sourceNode = v;
										break;
									}
								}
								isNode = false;
							} else {
								desNode = new Vertex(data.split(" ")[0], new Point(
										Integer.valueOf(data.split(" ")[1]), Integer.valueOf(data.split(" ")[2])));
								for (Vertex v : list.keySet()) {
									if (v.equals(desNode)) {
										desNode = v;
										break;
									}
								}
								Edge edge = new Edge(sourceNode, desNode, Integer.valueOf(data.split(" ")[3]));
								list.get(sourceNode).add(edge);
							}
						}
						scMatrix2.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					controller.getGraph().setAdjacencyMatrix(matrix);
					if (controller.getGraph().checkUnGraph()) {
						controller.setModel(UndirectedGraph.getInstance());
					} else {
						controller.setModel(DirectedGraph.getInstance());
					}
					controller.getGraph().setGraph(list);
					controller.notifyObservers();

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
