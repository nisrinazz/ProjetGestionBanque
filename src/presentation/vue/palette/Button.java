package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Button extends JButton {


    public Button(String buttonName , Color bgColor , Color fgColor , Font font){
        setText(buttonName);
        setBackground(bgColor);
        setForeground(fgColor);
        setFont(font);
        setBorderPainted(false);
    }

}
