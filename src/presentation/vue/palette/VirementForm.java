package presentation.vue.palette;
import javax.swing.*;
import java.awt.*;

public class VirementForm extends JPanel {
    private LabelFieldForms idCompteLabel;
    private ErrorLabel idCompteError;
    private LabelFieldForms motantLabel;
    private  ErrorLabel montantError;
    private BorderLessField montantField;
    private BorderLessField idCompteField;

    public BorderLessField getMontantField() {
        return montantField;
    }
    public BorderLessField getIdCompteField() {
        return idCompteField;
    }

    public ErrorLabel getMontantError() {
        return montantError;
    }

    public void setMontantError(String montantError) {
        this.montantError.setText(montantError);
    }

    public ErrorLabel getIdCompteError() {
        return idCompteError;
    }

    public void setIdCompteError(String idCompteError) {
        this.idCompteError.setText(idCompteError);
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }

    public Button submitBtn ;

    public void initLabels(Color fgColor , Font font){
        idCompteLabel = new LabelFieldForms("Id du bénéficiaire",fgColor,font);
        motantLabel = new LabelFieldForms("Montant à verser",fgColor,font);
        idCompteLabel.setBounds(20,30,160,30);
        motantLabel.setBounds(390,30,160,30);
        montantError = new ErrorLabel();
        idCompteError = new ErrorLabel();
        idCompteError.setBounds(165,55,190,30);
        montantError.setBounds(530,55,190,30);
    }
    public void initFields(Color color){
        montantField = new BorderLessField(color);
        idCompteField = new BorderLessField(color);
        montantField.setBounds(530,30,200,30);
        idCompteField.setBounds(165,30,200,30);
    }

    public void initButtons(String btnName,Color bgColor , Color fgColor,Font font){
        submitBtn = new Button(btnName,bgColor,fgColor,font);
        submitBtn.setBounds(300,100,100,30);
    }
    public void initButtons(Color bgColor , Color fgColor , Font font){
        submitBtn = new Button("Valider",bgColor,fgColor,font);
        submitBtn.setBounds(330,100,100,30);
    }
    public VirementForm(Color bgColor,Color fgColor,Font font,Color fieldColor,Color btnColor,Color fgBtnColor,Font fontBtn){
        initLabels(fgColor,font);
        initFields(fieldColor);
        initButtons(btnColor,fgBtnColor,fontBtn);
        setLayout(null);
        add(idCompteLabel);
        add(motantLabel);
        add(montantField);
        add(idCompteField);
        add(submitBtn);
        add(idCompteError);
        add(montantError);
        setBackground(bgColor);
    }
}
