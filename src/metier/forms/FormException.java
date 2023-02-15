package metier.forms;

public class FormException extends Exception{

	private String consigne;


	public String getConsigne() {
		return consigne;
	}

	public FormException()
	{
		super("Erreur dans le formulaire !!!!");
	}
	public FormException(String errorMsg)
	{
		super(errorMsg);
	}
	public FormException(String errorMsg, String consigne)
	{
		super(errorMsg);
		this.consigne = consigne;
	}



}

