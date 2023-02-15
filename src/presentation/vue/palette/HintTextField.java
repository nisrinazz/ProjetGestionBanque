package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextField extends JTextField {

    Font gainFont = new Font("Arial", Font.BOLD, 18);
    Font lostFont = new Font("Arial", Font.ITALIC, 17);

    public HintTextField(final String hint, Color gainColor, Color lostColor) {
        setText(hint);
        setForeground(lostColor);
        setFont(lostFont);
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(new LineBorder(Color.WHITE, 2, true));
        setBackground(Color.WHITE);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setFont(gainFont);
                    setForeground(gainColor);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(gainColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(lostColor);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(gainColor);
                }
            }
        });

    }








    public HintTextField(final String hint, Font f, Color textColor, Color backColor, boolean opaque) {

        setText(hint);
        setFont(f);
        setForeground(textColor);
        setBackground(backColor);
        setOpaque(opaque);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setFont(f);
                } else {
                    setText(getText());
                    setFont(f);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    //setForeground(Color.GRAY);
                    setForeground(textColor);
                } else {
                    setText(getText());
                    setFont(f);
                    setForeground(Color.blue);
                }
            }
        });

    }

}
