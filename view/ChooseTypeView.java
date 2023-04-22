package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.GraphController;
import model.DirectedGraph;
import model.Graph;
import model.Observer;
import model.UndirectedGraph;
import model.Vertex;

public class ChooseTypeView extends JPanel implements Observer{
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
		// hàm sử dụng để xem thử button nào đang được chọn
		updateState();
		// Sử dụng interface ItemListener và phương thức addItemListener() để thêm sự kiện khi JRadioButton được chọn
		ItemListener itemListener = new ItemListener() {
		@Override
			public void itemStateChanged(ItemEvent e) {
				updateState();
			}
		};
		undirectedButton.addItemListener(itemListener);
		directedButton.addItemListener(itemListener);
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
        repaint(); // Vẽ lại
	}

	public void updateState(){
		if (undirectedButton.isSelected()) {
			graphController.setModel(UndirectedGraph.getInstance());
		} else {
			graphController.setModel(DirectedGraph.getInstance());
		}
	}
}
