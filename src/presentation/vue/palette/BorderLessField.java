package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BorderLessField extends JTextField {

    public BorderLessField(Color color){
        setBackground(color);
        setBorder(new LineBorder(color, 2, true));
    }
}
