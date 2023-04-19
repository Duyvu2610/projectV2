package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GraphController;
import controller.NotifyController;
import model.BellmanFordSearch;
import model.Graph;
import model.Observer;
import model.PathFindingStrategy;

public class SearchMenuView extends JPanel{
	ArrayList<String> data;
	GraphController graphController;
	PathFindingStrategy currentFind;

	public SearchMenuView(GraphController graphController) {
		this.graphController = graphController;
		this.data = new ArrayList<>();
		data.add("Bellman");
		setLayout(new FlowLayout(FlowLayout.LEADING));
		for (int i = 0; i < data.size(); i++) {
			int index = i;
			JButton button = new JButton(data.get(index));
			button.setPreferredSize(new Dimension(120, 60));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					NotifyController notifyController = new NotifyController();
					switch (e.getActionCommand().toString()) {
						case "Bellman":
							currentFind = new BellmanFordSearch();
							graphController.setPathFindingStrategy(currentFind);
							break;
						default:
							break;
					}
					// String[][] res = data.get(index).findShortestPath(graphController.getGraph(),
					// 		graphController.getVertices().get(0), graphController.getVertices()
					// 				.get(graphController.getGraph().getAdjacencyMatrix().length - 1));
					
					String[][] res = graphController.pathFinding(graphController.getGraph(),graphController.getVertices().get(0),
					graphController.getVertices().get(graphController.getGraph().getAdjacencyMatrix().length - 1));
					
					notifyController.setNotify(res);
					graphController.drawPath(res);
					graphController.notifyObservers();
				}
			});

			add(button);
		}
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder("Menu"));
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
