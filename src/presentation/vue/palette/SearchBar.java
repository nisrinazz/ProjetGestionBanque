package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchBar extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    private HintTextField searchField ;
    private SearchButton searchButton ;
    private String hint ;

    public HintTextField getSearchField() {
        return searchField;
    }

    public SearchButton getSearchButton() {
        return searchButton;
    }

    public String getHint() {
        return hint;
    }

    public void initField(String hint , Color gainColor , Color lostColor){
        this.hint = hint ;
        searchField = new HintTextField(hint , gainColor , lostColor);
        searchField.setHorizontalAlignment(SwingConstants.CENTER);
        searchField.setPreferredSize(new Dimension(300, 50));
        searchField.setPreferredSize(new Dimension(350, 55));
    }
    public void initButton(ImageIcon icon,ImageIcon iconH , Color bgColor){
        searchButton = new SearchButton(icon,bgColor);
        searchButton.setPreferredSize(new Dimension(30,50));
        searchButton.setMaximumSize(new Dimension(40,55));
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setIcon(iconH);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setIcon(icon);

            }
    }

    );
    }

    public SearchBar(String hint , Color gainColor , Color lostColor, ImageIcon icon , ImageIcon iconH , Color bgColor, Color bgColorSearchBtn){
        setBackground(bgColor);
        initField(hint , gainColor , lostColor);
        initButton(icon,iconH,bgColorSearchBtn);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(searchField);
        panel.add(searchButton);
        add(panel);
    }
}
