package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class ErrorLabel extends JLabel {

    public ErrorLabel(Font font){
        setText("error");
       setForeground(Color.RED);
       setFont(font);
       setVisible(false);
    }
    public ErrorLabel(){
        setText("error");
        setForeground(Color.RED);
        setFont(new Font("Arial",Font.CENTER_BASELINE,8));
        setVisible(false);
    }
}
