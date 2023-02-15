package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderWithTitle extends JPanel {
    JLabel title ;

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void initLabel(Icon icon , String title, Font font, Color fontColor){
        this.title = new JLabel(title);
        this.title.setFont(font);
        this.title.setIcon(icon);
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setForeground(fontColor);
    }
    public HeaderWithTitle(Color backgroundColor, Color fontColor , Icon icon , String title , Font font){
        initLabel(icon,title,font,fontColor);
        setBackground(backgroundColor);
        setBorder(new EmptyBorder(10,20,10,20));
        add(this.title);
    }

    public void initLabel(Icon icon , Font font,Color fontColor){
        this.title = new JLabel();
        this.title.setFont(font);
        this.title.setIcon(icon);
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setForeground(fontColor);
    }
    public HeaderWithTitle(Color backgroundColor, Color fontColor , Icon icon , Font font){
        initLabel(icon,font,fontColor);
        setBackground(backgroundColor);
        setBorder(new EmptyBorder(10,20,10,20));
        add(this.title);
    }
}
