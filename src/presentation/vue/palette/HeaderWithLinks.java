package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class HeaderWithLinks extends JPanel {
    private JButton menuDropDown ;
    private SectionInformation sectionInformation ;
    private JButton infoDropDownBtn;
    private LinkedHashMap<String,JLabel> links ;

    private JPopupMenu dropDownMenuInfo ;

    public SectionInformation getSectionInformation() {
        return sectionInformation;
    }

    public JButton getMenuDropDown() {
        return menuDropDown;
    }

    public LinkedHashMap<String, JLabel> getLinks() {
        return links;
    }
    public void initButtons(Color bgColor , ImageIcon menuIcon,ImageIcon infoIcon){
        infoDropDownBtn = new JButton(infoIcon);
        menuDropDown = new JButton(menuIcon);
        menuDropDown.setBackground(bgColor);
        menuDropDown.setBorder(new LineBorder(bgColor, 2, true));
        menuDropDown.setBorderPainted(false);
        infoDropDownBtn.setBackground(bgColor);
        infoDropDownBtn.setBorder(new LineBorder(bgColor, 2, true));
        infoDropDownBtn.setBorderPainted(false);
        dropDownMenuInfo = new JPopupMenu();
        JMenuItem option1 = new JMenuItem("Sign out");
        JMenuItem option2 = new JMenuItem("Informations Personnelles");
        dropDownMenuInfo.add(option2);
        dropDownMenuInfo.add(option1);
    }
    public void initLabels(Font font , Color fgColor, String... labels){
        links = new LinkedHashMap<>();
        List<String> list = new ArrayList<>(Arrays.asList(labels));
        list.forEach(linkName->{
            JLabel link = new JLabel(linkName);
            link.setFont(font);
            link.setForeground(fgColor);
            links.put(linkName,link);
        });
        initActions();
    }

    public void initPanels(Color bgColor,Font fontNameInfo,Font fontRoleInfo,Color colorNameInfo,Color colorRoleInfo){
        sectionInformation = new SectionInformation(bgColor,fontNameInfo,fontRoleInfo,colorNameInfo,colorRoleInfo);
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
        infoDropDownBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dropDownMenuInfo.show(infoDropDownBtn, 0, infoDropDownBtn.getHeight());
            }
        });
    }

    public HeaderWithLinks(Color bgColor, Font fontNameInfo, Font fontRoleInfo, Color colorNameInfo, Color colorRoleInfo,ImageIcon menuIcon,ImageIcon infoIcon,Font fontLinks,Color fgColorLinks,String... linksNames){
        initPanels(bgColor,fontNameInfo,fontRoleInfo,colorNameInfo,colorRoleInfo);
        initButtons(bgColor,menuIcon,infoIcon);
        setBackground(bgColor);
        initLabels(fontLinks , fgColorLinks , linksNames);
        setLayout(new BorderLayout());
        add(menuDropDown, BorderLayout.WEST);
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,50,20));
        links.forEach((linkName,link)->{
            panel.add(link);
        });
        add(panel,BorderLayout.CENTER);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(sectionInformation);
        panel2.setBackground(bgColor);
        panel2.add(infoDropDownBtn);
        add(panel2,BorderLayout.EAST);
        setBorder(new EmptyBorder(20,20,20,20));
    }

    public HeaderWithLinks(Color bgColor, Font fontNameInfo, Font fontRoleInfo, Color colorNameInfo, Color colorRoleInfo,ImageIcon menuIcon,ImageIcon infoIcon){
        initPanels(bgColor,fontNameInfo,fontRoleInfo,colorNameInfo,colorRoleInfo);
        initButtons(bgColor,menuIcon,infoIcon);
        setBackground(bgColor);
        setLayout(new BorderLayout());
        add(menuDropDown, BorderLayout.WEST);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(sectionInformation);
        panel2.setBackground(bgColor);
        panel2.add(infoDropDownBtn);
        add(panel2,BorderLayout.EAST);
        setBorder(new EmptyBorder(20,20,20,20));
    }


}
