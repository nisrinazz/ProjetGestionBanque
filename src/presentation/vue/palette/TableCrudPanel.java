package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TableCrudPanel extends JPanel{
    JButton addButton , editButton , deleteButton;

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }


    public void initButtons(Color bgColor,ImageIcon addBtnIcon , ImageIcon editBtnIcon , ImageIcon deleteBtnIcon){
        addButton = new JButton(addBtnIcon);
        editButton = new JButton(editBtnIcon);
        deleteButton = new JButton(deleteBtnIcon);
        addButton.setBackground(bgColor);
        editButton.setBackground(bgColor);
        deleteButton.setBackground(bgColor);
        addButton.setBorderPainted(false);
        deleteButton.setBorderPainted(false);
        editButton.setBorderPainted(false);
    }
    public void initButtons(Color bgColor,ImageIcon addBtnIcon, ImageIcon deleteBtnIcon){
        addButton = new JButton(addBtnIcon);
        deleteButton = new JButton(deleteBtnIcon);
        addButton.setBackground(bgColor);
        deleteButton.setBackground(bgColor);
        addButton.setBorderPainted(false);
        deleteButton.setBorderPainted(false);
    }
    public TableCrudPanel(Color bgColor,ImageIcon addBtnIcon , ImageIcon deleteBtnIcon){
        initButtons(bgColor,addBtnIcon,deleteBtnIcon);
        setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));
        add(addButton);
        add(deleteButton);
        setBackground(bgColor);
    }

    public TableCrudPanel(Color bgColor,ImageIcon addBtnIcon , ImageIcon editBtnIcon , ImageIcon deleteBtnIcon){
        initButtons(bgColor,addBtnIcon,editBtnIcon,deleteBtnIcon);
        setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));
        add(addButton);
        add(editButton);
        add(deleteButton);
        setBackground(bgColor);
    }
}
