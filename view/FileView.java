package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import controller.GraphController;

public class FileView extends JPanel {
	private JButton saveFileButton = new JButton("Lưu File");
	private JButton openFileButton = new JButton("Mở File");
	private GraphController controller;

	public FileView(GraphController controller) {
		this.controller = controller;
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
		setOpaque(false);
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
