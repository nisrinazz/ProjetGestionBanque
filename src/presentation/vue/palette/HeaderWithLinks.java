package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class HeaderWithLinks extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    private SectionInformation sectionInformation ;
    private JButton signOutDropDownBtn;
    private LinkedHashMap<String,JLabel> links ;
    JMenuItem signOut ;
    private JPopupMenu signOutDropDownMenu;

    public SectionInformation getSectionInformation() {
        return sectionInformation;
    }


    public JMenuItem getSignOut() {
        return signOut;
    }

    public LinkedHashMap<String, JLabel> getLinks() {
        return links;
    }

    public void initMenuItems(){
        signOutDropDownMenu = new JPopupMenu();
        signOut = new JMenuItem("Sign out");
        signOut.setForeground(new Color(150,0,0));
        signOut.setIcon(new ImageIcon(cl.getResource("icons/signout.png")));
        signOutDropDownMenu.add(signOut);
    }

    public void initButtons(Color bgColor , ImageIcon infoIcon){
        signOutDropDownBtn = new JButton(infoIcon);
        signOutDropDownBtn.setBackground(bgColor);
        signOutDropDownBtn.setBorder(new LineBorder(bgColor, 2, true));
        signOutDropDownBtn.setBorderPainted(false);

    }

    public void initLabels(Font font , Color fgColor, String... labels){
        links = new LinkedHashMap<>();
        List<String> list = new ArrayList<>(Arrays.asList(labels));
        list.forEach(linkName->{
            JLabel link = new JLabel(linkName);
            link.setFont(font);
            link.setForeground(fgColor);
            link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            links.put(linkName,link);
        });
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
        signOutDropDownBtn.addActionListener(click->
        {  signOutDropDownMenu.show(signOutDropDownBtn, 0, signOutDropDownBtn.getHeight());
        });
    }


    public HeaderWithLinks(Color bgColor, Font fontNameInfo, Font fontRoleInfo, Color colorNameInfo, Color colorRoleInfo,ImageIcon infoIcon,Font fontLinks,Color fgColorLinks,String... linksNames){
        initPanels(bgColor,fontNameInfo,fontRoleInfo,colorNameInfo,colorRoleInfo);
        initButtons(bgColor,infoIcon);
        initMenuItems();
        setBackground(bgColor);
        initLabels(fontLinks , fgColorLinks , linksNames);
        setLayout(new BorderLayout());
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
        panel2.add(signOutDropDownBtn);
        add(panel2,BorderLayout.EAST);
        setBorder(new EmptyBorder(20,20,20,20));
        initActions();
    }

}
