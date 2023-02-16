package dao.dbFiles;

import dao.IClientDAO;
import dao.ICompteDAO;
import presentation.modele.Client;
import presentation.modele.Compte;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CompteDAOFile implements ICompteDAO {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    IClientDAO clientDAO ;


    public IClientDAO getClientDAO() {
        return clientDAO;
    }

    public void setClientDAO(IClientDAO iClientDAO) {
        this.clientDAO = iClientDAO;
    }

    public CompteDAOFile(){
        clientDAO = new ClientDAOFile();
    }

    @Override
    public List<Compte> findAll(){
        List<Compte> comptes = new ArrayList<>();
        try {
            List<String> comptesStr = Files.readAllLines(FileBasePaths.ACCOUNT_TABLE);
            comptesStr.remove(0);
            if(!comptesStr.isEmpty())
                comptes = comptesStr.stream().filter(line->line.trim().length()>0).map(line->
                        {
                            Compte compte = new Compte() ;
                            StringTokenizer stringTokenizer = new StringTokenizer(line,"\t");
                            String id = stringTokenizer.nextToken();
                            String d = stringTokenizer.nextToken();
                            String solde = stringTokenizer.nextToken();
                            String id_client = stringTokenizer.nextToken();
                            LocalDateTime date = LocalDateTime.parse(d,formatter);
                            compte.setNumeroCompte(id);
                            compte.setSolde(Double.parseDouble(solde));
                            compte.setDateCreation(date);
                            compte.setPropriétaire(clientDAO.findById(Long.parseLong(id_client)));
                            return compte ;
                        }).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return comptes;
    }

    @Override
    public Compte findById(String s) {
        return findAll().stream().filter(compte -> compte.getNumeroCompte().equals(s)).findFirst().orElse(null);
    }

    private long getIncrementedId(){
        Long id = 1L;
        if(!findAll().isEmpty())
        {
            id = findAll().stream().map(compte -> {
              return Long.parseLong(compte.getNumeroCompte().replaceAll("[\\D]", ""));
            }).max(Long::compareTo).get();
            id++;
        }
        return id;
    }

    @Override
    public Compte save(Compte compte) {
        Long id = getIncrementedId() ;
        String compteStr = "b-co00"+id+"\t\t\t"+
                           compte.getDateCreation().format(formatter)+"\t\t\t"+
                           compte.getSolde()+"\t\t\t"+
                           compte.getPropriétaire().getId()+"\n";
        compte.setNumeroCompte("b-co00"+id);
        try {
            Files.writeString(FileBasePaths.ACCOUNT_TABLE,compteStr, StandardOpenOption.APPEND);
            String LOGS_HEADER ="DATE\t\t\tTIME\t\t\tTYPE\t\t\tMESSAGE\n";
            Path p = Paths.get(FileBasePaths.LOGSFOLDER.toString(),"b-co00"+id+".txt");
            Files.writeString(p,LOGS_HEADER,StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compte ;
    }

    public Compte saveWithID(Compte compte) {
        String compteStr = compte.getNumeroCompte()+"\t\t\t"+
                compte.getDateCreation().format(formatter)+"\t\t\t"+
                compte.getSolde()+"\t\t\t"+
                compte.getPropriétaire().getId()+"\n";

        try {
            Files.writeString(FileBasePaths.ACCOUNT_TABLE,compteStr, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compte ;
    }

    public List<Compte> saveAll(List<Compte> comptes){
        return comptes.stream().map(compte-> save(compte)).collect(Collectors.toList());
    }
    public List<Compte> saveAllWithIds(List<Compte> comptes){
        return comptes.stream().map(compte-> saveWithID(compte)).collect(Collectors.toList());
    }

    @Override
    public Compte update(Compte newCompte) {
        List<Compte> comptes = findAll().stream().map(compte->{
            if(compte.equals(newCompte))
                return newCompte ;
            else return compte ;
        }).collect(Collectors.toList());
        FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE,FileBasePaths.ACCOUNT_TABLE_HEADER);
        saveAllWithIds(comptes);
        return newCompte;
    }

    @Override
    public boolean delete(String id) {
        List<Compte> comptes = findAll();
        boolean deleted = comptes.remove(findById(id));
        if(deleted){
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAllWithIds(comptes);
            Path p = Paths.get(FileBasePaths.LOGSFOLDER.toString(),id+".txt");
            try {
                Files.delete(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return deleted ;
    }

    @Override
    public List<Compte> findByOwner(Client c) {
        List<Compte> list;
        list = findAll().stream().filter(compte->compte.getPropriétaire().equals(c)).collect(Collectors.toList());
        return list;
    }

    public List<Compte> findByKeywordLike(String keyword){
        List<Compte> comptes = findAll();
       return comptes.stream().filter(compte ->
           compte.getNumeroCompte().equals(keyword) ||
           compte.getPropriétaire().getNom().contains(keyword) ||
           compte.getPropriétaire().getPrenom().contains(keyword) ||
           compte.getPropriétaire().getNomComplet().contains(keyword)
        ).collect(Collectors.toList());
    }

}
