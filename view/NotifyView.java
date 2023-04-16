package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotifyView extends JPanel {
	private String[] notify;
	private JLabel notifyLabel;
	private JLabel pathLabel;
	private static NotifyView instance;

	private NotifyView(String[] notify) {
		this.notify = notify;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 200));
		setBorder(BorderFactory.createTitledBorder("Notify"));
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

	public void updateNotify(String[] s) {
		String result = "";
		for (int i = 0; i < s.length; i++) {
			result += i != (s.length - 1) ? (s[i] + " → ") : s[i];
		}
		notifyLabel.setText("Đường đi ngắn nhất là: " + result);
		pathLabel.setText("Với tổng chi phí là: " + s[s.length - 1]);
	}
}
