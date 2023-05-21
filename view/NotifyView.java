package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Vertex;

public class NotifyView extends JPanel {
	private String[] notify;
	private JLabel notifyLabel;
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

		// add component
		add(notifyLabel, BorderLayout.NORTH);
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

	public void updatePathNotify(Vertex[] s, Vertex st, Vertex ds, int pathCost, boolean negativeCycle) {
		if (!negativeCycle) {

			if (s != null) {
				String result = "";
				for (int i = 0; i < s.length; i++) {
					result += i != (s.length - 1) ? (s[i].getName() + " → ") : s[i].getName();
				}
				String text = "<html>" +
						"Đường đi ngắn nhất từ đỉnh " + st.getName() + " đến đỉnh " + ds.getName() + " là: " + result +
						"<br>" +
						"Với tổng chi phí là: " + pathCost +
						"</html>";
				notifyLabel.setText(text);

			} else {
				notifyLabel.setText("Không có đường đi từ " + st.getName() + " đến " + ds.getName());
			}

		} else {
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += i != (s.length - 1) ? (s[i].getName() + " → ") : s[i].getName();
			}
			String text = "<html>" +
					"Đồ thị có chu trình âm là: " + result +
					"</html>";
			notifyLabel.setText(text);
		}
	}

	public void removeNotify() {
		notifyLabel.setText("");
	}

	public void updateConnectedNotify(boolean connected) {
		if (connected) {
			notifyLabel.setText("Đồ thị liên thông");
		} else {
			notifyLabel.setText("Đồ thị không liên thông");
		}
	}
}
