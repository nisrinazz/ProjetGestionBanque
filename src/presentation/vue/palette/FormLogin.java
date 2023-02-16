package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormLogin extends JPanel{
    JTextField loginField ;
    JLabel login , pass ;
    JCheckBox passIcon ;
    JPasswordField passField ;
    Button loginBtn ;
    ErrorLabel errorLogin , errorMdp ;
    ErrorLabel errorForm;

    public ErrorLabel getErrorForm() {
        return errorForm;
    }

        public void setErrorForm(String error) {
        this.errorForm.setText(error);
    }

    public ErrorLabel getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(String error) {
        this.errorLogin.setText(error);
    }

    public ErrorLabel getErrorMdp() {
        return errorMdp;
    }

    public void setErrorMdp(String error) {
        this.errorMdp.setText(error);
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public void initCheckBox(Icon hidePass , Icon showPass,Color bgColor){
        passIcon = new JCheckBox(hidePass);
        passIcon.setOpaque(true);
        passIcon.setBackground(bgColor);
            passIcon.setBounds(345,115,30,30);
        passIcon.addActionListener(click->{
            if(passIcon.isSelected()){
                passIcon.setIcon(showPass);
                passField.setEchoChar((char) 0);
            }
            else{
                passIcon.setIcon(hidePass);
                passField.setEchoChar('•');
            }
        });
    }
    public void initLabel(String login , String pass , Font font , Color color){
        errorLogin = new ErrorLabel();
        errorMdp = new ErrorLabel();
        errorForm = new ErrorLabel();
        this.login = new JLabel(login);
        this.login.setBounds(80,60,100,20);
        this.errorLogin.setBounds(190,80,190,30);
        this.pass = new JLabel(pass);
         this.pass.setBounds(80,120,100,20);
        this.errorMdp.setBounds(190,140,190,30);
        this.errorForm.setBounds(210,150,190,30);
        this.login.setFont(font);
        this.pass.setFont(font);
        this.login.setForeground(color);
        this.pass.setForeground(color);

    }
    public void initFields(){
         loginField = new JTextField(20);
         loginField.setBounds(190,55,150,30);
        loginField.setBorder(new LineBorder(Color.WHITE, 2, true));
        loginField.setBackground(Color.WHITE);
         passField = new JPasswordField(20);
        passField.setBorder(new LineBorder(Color.WHITE, 2, true));
        passField.setBackground(Color.WHITE);
        passField.setBounds(190,115,150,30);
    }
    public void initButtons(String buttonName,Color bgcolor , Color fgColor,Font font){
        loginBtn = new Button(buttonName,bgcolor,fgColor,font);
        loginBtn.setBounds(160,190,120,30);
    }

    public FormLogin(Color bgColor , String login , String pass , Font font , Color color, String buttonName, Color bgColorBtn, Color fgColorBtn , Font fontBtn,ImageIcon hidePass , ImageIcon showPass){
         initLabel(login,pass,font,color);
         initFields();
         initButtons(buttonName,bgColorBtn,fgColorBtn,fontBtn);
        initCheckBox(hidePass,showPass,bgColor);
         setLayout(null);
         add(this.login);
         add(this.pass);
         add(errorLogin);
         add(errorMdp);
         add(errorForm);
         add(loginField);
         add(passField);
         add(passIcon);
         add(loginBtn);
         setBackground(bgColor);
    }

}
