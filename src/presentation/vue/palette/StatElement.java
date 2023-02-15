package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatElement extends JPanel{
    JLabel icon ;
    JLabel title ;
    JLabel info ;
    int cornerRadius ;
    Color bgColor ;
    public JLabel getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info.setText(info);
    }
    public void initLabels(ImageIcon icon, String title , Color colorTitle , Color colorInfo , Font fontTitle , Font fontInfo ){
        this.icon = new JLabel(icon);
        this.title = new JLabel(title);
        this.info = new JLabel();
        this.title.setFont(fontTitle);
        this.info.setFont(fontInfo);
        this.info.setForeground(colorInfo);
        this.title.setForeground(colorTitle);
        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.info.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public StatElement(Color bgColor , int cornerRadius , ImageIcon icon, String title , Color colorTitle , Color colorInfo , Font fontTitle , Font fontInfo){
        this.bgColor = bgColor;
        this.cornerRadius = cornerRadius;
        initLabels(icon,title,colorTitle,colorInfo,fontTitle,fontInfo);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(this.title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.info);
        add(this.icon,BorderLayout.WEST);
        add(panel,BorderLayout.EAST);
        setBorder(new EmptyBorder(20,20,20,20));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(bgColor);
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}
