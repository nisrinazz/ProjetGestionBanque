package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class SideMenuPanel extends JPanel {
    private LinkedHashMap<String,JLabel> links ;
    public LinkedHashMap<String, JLabel> getLinks() {
        return links;
    }


    public void initLabels(Font font , Color fgColor, String... labels){
        links = new LinkedHashMap<>();
        List<String> list = new ArrayList<>(Arrays.asList(labels));
        list.forEach(linkName->{
            JLabel link = new JLabel(linkName);
            link.setFont(font);
            link.setForeground(fgColor);
            link.setAlignmentX(Component.CENTER_ALIGNMENT);
            links.put(linkName,link);
        });
        initActions();
    }

    public void initActions(){
        links.forEach((linkName,link)->
        { Color color = link.getForeground();
            link.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    link.setForeground(Color.GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    link.setForeground(color);
                }
            });
        });
    }
    public SideMenuPanel(Color bgColor , Color fgColor , Font font , String... Links){
        initLabels(font , fgColor , Links);
        setBackground(bgColor);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(15, 40, 0, 40));
        links.forEach((linkName,link)->{
            add(link);
            add(Box.createRigidArea(new Dimension(0, 30)));
        });


    }
}
