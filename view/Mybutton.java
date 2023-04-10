package view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.GraphController;

public class Mybutton extends JButton {
    private ImageIcon icon;
    private String desc;

    public Mybutton(ImageIcon icon, String desc) {
        
        this.icon = icon;
        this.desc = desc;
        setIcon(icon); //Chèn icon vào JButton       
        setToolTipText(desc);    
//        setPreferredSize(new Dimension(70, 10));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
    }
    
}
