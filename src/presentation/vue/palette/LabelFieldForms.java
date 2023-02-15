package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class LabelFieldForms extends JLabel {

    public LabelFieldForms(String label , Color color , Font font){
        setText(label);
        setForeground(color);
        setFont(font);
    }
}
