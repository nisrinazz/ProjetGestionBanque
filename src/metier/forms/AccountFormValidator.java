package metier.forms;

import dao.dbFiles.ClientDAOFile;
import metier.Verifiable;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;

import java.util.HashMap;
import java.util.Map;

public class AccountFormValidator implements Verifiable{
    private static String FIELD_ID="idClient",FIELD_SOLDE="solde";

    private Map<String , String> errors = new HashMap<>();

    public Map<String, String> Errors() {
        return errors;
    }

    public void setError(String fieldName, String errorMsg) {
        Errors().put(fieldName, errorMsg);
    }

    private void verifierId(Long id) throws FormException {
        if(id == null)
            throw  new FormException("Id est obligatoire");
    }
    private void verifierSolde(String solde) throws FormException {
        if(solde != null && solde.trim().length() !=0)
        {
            if(!isDecimal(solde))
                 throw new FormException("Solde est invalide");
        }
        else throw new FormException("Solde est obligatoire");
    }

    public void validerId(Long id){
        try {
            verifierId(id);
        } catch (FormException e) {
            Errors().put(FIELD_ID,e.getMessage());
        }
    }
    public void validerSolde(String solde){
        try {
            verifierSolde(solde);
        } catch (FormException e) {
            Errors().put(FIELD_SOLDE,e.getMessage());
        }
    }

    public Compte validerCreation(Long id , String solde){
        validerId(id);
        validerSolde(solde);
        if(Errors().isEmpty()){
            Double nv_solde = Double.parseDouble(solde);
            Compte compte = new Compte();
            compte.setDateCreation();
            Client client = new ClientDAOFile().findById(id);
            compte.setPropriétaire(client);
            compte.setSolde(nv_solde);
            String msg = "Compte" + compte.getNumeroCompte() + " nouvellement crée avec un solde initiale de " + compte.getSolde() + "DH";
            return compte;
        }
        return null ;
    }
}
