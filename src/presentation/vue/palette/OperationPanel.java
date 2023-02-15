package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperationPanel extends JPanel {
    JLabel title ;
    JLabel icon;


    public void initLabel(String text , Color fgColor,Font font , ImageIcon icon ){
        title = new JLabel(text);
        this.title.setFont(font);
        this.title.setText(text);
        this.title.setForeground(fgColor);
        this.icon = new JLabel(icon);

    }

    public OperationPanel(Color bgColor , String text , Color fgColor,Font font , ImageIcon icon){
        setBackground(bgColor);
        setLayout(new BorderLayout());
        JPanel gapPanel = new JPanel();
        gapPanel.setBackground(bgColor);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gapPanel.setPreferredSize(new Dimension(30, 0));
        setPreferredSize(new Dimension(200,50));
        initLabel(text,fgColor,font,icon);
        add(title,BorderLayout.WEST);
        add(gapPanel,BorderLayout.CENTER);
        add(this.icon,BorderLayout.EAST);
        setBorder(new EmptyBorder(10,10,10,10));
    }
}
