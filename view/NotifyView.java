package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NotifyView extends JPanel {
	private String notify;
	private JLabel notifyLabel;

	public NotifyView(String notify) {
		this.notify = notify;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 200));
		setBorder(BorderFactory.createTitledBorder("Notify"));
		// tao component
		notifyLabel = new JLabel(notify);
		notifyLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 0));


		// add component
		add(notifyLabel);
	}
	public void updateNotify(String s) {
		notifyLabel.setText(s);
	}
}
