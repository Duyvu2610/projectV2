package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GraphController;
import controller.NotifyController;
import model.BellmanFordSearch;
import model.PathFindingStrategy;
import model.Vertex;

public class SearchMenuView extends JPanel {
	ArrayList<String> data;
	GraphController graphController;
	PathFindingStrategy currentFind;

	public SearchMenuView(GraphController graphController) {
		this.graphController = graphController;
		this.data = new ArrayList<>();
		data.add("Bellman");
		data.add("Check connected");
		setLayout(new FlowLayout(FlowLayout.LEADING));
		for (int i = 0; i < data.size(); i++) {
			int index = i;
			JButton button = new JButton(data.get(index));
			button.setPreferredSize(new Dimension(100, 50));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					NotifyController notifyController = new NotifyController();
					switch (e.getActionCommand().toString()) {
						case "Bellman":
							currentFind = new BellmanFordSearch();
							graphController.setPathFindingStrategy(currentFind);
							break;
						default:
							break;
					}

					if (e.getActionCommand().toString().equals("Check connected")) {
						notifyController.setConnectedNotify(graphController.getGraph().isConnected());
						return;
					}
					// nếu như chưa chọn đỉnh bắt đầu và kết thúc thì show message
					if (graphController.getStartVertex() == graphController.getEndVertex()) {
						JOptionPane.showMessageDialog(null, "ĐỈnh bắt đầu và kết thúc trùng nhau vui lòng chọn lại",
								"Thông báo", JOptionPane.ERROR_MESSAGE, null);
					} else {
						Vertex[] res = graphController.pathFinding();
						notifyController.setPathNotify(res, graphController.getStartVertex(),
								graphController.getEndVertex(), graphController.getGraph().pathCostOfFind(res),
								((BellmanFordSearch) currentFind).isNegativeCycle());
						graphController.drawPath(res);
					}
				}
			});
			add(button);
		}
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder("Menu"));
	}

	// Ghi đè phương thức paintComponent để vẽ nền trong suốt
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Vẽ màu sắc trong suốt
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
