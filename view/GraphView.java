package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JPanel;

import controller.EdgeController;
import controller.GraphController;
import controller.VertexController;
import model.DirectedGraph;
import model.Edge;
import model.Graph;
import model.Observer;
import model.UndirectedGraph;
import model.Vertex;
import utils.VertexComParator;

public class GraphView extends JPanel implements Observer {
	private List<VertexController> vertexs;
	private List<EdgeController> edges;
	private GraphController controller;
	private VertexController vertexController;
	private EdgeController edgeController;
	private Edge subEdge = null;
	Graphics2D g2d;

	public GraphView(GraphController controller) {
		this.controller = controller;
		this.vertexs = new ArrayList<VertexController>();
		this.edges = new ArrayList<EdgeController>();
		this.controller.registerObserver(this);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		// Tạo một đối tượng MouseAdapter để xử lý sự kiện click chuột
		MouseAdapter mouseAdapter = new MouseAdapter() {
			// Khởi tạo các biến
			boolean dragging = false;
			Vertex currentVertex;
			Vertex prevVertex;
			Point currentClick;
			Point prevPt;
			Point firstClick;
			Point lastClick;
			int beginComp = 0;

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				// lấy tọa độ của chuột
				currentClick = new Point(e.getX(), e.getY());
				firstClick = currentClick;
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
						// test
						subEdge = new Edge(prevVertex, null, 1);

					} else if (beginComp == 2) {
						subEdge = null;
						currentVertex = controller.findVertex(currentClick);
						if (currentVertex != null) {
							controller.handleAddEdge(currentClick, prevVertex);
						}
						beginComp = 0;
						updateView();
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
					updateView();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				currentClick = e.getPoint();

				if (controller.getCodeExcute() == 3) {
					if (beginComp == 1) {
						subEdge.setDestination(new Vertex("test", currentClick));
						updateView();
					}
				}

			}

			//
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (controller.getCodeExcute() == 6) {
					dragging = false;
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

		g2d = (Graphics2D) g;
		vertexs.clear();
		for (Vertex vertex : controller.getVertices()) {
			vertexController = new VertexController(vertex);
			vertexController.updateView(g2d);
			vertexs.add(vertexController);
		}

		edges.clear();

		Map<Vertex, List<Edge>> list = new TreeMap<>(new VertexComParator());
		list.putAll(controller.getGraph().getAdjacencyList());
		for (Vertex vertex : list.keySet()) {
			for (Edge edge : list.get(vertex)) {
				edgeController = new EdgeController(edge);
				edgeController.updateView(g2d, controller.getTypeModel());
				edges.add(edgeController);
			}
		}

		if (subEdge != null && controller.getTypeModel() == UndirectedGraph.class) {
			EdgeController test = new EdgeController(subEdge);
			int stSrcX = (int) test.getModel().getSource().getLocation().getX() >= (int) test.getModel()
					.getDestination().getLocation().getX() ? (int) test.getModel().getSource().getLocation().getX()
							: (int) test.getModel().getSource().getLocation().getX() + 2 * Vertex.R;
			int stSrcY = (int) test.getModel().getSource().getLocation().getY() + Vertex.R;

			test.drawLine(g2d, Color.GREEN, stSrcX, stSrcY,
					(int) test.getModel().getDestination().getLocation().getX(),
					(int) test.getModel().getDestination().getLocation().getY(), "");
		}

		if (subEdge != null && controller.getTypeModel() == DirectedGraph.class) {
			EdgeController test = new EdgeController(subEdge);
			int stSrcX = (int) test.getModel().getSource().getLocation().getX() >= (int) test.getModel()
					.getDestination().getLocation().getX() ? (int) test.getModel().getSource().getLocation().getX()
							: (int) test.getModel().getSource().getLocation().getX() + 2 * Vertex.R;
			int stSrcY = (int) test.getModel().getSource().getLocation().getY() + Vertex.R;

			int desDesX = test.getModel().getDestination().getLocation().x;
			int desDesY = test.getModel().getDestination().getLocation().y;
			double angle = Math.atan2(desDesY - stSrcY, desDesX - stSrcX);
			int length = 15;
			int arrowX1 = desDesX - (int) (length * Math.cos(angle - Math.PI / 6));
			int arrowY1 = desDesY - (int) (length * Math.sin(angle - Math.PI / 6));
			int arrowX2 = desDesX - (int) (length * Math.cos(angle + Math.PI / 6));
			int arrowY2 = desDesY - (int) (length * Math.sin(angle + Math.PI / 6));

			test.drawLine(g2d, Color.GREEN, stSrcX, stSrcY,
					(int) desDesX,
					(int) desDesY,arrowX1, arrowY1, arrowX2, arrowY2, "");
		}

	}

	@Override
	public void updateGraph(Graph g) {
		revalidate();
		repaint();
	}

	public void updateView() {
		revalidate();
		repaint();
	}

}
