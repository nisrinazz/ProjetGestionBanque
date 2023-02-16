package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class ClientForm extends JPanel {
   private ErrorLabel errorNom ;
   private ErrorLabel errorPrenom ;
   private ErrorLabel errorLogin ;
   private ErrorLabel errorMdp ;
   private ErrorLabel errorTel ;
   private ErrorLabel errorCin ;
   private ErrorLabel errorMail ;
   private ErrorLabel errorSexe;
    private LabelFieldForms nomLabel ;
    private LabelFieldForms prenomLabel ;

    private LabelFieldForms loginLabel ;

    private LabelFieldForms mdpLabel ;
    private LabelFieldForms telLabel ;
    private LabelFieldForms cinLabel ;
    private LabelFieldForms mailLabel ;
    private LabelFieldForms sexeLabel ;
    private BorderLessField nom ;
    private BorderLessField prenom ;
    private BorderLessField login ;
    private BorderLessField mdp ;
    private BorderLessField tel ;
    private BorderLessField cin ;
    private BorderLessField mail ;
    private JComboBox<String> sexe ;
    private Button submitBtn ;


    public BorderLessField getNom() {
        return nom;
    }

    public BorderLessField getPrenom() {
        return prenom;
    }

    public BorderLessField getLogin() {
        return login;
    }

    public BorderLessField getMdp() {
        return mdp;
    }

    public BorderLessField getTel() {
        return tel;
    }

    public BorderLessField getCin() {
        return cin;
    }

    public BorderLessField getMail() {
        return mail;
    }

    public JComboBox<String> getSexe() {
        return sexe;
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }

    public ErrorLabel getErrorSexe() {
        return errorSexe;
    }

    public void setErrorSexe(String errorSexe) {
        this.errorSexe.setText(errorSexe);
    }

    public void setNom(String nom) {
       this.nom.setText(nom);
    }

    public void setPrenom(String prenom) {
        this.prenom.setText(prenom);
    }

    public void setLogin(String login) {
        this.login.setText(login);
    }

    public void setMdp(String mdp) {
        this.mdp.setText(mdp);
    }

    public void setTel(String tel) {
        this.tel.setText(tel);
    }

    public void setCin(String cin) {
        this.cin.setText(cin);
    }

    public void setMail(String mail) {
        this.mail.setText(mail);
    }

    public void setSexe(String sexe) {
        this.sexe.setSelectedItem((String)sexe);
    }

    public ErrorLabel getErrorNom() {
        return errorNom;
    }

    public void setErrorNom(String errorNom) {
        this.errorNom.setText(errorNom);
    }

    public ErrorLabel getErrorPrenom() {
        return errorPrenom;
    }

    public void setErorrPrenom(String erorrPrenom) {
        this.errorPrenom.setText(erorrPrenom);
    }

    public ErrorLabel getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(String errorLogin) {
        this.errorLogin.setText(errorLogin);
    }

    public ErrorLabel getErrorMdp() {
        return errorMdp;
    }

    public void setErrorMdp(String errorMdp) {
        this.errorMdp.setText(errorMdp);
    }

    public ErrorLabel getErrorCin() {
        return errorCin;
    }

    public void setErrorCin(String errorCin) {
        this.errorCin.setText(errorCin);
    }

    public ErrorLabel getErrorMail() {
        return errorMail;
    }

    public void setErrorMail(String errorMail) {
        this.errorMail.setText(errorMail);
    }

    public ErrorLabel getErrorTel() {
        return errorTel;
    }

    public void setErrorTel(String errorTel) {
        this.errorTel.setText(errorTel);
    }

    public void initLabels(Color color , Font font){
        errorNom = new ErrorLabel();
        errorPrenom = new ErrorLabel();
        errorLogin = new ErrorLabel();
        errorMdp = new ErrorLabel();
        errorTel = new ErrorLabel();
        errorCin = new ErrorLabel();
        errorMail = new ErrorLabel();
        errorSexe = new ErrorLabel();
        nomLabel = new LabelFieldForms("Nom",color,font);
        nomLabel.setBounds(20,30,120,30);
        prenomLabel = new LabelFieldForms("Prenom",color,font);
        prenomLabel.setBounds(330,30,120,30);
        loginLabel = new LabelFieldForms("Login",color,font);
        loginLabel.setBounds(20,100,120,30);
        mdpLabel = new LabelFieldForms("Mot de passe",color,font);
        mdpLabel.setBounds(330,100,120,30);
        telLabel = new LabelFieldForms("Telephone",color,font);
        telLabel.setBounds(20,170,120,30);
        cinLabel = new LabelFieldForms("CIN",color,font);
        cinLabel.setBounds(330,170,120,30);
        mailLabel = new LabelFieldForms("Email",color,font);
        mailLabel.setBounds(20,240,120,30);
        sexeLabel = new LabelFieldForms("Sexe",color,font);
        sexeLabel.setBounds(330,240,120,30);
        errorNom.setBounds(110,55,190,30);
        errorPrenom.setBounds(455,55,190,30);
        errorLogin.setBounds(110,125,190,30);
        errorMdp.setBounds(455,125,190,30);
        errorTel.setBounds(110,195,190,30);
        errorCin.setBounds(455,195,190,30);
        errorMail.setBounds(110,265,190,30);
        errorSexe.setBounds(455,265,190,30);
    }

    public void initLabels(String nom , String prenom , String login , String mdp , String tel , String cin , String mail , String sexe,Color color , Font font){
        errorNom = new ErrorLabel();
        errorPrenom = new ErrorLabel();
        errorLogin = new ErrorLabel();
        errorMdp = new ErrorLabel();
        errorTel = new ErrorLabel();
        errorCin = new ErrorLabel();
        errorMail = new ErrorLabel();
        nomLabel = new LabelFieldForms(nom,color,font);
        nomLabel.setBounds(20,30,120,30);
        prenomLabel = new LabelFieldForms(prenom,color,font);
        prenomLabel.setBounds(330,30,120,30);
        loginLabel = new LabelFieldForms(login,color,font);
        loginLabel.setBounds(20,100,120,30);
        mdpLabel = new LabelFieldForms(mdp,color,font);
        mdpLabel.setBounds(330,100,120,30);
        telLabel = new LabelFieldForms(tel,color,font);
        telLabel.setBounds(20,170,120,30);
        cinLabel = new LabelFieldForms(cin,color,font);
        cinLabel.setBounds(330,170,120,30);
        mailLabel = new LabelFieldForms(mail,color,font);
        mailLabel.setBounds(20,240,120,30);
        sexeLabel = new LabelFieldForms(sexe,color,font);
        sexeLabel.setBounds(330,240,120,30);
        errorNom.setBounds(110,55,190,30);
        errorPrenom.setBounds(455,55,190,30);
        errorLogin.setBounds(110,125,190,30);
        errorMdp.setBounds(455,125,190,30);
        errorTel.setBounds(110,195,190,30);
        errorCin.setBounds(455,195,190,30);
        errorMail.setBounds(110,265,190,30);
        errorSexe.setBounds(455,265,190,30);
    }


    public void initButtons(String submit,Color bgColor , Color fgColor,Font font){
        submitBtn = new Button(submit,bgColor,fgColor,font);
        submitBtn.setBounds(300,310,100,30);
    }

    public void initButtons(Color bgColor , Color fgColor , Font font){
        submitBtn = new Button("Ajouter",bgColor,fgColor,font);
        submitBtn.setBounds(300,310,100,30);

    }
    public void initFields(Color color){
        nom = new BorderLessField(color);
        nom.setBounds(110,30,200,30);
        prenom = new BorderLessField(color);
        prenom.setBounds(455,30,200,30);
        login = new BorderLessField(color);
        login.setBounds(110,100,200,30);
        mdp = new BorderLessField(color);
        mdp.setBounds(455,100,200,30);
        tel = new BorderLessField(color);
        tel.setBounds(110,170,200,30);
        cin = new BorderLessField(color);
        cin.setBounds(455,170,200,30);
        mail = new BorderLessField(color);
        mail.setBounds(110,240,200,30);
        sexe = new JComboBox<>(new String[]{"HOMME", "FEMME"});
        sexe.setSelectedIndex(-1);
        sexe.setBounds(455,240,200,30);
    }



    public ClientForm(Color bgColor,String btnName , Color bgColorBtn , Color fgColorBtn , Font fontBtn, Color colorLabel , Font fontLabel, Color colorField){
        setLayout(null);
        initFields(colorField);
        initLabels(colorLabel,fontLabel);
        initButtons(btnName, bgColorBtn , fgColorBtn , fontBtn);
        add(nom);
        add(prenom);
        add(login);
        add(mdp);
        add(tel);
        add(cin);
        add(sexe);
        add(mail);
        add(submitBtn);
        add(nomLabel);
        add(prenomLabel);
        add(loginLabel);
        add(mdpLabel);
        add(telLabel);
        add(cinLabel);
        add(sexeLabel);
        add(mailLabel);
        add(errorNom);
        add(errorPrenom);
        add(errorLogin);
        add(errorMdp);
        add(errorCin);
        add(errorMail);
        add(errorTel);
        add(errorSexe);
        setBackground(bgColor);
    }
}
