package presentation.vue.palette;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SearchButton extends JButton {

    public SearchButton(ImageIcon icon , Color bgColor){
        setIcon(icon);
        setBackground(bgColor);
        setBorderPainted(false);
    }
}
