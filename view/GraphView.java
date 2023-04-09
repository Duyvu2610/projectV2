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
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GraphController;
import controller.VertexController;
import model.Vertex;

public class GraphView extends JPanel {
	private List<VertexController> vertexs;
	private GraphController controller;
	private VertexController vertexController;

	public GraphView(GraphController controller) {
		this.controller = controller;
		this.vertexs = new ArrayList<VertexController>();

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		// Thiết lập layout cho MainPanel là null để có thể tự do vị trí của childPanel
		setLayout(null);

		// Tạo một đối tượng MouseAdapter để xử lý sự kiện click chuột
		MouseAdapter mouseAdapter = new MouseAdapter() {
			// Khởi tạo các biến
			 boolean dragging = false;
			 Vertex currentVertex;
			Point currentClick;
			Point prevPt;

			@Override
			public void mouseClicked(MouseEvent e) {
				
				super.mouseClicked(e);
				// lấy tọa độ của chuột
				currentClick = new Point(e.getX(), e.getY());
				// Nếu click vào nút thêm đỉnh mới được thêm
				if (controller.getCodeExcute() == 1) {
					controller.handleAddVertex(currentClick);
					repaint();
				} else if (controller.getCodeExcute() == 2) {
					controller.handleRemoveVertex(currentClick);
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
		        if (dragging && controller.getCodeExcute() == 6 && currentVertex != null) {
					
		        	currentClick = e.getPoint();
		        	int deltaX = (int)(currentClick.getX() - prevPt.getX());
		        	int deltaY = (int)(currentClick.getY() - prevPt.getY());
		        	currentVertex.move(deltaX, deltaY);
		        	prevPt = currentClick;
		            revalidate();
		            repaint();
		        }
		    }
//
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	super.mouseReleased(e);
		    	if (controller.getCodeExcute() == 6) {
		    		System.out.println(currentVertex);
			        dragging = false;
//			        currentVertex = null;
			        revalidate();
			        repaint();
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
	}



}
