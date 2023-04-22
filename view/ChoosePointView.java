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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GraphController;
import model.Graph;
import model.Observer;
import model.Vertex;

public class ChoosePointView extends JPanel implements Observer{
	private JComboBox<String> startComboBox;
    private JComboBox<String> endComboBox;
	private GraphController graphController;

	public ChoosePointView(GraphController graphController) {
        this.graphController = graphController;
		graphController.registerObserver(this);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder("Menu"));

        // khởi tạo các JComboBox với danh sách đỉnh ban đầu
        startComboBox = new JComboBox<>();
        endComboBox = new JComboBox<>();
        updateItems();
		// Tạo 2 JLabel
		JLabel startLabel = new JLabel("Start Point: ");
		JLabel endLabel = new JLabel("End Point: ");

        startComboBox.setPreferredSize(new Dimension(100, 40));
        endComboBox.setPreferredSize(new Dimension(100, 40));

         // Thêm JLabel và JComboBox vào JPanel
    	add(startLabel);
    	add(startComboBox);
    	add(endLabel);
    	add(endComboBox);

		// Xử lí khi user chọn 1 đỉnh bắt đầu hoặc kết thúc
		startComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedStartVertex = (String) startComboBox.getSelectedItem();
				// Xử lý khi người dùng chọn đỉnh xuất phát
				graphController.setStartVertex(selectedStartVertex);
			}
		});
		
		endComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedEndVertex = (String) endComboBox.getSelectedItem();
				// Xử lý khi người dùng chọn đỉnh kết thúc
				graphController.setEndVertex(selectedEndVertex);

			}
		});
		
	}

	// Ghi đè phương thức paintComponent để vẽ nền trong suốt
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Vẽ màu sắc trong suốt
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	// Cập nhật danh sách đỉnh trong JComboBox
    public void updateItems() {
        List<Vertex> vertices = graphController.getVertices();
        startComboBox.removeAllItems();
        endComboBox.removeAllItems();
        for (Vertex vertex : vertices) {
            startComboBox.addItem(vertex.getName());
            endComboBox.addItem(vertex.getName());
        }
    }

	@Override
	public void updateGraph(Graph g) {
		updateItems(); // Cập nhật danh sách đỉnh trong JComboBox
        repaint(); // Vẽ lại
	}
}
