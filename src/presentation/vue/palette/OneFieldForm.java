package presentation.vue.palette;

import presentation.vue.palette.BorderLessField;
import presentation.vue.palette.Button;
import presentation.vue.palette.LabelFieldForms;

import javax.swing.*;
import java.awt.*;

public class OneFieldForm extends JPanel {
    private Button submit ;
    private LabelFieldForms labelField;
    private BorderLessField field ;
    ErrorLabel error ;

    public Button getSubmit() {
        return submit;
    }

    public BorderLessField getField() {
        return field;
    }

    public ErrorLabel getError() {
        return error;
    }

    public void setError(String error) {
        this.error.setText(error);
    }

    public void initLabel(String label , Color color , Font font){
        labelField = new LabelFieldForms(label,color,font);
        error =new ErrorLabel();
        labelField.setBounds(50,30,100,40);
        error.setBounds(140,60,250,40);
        error.setVisible(false);
    }

    public void initField(Color fieldColor){
        field = new BorderLessField(fieldColor);
        field.setBounds(140,30,250,40);
    }

    public void initButton(String buttonName , Color btnBgColor , Color btnFgColor , Font btnFont  ){
        submit = new Button(buttonName,btnBgColor,btnFgColor,btnFont);
        submit.setBounds(200,100,100,30);
    }


    public OneFieldForm(Color bgColor , String label , Color colorLabel , Font fontLabel,Color fieldColor,String buttonName , Color btnBgColor , Color btnFgColor , Font btnFont){
        initButton(buttonName,btnBgColor,btnFgColor,btnFont);
        initField(fieldColor);
        initLabel(label,colorLabel,fontLabel);
        setBackground(bgColor);
        setLayout(null);
        add(this.labelField);
        add(this.field);
        add(this.submit);
        add(this.error);
    }



}
