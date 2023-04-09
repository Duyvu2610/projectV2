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

public class FileView extends JPanel{
	ArrayList<PathFindingStrategy> data;
	public FileView() {
		this.data = new ArrayList<>();
		data.add(new DijkstraPathFindingStrategy());
		setLayout(new FlowLayout(FlowLayout.LEADING));
		for (int i = 0; i < data.size(); i++) {
			int index = i;
			JButton button = new JButton(data.get(index).getClass().getName().substring(6));
			button.setPreferredSize(new Dimension(120, 60));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// show a warning message
//					data.get(index).search();
					System.out.println("hihi");
				}
			});

			add(button);
		}
		setBorder(BorderFactory.createTitledBorder("Menu"));
	}
}
