package metier.clients;

import dao.IClientDAO;
import dao.ICompteDAO;
import dao.ILogsDAO;
import dao.dbFiles.ClientDAOFile;
import dao.dbFiles.CompteDAOFile;
import dao.dbFiles.LogDAOFile;
import metier.forms.OperationFormValidator;
import presentation.modele.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ServiceClient implements IServiceClient {
        private ICompteDAO compteDAO ;
        private  ILogsDAO logsDAO = new LogDAOFile();
        Compte compte ;
        public Compte getCompte() {
            return compte;
        }
        public void setCompte(Compte compte) {
            this.compte = compte;
        }
        public ServiceClient(Compte compte) {
            this.compte = compte ;
            compteDAO = new CompteDAOFile();
        }

    public boolean autorisationOp(){
            if(operationsAuj().size()<5)
                return true;
            else
                return false;
    }

    @Override
    public Map<String,String> versement(String montant){
        OperationFormValidator operationFormValidator = new OperationFormValidator(compteDAO,compte);
        if(operationFormValidator.validerVersement(montant)) {
            compte.setSolde(compte.getSolde() + Double.parseDouble(montant));
            compteDAO.update(compte);
            String msg = "Montant " + montant + "DH versé en votre faveur";
            logsDAO.save(compte, TypeLog.VERSEMENT, msg);
        }
            return operationFormValidator.Errors() ;
    }

    @Override
    public Map<String,String> retrait(String montant) {
        OperationFormValidator operationFormValidator = new OperationFormValidator(compteDAO,compte);
        if(operationFormValidator.validerRetrait(montant)) {
            compte.setSolde(compte.getSolde() - Double.parseDouble(montant));
            compteDAO.update(compte);
            String msg = "Montant " + montant + "DH retiré de votre compte";
            logsDAO.save(compte ,TypeLog.RETRAIT , msg);
        }
        return operationFormValidator.Errors() ;
    }

    @Override
    public Map<String,String> virement(String numCompte, String montant) {
        OperationFormValidator operationFormValidator = new OperationFormValidator(compteDAO,compte);
        if(operationFormValidator.validerVirement(montant,numCompte)){
            Compte benef = compteDAO.findById(numCompte);
            compte.setSolde(compte.getSolde() - Double.parseDouble(montant));
            benef.setSolde(benef.getSolde()+Double.parseDouble(montant));
            compteDAO.update(compte);
            compteDAO.update(benef);
            String msg1 = "Virement d'un montant de "+montant +"DH versé à partir de votre compte en faveur du compte "+compte.getNumeroCompte();
            String msg2 = "Virement d'un montant de "+ montant +"DH versé en votre faveur à partir du compte "+benef.getNumeroCompte();
            logsDAO.save(compte , TypeLog.VIREMENT, msg1);
            logsDAO.save(benef ,TypeLog.VIREMENT, msg2);
        }
        return operationFormValidator.Errors() ;
    }

    @Override
    public List<Log> operationsAuj() {
         return logsDAO.findByDate(compte,LocalDate.now());
    }

    @Override
    public Log derniereOperation() {
        List<Log> logs = logsDAO.findAll(compte);
        return logs.get(logs.size()-1);
    }

    public Integer nbrCompteClient(){
            return compteDAO.findByOwner(compte.getPropriétaire()).size();
    }
    @Override
    public Double soldeCompte() {
        return compte.getSolde();
    }

    @Override
    public List<Log> archiveVirements() {
        return logsDAO.findByType(compte ,TypeLog.VIREMENT);
    }

    @Override
    public List<Log> archiveVersements() {
        return logsDAO.findByType(compte ,TypeLog.VERSEMENT);
    }

    @Override
    public List<Log> archiveRetraits() {
        return logsDAO.findByType(compte ,TypeLog.RETRAIT);
    }

    @Override
    public List<Log> listeLogs() {
        return logsDAO.findAll(compte);
    }


    @Override
    public void afficherTicket() {

    }
}
