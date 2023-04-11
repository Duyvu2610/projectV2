package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.EdgeController;
import controller.GraphController;
import controller.SubEdgeController;
import controller.VertexController;
import model.Edge;
import model.Graph;
import model.Observer;
import model.Vertex;

public class GraphView extends JPanel implements Observer {
	private List<VertexController> vertexs;
	private List<EdgeController> edges;
	private GraphController controller;
	private VertexController vertexController;
	private EdgeController edgeController;
	private SubEdgeController subEdgeController;

	public GraphView(GraphController controller) {
		this.controller = controller;
		this.vertexs = new ArrayList<VertexController>();
		this.edges = new ArrayList<EdgeController>();

		this.controller.registerObserver(this);

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		// Thiết lập layout cho MainPanel là null để có thể tự do vị trí của childPanel
		setLayout(null);

		// Tạo một đối tượng MouseAdapter để xử lý sự kiện click chuột
		MouseAdapter mouseAdapter = new MouseAdapter() {
			// Khởi tạo các biến
			boolean dragging = false;
			Vertex currentVertex;
			Vertex prevVertex;
			Point currentClick;
			Point prevPt;
			int beginComp = 0;

			@Override
			public void mouseClicked(MouseEvent e) {

				super.mouseClicked(e);
				// lấy tọa độ của chuột
				currentClick = new Point(e.getX(), e.getY());
				// Nếu click vào nút thêm đỉnh mới được thêm
				if (controller.getCodeExcute() == 1) {
					controller.handleAddVertex(currentClick);
					revalidate();
					repaint();
				} else if (controller.getCodeExcute() == 2) {
					controller.handleRemoveVertex(currentClick);
					revalidate();
					repaint();
				} else if (controller.getCodeExcute() == 3) {

					beginComp++;
					if (beginComp == 1) {
						prevVertex = controller.findVertex(currentClick);
						subEdgeController = new SubEdgeController(null, null);
						subEdgeController.setModel(prevVertex);

					} else if (beginComp == 2) {
						currentVertex = controller.findVertex(currentClick);
						if (currentVertex != null) {
							controller.handleAddEdge(currentClick, prevVertex);
						}
						subEdgeController = null;
						beginComp = 0;
						revalidate();
						repaint();
					}

				} else if (controller.getCodeExcute() == 4) {

					controller.renameVertex(currentClick);
					revalidate();
					repaint();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				currentClick = e.getPoint();
				if (controller.getCodeExcute() == 6) {
					for (int i = 0; i < vertexs.size(); i++) {
						if (vertexs.get(i).isClick(currentClick)) {

							currentVertex = vertexs.get(i).getModel();
							dragging = true;
							prevPt = e.getPoint();
						}
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				currentClick = e.getPoint();
				if (dragging && controller.getCodeExcute() == 6 && currentVertex != null) {
					int deltaX = (int) (currentClick.getX() - prevPt.getX());
					int deltaY = (int) (currentClick.getY() - prevPt.getY());
					currentVertex.move(deltaX, deltaY);
					prevPt = currentClick;
					revalidate();
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				currentClick = e.getPoint();
				if (controller.getCodeExcute() == 3) {
					if (beginComp == 1) {
						// System.out.println(subEdgeController);
						subEdgeController.setPoint(currentClick);
						revalidate();
						repaint();
					}
				}

			}

			//
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (controller.getCodeExcute() == 6) {
					dragging = false;
					// currentVertex = null;

				}

			}

		};
		// Thêm đối tượng MouseAdapter vào JPanel
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		for (Vertex vertex : controller.getVertices()) {
			vertexController = new VertexController(vertex);
			vertexController.updateView(g2d, Color.GREEN);
			vertexs.add(vertexController);
		}

		Map<Vertex, List<Edge>> subList = new LinkedHashMap<Vertex, List<Edge>>(
				controller.getGraph().getAdjacencyList());

		for (Vertex vertex : subList.keySet()) {
			for (Edge edge : subList.get(vertex)) {
				edgeController = new EdgeController(edge);

				edgeController.updateView(g2d, Color.BLUE);
				edges.add(edgeController);
			}
		}
		if (subEdgeController != null && subEdgeController.getModel() != null && subEdgeController.getPoint() != null) {
			subEdgeController = new SubEdgeController(subEdgeController.getModel(), subEdgeController.getPoint());
			subEdgeController.updateView(g2d, getBackground());
		}

	}

	@Override
	public void updateGraph(Graph g) {
		revalidate();
		repaint();
	}

}
