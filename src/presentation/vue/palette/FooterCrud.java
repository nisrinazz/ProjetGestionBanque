package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FooterCrud extends JPanel {
  TableCrudPanel tableCrudPanel;
    SearchBar searchBar ;


    public TableCrudPanel getTableCrudPanel() {
        return tableCrudPanel;
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }


    public void initPanel(Color bgColor,String hint,Color gainColor , Color lostColor , ImageIcon iconSearch , ImageIcon iconSearchH,Color bgSearchBtn,ImageIcon addBtnIcon , ImageIcon editBtnIcon , ImageIcon deleteBtnIcon){
      tableCrudPanel=new TableCrudPanel(bgColor,addBtnIcon,editBtnIcon,deleteBtnIcon);
      searchBar = new SearchBar(hint ,gainColor , lostColor, iconSearch ,iconSearchH ,bgColor,bgSearchBtn);
  }

    public void initPanel(Color bgColor,String hint,Color gainColor , Color lostColor , ImageIcon iconSearch , ImageIcon iconSearchH,Color bgSearchBtn,ImageIcon addBtnIcon, ImageIcon deleteBtnIcon){
        tableCrudPanel=new TableCrudPanel(bgColor,addBtnIcon,deleteBtnIcon);
        searchBar = new SearchBar(hint ,gainColor , lostColor, iconSearch ,iconSearchH ,bgColor,bgSearchBtn);
    }

    public void initPanel(Color bgColor,String hint,Color gainColor , Color lostColor , ImageIcon iconSearch , ImageIcon iconSearchH,Color bgSearchBtn){
        searchBar = new SearchBar(hint ,gainColor , lostColor, iconSearch ,iconSearchH ,bgColor,bgSearchBtn);
    }

    public FooterCrud(Color bgColor, String hint, Color gainColor , Color lostColor , ImageIcon iconSearch , ImageIcon iconSearchH, Color bgSearchBtn, ImageIcon addBtnIcon , ImageIcon editBtnIcon , ImageIcon deleteBtnIcon){
        initPanel(bgColor,hint,gainColor , lostColor, iconSearch ,iconSearchH ,bgSearchBtn,addBtnIcon,editBtnIcon,deleteBtnIcon);
        setBackground(bgColor);
        setLayout(new BorderLayout());
        add(searchBar,BorderLayout.WEST);
        add(tableCrudPanel,BorderLayout.EAST);
        setBorder(new EmptyBorder(15,15,25,3));
    }

    public FooterCrud(Color bgColor, String hint, Color gainColor , Color lostColor , ImageIcon iconSearch , ImageIcon iconSearchH, Color bgSearchBtn, ImageIcon addBtnIcon , ImageIcon deleteBtnIcon){
        initPanel(bgColor,hint,gainColor , lostColor, iconSearch ,iconSearchH ,bgSearchBtn,addBtnIcon,deleteBtnIcon);
        setBackground(bgColor);
        setLayout(new BorderLayout());
        add(searchBar,BorderLayout.WEST);
        add(tableCrudPanel,BorderLayout.EAST);
        setBorder(new EmptyBorder(15,15,25,3));
    }

    public FooterCrud(Color bgColor, String hint, Color gainColor , Color lostColor , ImageIcon iconSearch , ImageIcon iconSearchH, Color bgSearchBtn){
        initPanel(bgColor,hint,gainColor , lostColor, iconSearch ,iconSearchH ,bgSearchBtn);
        setBackground(bgColor);
        setLayout(new BorderLayout());
        add(searchBar,BorderLayout.CENTER);
        setBorder(new EmptyBorder(15,15,30,3));
    }


}
