package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.DijkstraPathFindingStrategy;
import model.PathFindingStrategy;

public class FileView extends JPanel {
	private JButton saveFileButton = new JButton("Lưu File");
	private JButton openFileButton = new JButton("Mở File");

	public FileView() {
		setLayout(new FlowLayout(FlowLayout.LEADING));
		saveFileButton.setPreferredSize(new Dimension(120, 60));
		saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});
		// open file
		openFileButton.setPreferredSize(new Dimension(120, 60));
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});

		add(openFileButton);
		add(saveFileButton);
		setBorder(BorderFactory.createTitledBorder("Menu"));
	}
}
