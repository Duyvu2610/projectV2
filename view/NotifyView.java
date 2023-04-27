package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Vertex;

public class NotifyView extends JPanel {
	private String[] notify;
	private JLabel notifyLabel;
	private JLabel pathLabel;
	private static NotifyView instance;

	private NotifyView(String[] notify) {
		this.notify = notify;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 200));
		setBorder(BorderFactory.createTitledBorder("Console"));
		// tao component
		notifyLabel = new JLabel();
		notifyLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 0));
		notifyLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // đặt kích thước font là 16

		pathLabel = new JLabel();
		pathLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 0));
		pathLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // đặt kích thước font là 16

		// add component
		add(notifyLabel, BorderLayout.NORTH);
		add(pathLabel);
	}

	public static NotifyView getInstance() {
		if (instance == null) {
			synchronized (NotifyView.class) {
				if (instance == null) {
					instance = new NotifyView(new String[0]);
				}
			}
		}
		return instance;
	}

	public void updateNotify(Vertex[] s, Vertex st, Vertex ds, int pathCost) {
		if (s != null) {

			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += i != (s.length - 1) ? (s[i].getName() + " → ") : s[i].getName();
			}
			notifyLabel.setText("Đường đi ngắn nhất từ đỉnh " +  st.getName() +  " đến đỉnh " +  ds.getName() + " là: " + result);
			pathLabel.setText("Với tổng chi phí là: " + pathCost);
		} else {
			notifyLabel.setText("Không có đường đi từ " + st.getName() +  " đến " +  ds.getName());
			pathLabel.setText("");
		}
	}
	public void removeNotify(){
		notifyLabel.setText("");
		pathLabel.setText("");
	}
}
