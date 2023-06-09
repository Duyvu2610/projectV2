package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GraphController;
import controller.NotifyController;
import model.Graph;
import model.Observer;

public class App extends JFrame implements Observer {

	private SearchMenuView menuView;
	private FileView fileView;
	private MatrixView matrixView;
	private FeatureView featureView;
	private GraphView graphView;
	private NotifyView notifyView;
	private GraphController graphController;
	private NotifyController notifyController;
	private ChoosePointView choosePointView;
	private ChooseTypeView chooseTypeView;
	private JPanel leftCol;
	private JPanel rightCol;

	public App() {
		this.graphController = new GraphController();
		this.chooseTypeView = new ChooseTypeView(graphController);
		this.notifyController = new NotifyController();
		this.menuView = new SearchMenuView(graphController);
		this.fileView = new FileView(graphController);
		this.matrixView = new MatrixView(graphController);
		this.featureView = new FeatureView(graphController);
		this.graphView = new GraphView(graphController);
		this.notifyView = NotifyView.getInstance();
		this.choosePointView = new ChoosePointView(graphController);
		graphController.registerObserver(this);
		init();
	}

	private void init() {
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("Tìm đường đi ngắn nhất");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel app = new JPanel(new BorderLayout()) {
			// @Override
			// protected void paintComponent(Graphics g) {
			// super.paintComponent(g);
			// Graphics2D g2d = (Graphics2D) g;
			// int width = getWidth();
			// int height = getHeight();
			// Random random = new Random();
			// int r = random.nextInt(256);
			// int green = random.nextInt(256);
			// int b = random.nextInt(256);
			// Color color1 = new Color(r, green, b);
			// r = random.nextInt(256);
			// green = random.nextInt(256);
			// b = random.nextInt(256);
			// Color color2 = new Color(r,green,b);
			// GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
			// g2d.setPaint(gp);
			// g2d.fillRect(0, 0, width, height);
			// }
		};
		leftCol = leftCol();
		leftCol.setOpaque(false);
		app.setPreferredSize(new Dimension(1200, 700));
		app.add(leftCol, BorderLayout.WEST);
		rightCol = rightCol();
		app.add(rightCol);
		app.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// Override the paint method of the main JPanel to add a gradient background
		// Start a timer to update the background colors every 2 seconds
		// Timer timer = new Timer(2000, new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// app.repaint();
		// }
		// });
		// timer.start();
		add(app);
		setVisible(true);
	}

	public JPanel leftCol() {
		JPanel leftJPanel = new JPanel();
		leftJPanel.setLayout(new BoxLayout(leftJPanel, BoxLayout.Y_AXIS));
		// choose vertex
		chooseTypeView.setPreferredSize(new Dimension(400, 60));
		chooseTypeView.setBorder(BorderFactory.createTitledBorder("Type"));
		// Menu
		menuView.setPreferredSize(new Dimension(400, 85));
		// File
		fileView.setPreferredSize(new Dimension(400, 85));
		fileView.setBorder(BorderFactory.createTitledBorder("File"));
		// choose vertex
		choosePointView.setPreferredSize(new Dimension(400, 75));
		choosePointView.setBorder(BorderFactory.createTitledBorder("Vertex"));

		//
		leftJPanel.setPreferredSize(new Dimension(400, 700));

		// add component
		leftJPanel.add(chooseTypeView);
		leftJPanel.add(choosePointView);
		leftJPanel.add(menuView);
		leftJPanel.add(fileView);
		leftJPanel.add(matrixView);

		return leftJPanel;
	}

	public JPanel rightCol() {
		JPanel rightJPanel = new JPanel(new BorderLayout());
		rightJPanel.setOpaque(false);
		// Screen
		JPanel screen = new JPanel(new BorderLayout(5, 0));
		// node feature
		// show graph
		screen.setPreferredSize(new Dimension((int) rightJPanel.getPreferredSize().getWidth(), 500));
		screen.add(graphView, BorderLayout.CENTER);
		screen.add(featureView, BorderLayout.WEST);
		// Notify
		rightJPanel.setBorder(BorderFactory
				.createTitledBorder(this.fileView.getCurrentFile() != null ? this.fileView.getCurrentFile() : "Graph"));

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
		leftCol.remove(matrixView);
		matrixView = new MatrixView(graphController);
		leftCol.add(matrixView, BorderLayout.SOUTH);
		rightCol.setBorder(BorderFactory
				.createTitledBorder(this.fileView.getCurrentFile() != null ? this.fileView.getCurrentFile() : "Graph"));
	}

}
