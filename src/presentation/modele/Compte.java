package presentation.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Compte {
    private static long          compteur = 1;
    private String          numeroCompte;
    private Double          solde;
    private LocalDateTime   dateCreation;
    private Client          propriétaire;

    private List<Log>       logs = new ArrayList<>();

    public void setDateCreation() { this.dateCreation = LocalDateTime.now(); }
    public void setDateCreation(LocalDateTime date) { this.dateCreation = date; }
    public void setPropriétaire(Client propriétaire) {
        this.propriétaire = propriétaire;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Client           getPropriétaire() {
        return propriétaire;
    }
    public Double           getSolde() {
        return solde;
    }
    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setLog(TypeLog type, String msg) {

        Log log = new Log(LocalDate.now(), LocalTime.now(), type, msg);
        logs.add(log);
    }
    public void setNumeroCompte() {
        this.numeroCompte = "b-co00" + compteur++;
    }

    public void setNumeroCompte(String num) {
        this.numeroCompte = num;
    }
    public LocalDateTime    getDateCreation() {
        return dateCreation;
    }

    public Compte(){
        setNumeroCompte();
        setDateCreation();
        setSolde(0.0);
        setPropriétaire(null);
    }
    public List<Log>        getLogs() {
        return logs;
    }

    @Override
    public String toString() {

        String      compteStr  = "------------------------------------------------------\n";
                    compteStr += "| N° de Compte            : "   + getNumeroCompte()   + "\n";
                    compteStr += "| Solde du Compte         : "   + getSolde()    + " Dh\n" ;
                    compteStr += "| Propriétaire du Compte  : "   + getPropriétaire().getNomComplet() + "\n" ;
                    compteStr += "------------------------------------------------------\n";

        return compteStr;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Compte compte) {

            return this.numeroCompte.equals(compte.numeroCompte) ;
        }
        return false ;
    }

}
