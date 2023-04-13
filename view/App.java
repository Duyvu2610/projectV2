package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GraphController;
import model.Graph;
import model.GraphModel;
import model.Observer;

public class App extends JFrame implements Observer{
//	private GraphModel graphModel;
//	private GraphController graphController;
//	private GraphView graphView;
//	private MatrixView matrixView;
//	
//	private JPanel leftCol;
//	private NotifyView notify;
	private MenuView menuView;
	private FileView fileView;
	private MatrixView matrixView;
	private FeatureView featureView;
	private GraphView graphView;
	private NotifyView notifyView;
	private GraphController graphController;
	private JPanel leftCol;




	public App(){
		this.graphController = new GraphController(new Graph());
		this.menuView = new MenuView();
		this.fileView = new FileView();
		this.matrixView = new MatrixView(graphController);
		this.featureView = new FeatureView(graphController);
		this.graphView = new GraphView(graphController);
		this.notifyView = new NotifyView(getName());
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
		menuView.setPreferredSize(new Dimension(400, 200));
		// File
		fileView.setPreferredSize(new Dimension(400, 150));
		fileView.setBorder(BorderFactory.createTitledBorder("File"));


		//
		leftJPanel.setPreferredSize(new Dimension(400, 700));

		// add component
		leftJPanel.add(menuView, BorderLayout.NORTH);
		leftJPanel.add(fileView);
		leftJPanel.add(matrixView, BorderLayout.SOUTH);
		return leftJPanel;
	}

	public JPanel rightCol() {
		JPanel rightJPanel = new JPanel(new BorderLayout());
		// Screen
		JPanel screen = new JPanel(new BorderLayout(5, 0));
		// node feature
		// show graph
		screen.setPreferredSize(new Dimension((int) rightJPanel.getPreferredSize().getWidth(), 500));
		screen.add(graphView, BorderLayout.CENTER);
		screen.add(featureView, BorderLayout.WEST);
		// Notify
		rightJPanel.setBorder(BorderFactory.createTitledBorder(getTitle()));

		// add component
		rightJPanel.add(screen);
		rightJPanel.add(notifyView, BorderLayout.SOUTH);
		return rightJPanel;
	}

	public static void main(String[] args) {
		new App();
	}

	@Override
	public void updateGraph(Graph g) {
		// TODO Auto-generated method stub
		graphController = new GraphController(g);
		leftCol.remove(matrixView);
		matrixView = new MatrixView(graphController);
		leftCol.add(matrixView, BorderLayout.SOUTH);
	}



}
