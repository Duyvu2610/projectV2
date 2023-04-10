package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GraphController;
import model.Graph;
import model.GraphModel;

public class App extends JFrame implements model.Observer {
	private GraphModel graphModel;
	private GraphController graphController;
	private GraphView graphView;
	private MatrixView matrixView;
	
	private JPanel leftCol;
	private NotifyView notify;


	public App(GraphModel graphModel, GraphController graphController) {
		this.graphModel = graphModel;
		this.graphController = graphController;
		graphController.setView(this);
		graphController.registerObserver(this);
		
		init();
	}

	private void init() {
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("FlowLayout Example");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel app = new JPanel(new BorderLayout());
		leftCol = leftCol();
		app.setPreferredSize(new Dimension(1200, 700));
		app.add(leftCol, BorderLayout.WEST);
		app.add(rightCol());
		app.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(app);
		setVisible(true);
	}

	public JPanel leftCol() {
		JPanel leftJPanel = new JPanel();
		leftJPanel.setLayout(new BorderLayout());
		// Menu
		MenuView menu = new MenuView();
		menu.setPreferredSize(new Dimension(400, 200));
		// File
		FileView file = new FileView();

		file.setPreferredSize(new Dimension(400, 150));
		file.setBorder(BorderFactory.createTitledBorder("File"));

		// matrix
		matrixView = new MatrixView(graphController.getGraph());

		//
		leftJPanel.setPreferredSize(new Dimension(400, 700));

		// add component
		leftJPanel.add(menu, BorderLayout.NORTH);
		leftJPanel.add(file);
		leftJPanel.add(matrixView, BorderLayout.SOUTH);
		return leftJPanel;
	}

	public JPanel rightCol() {
		JPanel rightJPanel = new JPanel(new BorderLayout());
		// Screen
		JPanel screen = new JPanel(new BorderLayout(5, 0));
		// node feature
		FeatureView featureView = new FeatureView(graphController);
		// show graph
		graphView = new GraphView(graphController);
		screen.setPreferredSize(new Dimension((int) rightJPanel.getPreferredSize().getWidth(), 500));
		screen.add(graphView, BorderLayout.CENTER);
		screen.add(featureView, BorderLayout.WEST);
		// Notify
		notify = new NotifyView("");
		rightJPanel.setBorder(BorderFactory.createTitledBorder(getTitle()));

		// add component
		rightJPanel.add(screen);
		rightJPanel.add(notify, BorderLayout.SOUTH);
		return rightJPanel;
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		GraphModel gm = new GraphModel(graph);
		GraphController gc1 = new GraphController(gm);
		new App(gm, gc1);
	}

	@Override
	public void updateGraph(Graph g) {
		// TODO Auto-generated method stub
		leftCol.remove(matrixView);
		matrixView = new MatrixView(g);
		leftCol.add(matrixView, BorderLayout.SOUTH);
		leftCol.validate();
		leftCol.repaint();
	}

	public void updateNotify(String s) {
		notify.updateNotify(s);
		
	}


}
