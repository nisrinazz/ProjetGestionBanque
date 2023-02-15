package presentation.vue.palette;

import presentation.modele.Compte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ChooseAccountForm extends JPanel{
    String[] data ;
    JLabel title ;
    JComboBox<String> numCompteField;
    Button cancelBtn , submitBtn;
    ErrorLabel errorLabel ;

    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel.setText(errorLabel);
    }

    public JLabel getTitle() {
        return title;
    }

    public JComboBox<String> getNumCompteField() {
        return numCompteField;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }
    public void initLabels(String label, Font font , Color fgColor){
       title = new JLabel(label);
       errorLabel = new ErrorLabel();
       errorLabel.setBounds(135,100,150,20);
       title.setFont(font);
       title.setForeground(fgColor);
       title.setBounds(140,10,200,50);
    }
    public void initField(List<Compte> comptes){
        initCompteData(comptes);
        numCompteField = new JComboBox<>(data);
        numCompteField.setSelectedIndex(-1);
        numCompteField.setBounds(135,70,180,30);
    }

    public void initCompteData(List<Compte> comptes){
        data = new String[comptes.size()];
        int i=0;
        for(Compte compte : comptes){
            data[i]=compte.getNumeroCompte();
            i++;
        }
    }

    public void initButton(String FirstBtnName , String SecondBtnName ,  Color bgColor , Color fgColor , Font font){
        submitBtn = new Button(FirstBtnName,bgColor,fgColor,font);
        submitBtn.setBounds(110,130,100,30);
        cancelBtn = new Button(SecondBtnName,bgColor,fgColor,font);
        cancelBtn.setBounds(230,130,100,30);
    }


    public ChooseAccountForm(List<Compte> listCompte ,Color bgColor , String FirstBtnName, String SecondBtnName , Color bgColorBtn , Color fgColor , Font font, String title , Font fontTitle , Color colorTitle){
       initField(listCompte);
        initButton(FirstBtnName,SecondBtnName,bgColorBtn,fgColor,font);
        initLabels(title,fontTitle,colorTitle);
        setLayout(null);
        add(numCompteField);
        add(cancelBtn);
        add(submitBtn);
        add(this.title);
        add(errorLabel);
        setBackground(bgColor);
    }



}
