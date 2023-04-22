package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.GraphController;
import model.Graph;
import model.Observer;
import model.Vertex;

public class ChooseTypeView extends JPanel implements Observer{
	private GraphController graphController;
	private JRadioButton dfsRadioButton;
private JRadioButton bfsRadioButton;

public ChooseTypeView(GraphController graphController) {
    this.graphController = graphController;
	graphController.registerObserver(this);
    setLayout(new FlowLayout(FlowLayout.LEADING));
    setOpaque(false);
    setBorder(BorderFactory.createTitledBorder("Menu"));

	// Thêm hai JRadioButton cho DFS và BFS
    dfsRadioButton = new JRadioButton("Undirected");
    dfsRadioButton.setOpaque(false);
    bfsRadioButton = new JRadioButton("Directed");
    bfsRadioButton.setOpaque(false);
    add(dfsRadioButton);
    add(bfsRadioButton);

    // Gom nhóm hai JRadioButton lại với nhau
    ButtonGroup group = new ButtonGroup();
    group.add(dfsRadioButton);
    group.add(bfsRadioButton);
    dfsRadioButton.setSelected(true);
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
}
