package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class FilterSortPanel extends JPanel {

   private LinkedHashMap<String,JRadioButton> list;
    private ButtonGroup group ;
   private JLabel title ;

    public LinkedHashMap<String, JRadioButton> getList() {
        return list;
    }

    public void initLabels(String title, Font fontTitle , Color fgTitle){
        this.title=new JLabel(title);
        this.title.setFont(fontTitle);
        this.title.setForeground(fgTitle);
    }

    public void initRadioButtons(Font fontItems , Color fgItems , String... list){
        List<String> filterByList = new ArrayList<>(Arrays.asList(list));
        this.list = new LinkedHashMap<>();
        filterByList.forEach(item->{
                JRadioButton button = new JRadioButton(item);
                button.setFont(fontItems);
                button.setForeground(fgItems);
                this.list.put(item,button);
        });
        group = new ButtonGroup();
    }

    public FilterSortPanel(String title , Font fontTitle , Color fgTitle , Font fontItems , Color fgItems, String... list){
          initLabels(title,  fontTitle ,  fgTitle);
          initRadioButtons( fontItems ,  fgItems , list);
          setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
          add(this.title);
          this.list.forEach((name, button)->{
              group.add(button);
              add(button);
          });



    }


}
