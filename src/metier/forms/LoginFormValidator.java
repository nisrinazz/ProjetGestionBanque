package metier.forms;

import dao.IAuthDAO;
import presentation.modele.Admin;
import presentation.modele.Client;
import presentation.modele.Utilisateur;

import java.util.HashMap;
import java.util.Map;

public class LoginFormValidator{

	private static final String FIELD_LOGIN ="login", FIELD_PASS = "pass";


	private Map<String , String> errors = new HashMap<>();
	private String resultMsg;
	private IAuthDAO authDAO;


	public LoginFormValidator(IAuthDAO authDAO){

		this.authDAO = authDAO;
	}
	public Map<String, String> Errors() {
		return errors;
	}
	public void setError(String fieldName, String errorMsg) {
		Errors().put(fieldName, errorMsg);
	}

	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public IAuthDAO getAuthDAO() {
		return authDAO;
	}
	public void setAuthDAO(IAuthDAO authDAO) {
		this.authDAO = authDAO;
	}


	private void verifierLogin(String login) throws FormException{
		if(login!= null && login.trim().length()!=0){
			if(login.trim().length()<4)
				throw new FormException("Login doit avoir plus de 4 caractères");
		}else{
			throw new FormException("Login est obligatoire");
		}
	}
	private void verifierPass(String pass) throws FormException{
		if(pass!= null && pass.trim().length()!=0){
			if(pass.trim().length()<4)
				throw new FormException("Mot de passe doit avoir plus de 4 caractères");
		}else{
			throw new FormException("Mot de passe est obligatoire");
		}
	}

	// fonctions de validation
	public void validerLogin(String login){

		try {
			verifierLogin(login);

		} catch (FormException e) {
			setError(FIELD_LOGIN, e.getMessage());
		}
	}
	public void validerPass(String pass){

		try {
			verifierPass(pass);

		} catch (FormException e) {
			setError(FIELD_PASS, e.getMessage());
		}
	}

	// fonction de validation de formulaire
	public Utilisateur validerSession(String login, String password){

		errors.clear();
		Utilisateur session = null;
		validerLogin(login);
		validerPass(password);
		if(Errors().isEmpty()){
			session = authDAO.findByLoginAndPass(login, password);
		}
		return session;
}}

