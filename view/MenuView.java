package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GraphController;
import model.BellmanFordSearch;
import model.DijkstraPathFindingStrategy;
import model.Graph;
import model.Observer;
import model.PathFindingStrategy;
import model.Vertex;

public class MenuView extends JPanel implements Observer{
	ArrayList<PathFindingStrategy> data;
	GraphController graphController;
	public MenuView(GraphController graphController) {
		this.graphController = graphController;
		this.data = new ArrayList<>();
		
		this.graphController.registerObserver(this);
		data.add(new DijkstraPathFindingStrategy());
		data.add(new BellmanFordSearch());
		setLayout(new FlowLayout(FlowLayout.LEADING));
		for (int i = 0; i < data.size(); i++) {
			int index = i;
			JButton button = new JButton(data.get(index).getClass().getName().substring(6));
			button.setPreferredSize(new Dimension(120, 60));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					String[] res = data.get(1).findShortestPath(graphController.getGraph(),
					 graphController.getVertices().get(0), graphController.getVertices().get(graphController.getGraph().getAdjacencyMatrix().length-1));
					System.out.println(res);
				}
			});

			add(button);
		}
		setBorder(BorderFactory.createTitledBorder("Menu"));
	}
	@Override
	public void updateGraph(Graph g) {
		// TODO Auto-generated method stub
		

	}
}
