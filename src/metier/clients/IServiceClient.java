package metier.clients;

import presentation.modele.Compte;
import presentation.modele.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IServiceClient {
        public Compte getCompte();
        public boolean autorisationOp();
        Map<String,String> versement(String solde);
        Map<String,String> retrait  (String solde);
        Map<String,String> virement (String numCompte , String solde);
        List<Log> operationsAuj();
        Log derniereOperation();
        public Integer nbrCompteClient();
        Double soldeCompte();
        List<Log>   archiveVirements();
        List<Log>   archiveVersements();
        List<Log> archiveRetraits();
        List<Log> listeLogs();

        void afficherTicket();



}
