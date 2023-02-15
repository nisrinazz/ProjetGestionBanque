package presentation.vue.palette;
import dao.dbFiles.ClientDAOFile;
import presentation.modele.Client;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccountForm extends JPanel {
    Long[] data ;
    private LabelFieldForms idClientLabel;
    private ErrorLabel idClientError;
    private LabelFieldForms soldeLabel ;
    private  ErrorLabel soldeError;
    private BorderLessField soldeField ;
    private JComboBox<Long> idClientField ;

    public BorderLessField getSoldeField() {
        return soldeField;
    }
    public JComboBox<Long> getIdClientField() {
        return idClientField;
    }

    public ErrorLabel getSoldeError() {
        return soldeError;
    }

    public void setSoldeError(String soldeError) {
        this.soldeError.setText(soldeError);
    }

    public ErrorLabel getIdClientError() {
        return idClientError;
    }

    public void setIdClientError(String idClientError) {
        this.idClientError.setText(idClientError);
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }

    public Button submitBtn ;

    public void initLabels(Color fgColor , Font font){
        idClientLabel = new LabelFieldForms("ID du client",fgColor,font);
        soldeLabel = new LabelFieldForms("Solde Initial",fgColor,font);
        idClientLabel.setBounds(20,30,120,30);
        soldeLabel.setBounds(330,30,120,30);
        soldeError = new ErrorLabel();
        idClientError = new ErrorLabel();
        idClientError.setBounds(110,55,190,30);
        soldeError.setBounds(455,55,190,30);
    }
    public void initIdData(){
        List<Client> listClient = new ClientDAOFile().findAll();
        data = new Long[listClient.size()];
        int i=0;
        for(Client client : listClient){
            data[i]=client.getId();
            i++;
        }
    }
    public void initFields(Color color){
        soldeField = new BorderLessField(color);
        initIdData();
        idClientField = new JComboBox<>(data);
        soldeField.setBounds(455,30,200,30);
        idClientField .setBounds(110,30,200,30);
        idClientField.setSelectedIndex(-1);
    }

    public void initButtons(String btnName,Color bgColor , Color fgColor,Font font){
        submitBtn = new Button(btnName,bgColor,fgColor,font);
        submitBtn.setBounds(300,100,100,30);
    }
    public void initButtons(Color bgColor , Color fgColor , Font font){
        submitBtn = new Button("Ajouter",bgColor,fgColor,font);
        submitBtn.setBounds(300,100,100,30);
    }
    public AccountForm(Color bgColor,Color fgColor,Font font,Color fieldColor,Color btnColor,Color fgBtnColor,Font fontBtn){
        initLabels(fgColor,font);
        initFields(fieldColor);
        initButtons(btnColor,fgBtnColor,fontBtn);
        setLayout(null);
        add(idClientLabel);
        add(soldeLabel);
        add(soldeField);
        add(idClientField);
        add(submitBtn);
        add(idClientError);
        add(soldeError);
        setBackground(bgColor);
    }

}
