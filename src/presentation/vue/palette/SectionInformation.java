package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class SectionInformation extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    JLabel nameUser , roleUser ;

    public JLabel getNameUser() {
        return nameUser;
    }

    public JLabel getRoleUser() {
        return roleUser;
    }

    public void setNameUser(String name) {
        this.nameUser.setText(name);
    }

    public void setRoleUser(String role) {
        this.roleUser.setText(role);
    }

    public void initLabels( Font fontName , Font fontRole , Color colorName , Color colorRole) {
        nameUser = new JLabel();
        nameUser.setForeground(colorName);
        nameUser.setFont(fontName);
        nameUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        roleUser = new JLabel();
        roleUser.setForeground(colorRole);
        roleUser.setFont(fontRole);
        roleUser.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public SectionInformation(Color bgColor, Font fontName , Font fontRole , Color colorName , Color colorRole){
        setBackground(bgColor);
        initLabels(fontName,fontRole,colorName,colorRole);
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(nameUser);
        panel.add(Box.createRigidArea(new Dimension(0, 3)));
        panel.add(roleUser);
        add(panel);
    }

}
