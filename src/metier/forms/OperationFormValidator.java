package metier.forms;

import dao.ICompteDAO;
import metier.Verifiable;
import presentation.modele.Compte;

import java.util.HashMap;
import java.util.Map;

public class OperationFormValidator implements Verifiable {
    private static String FIELD_ID="numCompte",FIELD_MONTANT="montant";

    private ICompteDAO compteDAO ;
    private Compte compte ;

    private Map<String , String> errors = new HashMap<>();

    public Map<String, String> Errors() {
        return errors;
    }

    public void setError(String fieldName, String errorMsg) {
        Errors().put(fieldName, errorMsg);
    }

    private void verifierNumCompte(String num) throws FormException {
        if(num != null && num.trim().length() !=0) {
            if (compteDAO.findById(num) == null)
                throw new FormException("Id compte inexistant");
        }
        else throw  new FormException("Id compte est obligatoire");
    }
    private void verifierMontant(String montant,boolean versement) throws FormException {
        if(montant != null && montant.trim().length() !=0) {
            if (!isDecimal(montant))
                throw new FormException("Montant invalide");
            else if (compte.getSolde() < Double.parseDouble(montant) && !versement)
                throw new FormException("montant supérieure à votre solde");
        }
        else throw new FormException("montant est obligatoire");
    }

    public void validerNumCompte(String numCompte){
        try {
            verifierNumCompte(numCompte);
        } catch (FormException e) {
            Errors().put(FIELD_ID,e.getMessage());
        }
    }

    public void validerMontant(String montant,boolean versement){
        try {
            verifierMontant(montant,versement);
        } catch (FormException e) {
            Errors().put(FIELD_MONTANT,e.getMessage());
        }
    }

    public boolean validerRetrait(String montant){
        validerMontant(montant,false);
        if(Errors().isEmpty()) return true ;
        return false;

    }

    public boolean validerVersement(String montant){
        validerMontant(montant,true);
        if(Errors().isEmpty()) return true ;
        return false;
    }
    public boolean validerVirement(String montant , String numCompte){
        validerMontant(montant,false);
        validerNumCompte(numCompte);
        if(Errors().isEmpty()) return true ;
        return false;
    }

    public OperationFormValidator(ICompteDAO compteDAO,Compte compte){
        this.compteDAO = compteDAO;
        this.compte = compte;
    }
}
