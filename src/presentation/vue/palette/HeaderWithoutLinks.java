package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderWithoutLinks extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    private JButton sideMenuBtn;
    private SectionInformation sectionInformation ;
    private JButton signOutDropDownBtn;

    private JMenuItem signOut ;

    private JPopupMenu signOutDropDownMenu;


    public JPopupMenu getSignOutDropDownMenu() {
        return signOutDropDownMenu;
    }

    public SectionInformation getSectionInformation() {
        return sectionInformation;
    }

    public JMenuItem getSignOut() {
        return signOut;
    }

    public JButton getSideMenuBtn() {
        return sideMenuBtn;
    }

    public void initMenuItems(){
        signOutDropDownMenu = new JPopupMenu();
        signOut = new JMenuItem("Sign out");
        signOut.setForeground(new Color(150,0,0));
        signOut.setIcon(new ImageIcon(cl.getResource("icons/signout.png")));
        signOutDropDownMenu.add(signOut);
    }

    public void initButtons(Color bgColor , ImageIcon menuIcon, ImageIcon infoIcon){
        signOutDropDownBtn = new JButton(infoIcon);
        sideMenuBtn = new JButton(menuIcon);
        sideMenuBtn.setBackground(bgColor);
        sideMenuBtn.setBorder(new LineBorder(bgColor, 2, true));
        sideMenuBtn.setBorderPainted(false);
        signOutDropDownBtn.setBackground(bgColor);
        signOutDropDownBtn.setBorder(new LineBorder(bgColor, 2, true));
        signOutDropDownBtn.setBorderPainted(false);
        initActions();
    }

    public void initPanels(Color bgColor,Font fontNameInfo,Font fontRoleInfo,Color colorNameInfo,Color colorRoleInfo){
        sectionInformation = new SectionInformation(bgColor,fontNameInfo,fontRoleInfo,colorNameInfo,colorRoleInfo);
    }

    public void initActions(){
        signOutDropDownBtn.addActionListener(click->
        {  signOutDropDownMenu.show(signOutDropDownBtn, 0, signOutDropDownBtn.getHeight());
        });
    }

    public HeaderWithoutLinks(Color bgColor, Font fontNameInfo, Font fontRoleInfo, Color colorNameInfo, Color colorRoleInfo,ImageIcon menuIcon,ImageIcon infoIcon){
        initPanels(bgColor,fontNameInfo,fontRoleInfo,colorNameInfo,colorRoleInfo);
        initButtons(bgColor,menuIcon,infoIcon);
        initMenuItems();
        setBackground(bgColor);
        setLayout(new BorderLayout());
        add(sideMenuBtn, BorderLayout.WEST);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(sectionInformation);
        panel2.setBackground(bgColor);
        panel2.add(signOutDropDownBtn);
        add(panel2,BorderLayout.EAST);
        setBorder(new EmptyBorder(20,20,20,20));
    }
}
