package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.GraphController;
import model.DirectedGraph;
import model.Graph;
import model.Observer;
import model.UndirectedGraph;

public class ChooseTypeView extends JPanel implements Observer, ActionListener {
	private GraphController graphController;
	private JRadioButton undirectedButton;
	private JRadioButton directedButton;

	public ChooseTypeView(GraphController graphController) {
		this.graphController = graphController;
		graphController.registerObserver(this);
		setLayout(new FlowLayout(FlowLayout.LEADING));
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder("Menu"));

		// Thêm hai JRadioButton cho DFS và BFS
		undirectedButton = new JRadioButton("Undirected");
		undirectedButton.setOpaque(false);
		directedButton = new JRadioButton("Directed");
		directedButton.setOpaque(false);
		add(undirectedButton);
		add(directedButton);

		// Thiết lập kích thước cho JRadioButton
		// Dimension radioSize = new Dimension(120, 60);
		// undirectedButton.setPreferredSize(radioSize);
		// directedButton.setPreferredSize(radioSize);

		// Gom nhóm hai JRadioButton lại với nhau
		ButtonGroup group = new ButtonGroup();
		group.add(undirectedButton);
		group.add(directedButton);

		undirectedButton.setSelected(true);
		graphController.setModel(UndirectedGraph.getInstance());

		undirectedButton.addActionListener(this);
		directedButton.addActionListener(this);

	}

	// Ghi đè phương thức paintComponent để vẽ nền trong suốt
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Vẽ màu sắc trong suốt
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void updateGraph(Graph g) {
		if (graphController.getTypeModel() == UndirectedGraph.class) {
			undirectedButton.setSelected(true);
		} else {
			directedButton.setSelected(true);
		}
		repaint(); // Vẽ lại
	}

	public void updateState() {

		if (graphController.getGraph() != null) {
			if (!graphController.getGraph().getAdjacencyList().isEmpty()) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Nếu thay đổi dạng đồ thị thì đồ thị sẽ bị xóa. Bạn có chắc chắn xóa không?", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (confirm == 0) {
					if (undirectedButton.isSelected()) {
						graphController.setModel(UndirectedGraph.getInstance());
					} else {
						graphController.setModel(DirectedGraph.getInstance());
					}
					graphController.removeAllGraph();
					graphController.notifyObservers();
				} else {
					if (undirectedButton.isSelected()) {
						directedButton.setSelected(true);
					} else {
						undirectedButton.setSelected(true);
					}
				}

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateState();
	}
}
