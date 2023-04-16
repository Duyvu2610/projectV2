package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mybutton extends JButton {
    private ImageIcon icon;
    private String desc;

    public Mybutton(ImageIcon icon, String desc) {

        this.icon = icon;
        this.desc = desc;
        // Chèn icon vào JButton
        setIcon(icon);
        setToolTipText(desc);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

}
