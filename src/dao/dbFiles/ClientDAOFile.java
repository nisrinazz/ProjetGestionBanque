package dao.dbFiles;

import dao.IClientDAO;
import dao.ICompteDAO;
import presentation.modele.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClientDAOFile implements IClientDAO {


    public ClientDAOFile(){}
    @Override
    public List<Client> findAll() {
        List<Client> clients= new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FileBasePaths.CLIENT_TABLE);
            lines.remove(0);
          if(!lines.isEmpty())
                clients = lines.stream().filter(line -> {
                    return line.trim().length() > 0;
                }).map(
                        line ->
                        {
                            StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");

                            Long id = Long.parseLong(stringTokenizer.nextToken());
                            String nom = stringTokenizer.nextToken();
                            String prenom = stringTokenizer.nextToken();
                            String login = stringTokenizer.nextToken();
                            String mdp = stringTokenizer.nextToken();
                            String cin = stringTokenizer.nextToken();
                            String email = stringTokenizer.nextToken();
                            String tel = stringTokenizer.nextToken();
                            String s = stringTokenizer.nextToken();
                            Sexe sexe = null;
                            if (email.equalsIgnoreCase("NULL")) email = "";
                            if (tel.equalsIgnoreCase("NULL")) tel = "";
                            if (!s.equalsIgnoreCase("NULL")) {
                                if (s.equalsIgnoreCase("HOMME")) sexe = Sexe.HOMME;
                                else sexe = Sexe.FEMME;
                            }
                            Client client = new Client(login, mdp, nom, prenom, email, cin, tel, sexe);
                            client.setId(id);
                            return client;
                        }
            ).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return clients;
    }

    @Override
    public Client findById(Long aLong) {
        return findAll().stream()
                .filter(client -> client.getId() == aLong)
                .findFirst()
                .orElse(null);
    }
    private long getIncrementedId(){
        Long id = 1L;
        if(!findAll().isEmpty())
        {
            id = findAll().stream().map(Utilisateur::getId).max(Long::compareTo).get();
            id++;
        }
        return id;
    }
    @Override
    public Client save(Client client) {
        Long id = getIncrementedId();
        String clientStr = id + "\t\t\t" +
                client.getNom()+ "\t\t\t" +
                client.getPrenom()+ "\t\t\t" +
                client.getLogin()+ "\t\t\t" +
                client.getMotDePasse()+ "\t\t\t" +
                client.getCin()+ "\t\t\t" +
                (client.getEmail()!=null&&client.getEmail().trim().length()!=0?client.getEmail():"NULL")+ "\t\t\t" +
                (client.getTel()!=null&&client.getTel().trim().length()!=0?client.getTel():"NULL")+ "\t\t\t" +
                (client.getSexe()!=null?client.getSexe():"NULL")+ "\n" ;
        try {
            Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);
            client.setId(id);
           }
        catch (IOException e) { e.printStackTrace();}
        return client;
    }

    public Client saveWithID(Client client) {

        String clientStr =  client.getId() + "\t\t\t" +
                client.getNom()+ "\t" +
                client.getPrenom()+ "\t\t" +
                client.getLogin()+ "\t\t" +
                client.getMotDePasse()+ "\t\t" +
                client.getCin()+ "\t\t\t" +
                (client.getEmail()!=null&&client.getEmail().trim().length()!=0?client.getEmail():"NULL")+ "\t" +
                (client.getTel()!=null&&client.getTel().trim().length()!=0?client.getTel():"NULL")+ "\t\t" +
                (client.getSexe()!=null?client.getSexe():"NULL")+ "\t\tNULL\n" ;

        try {
            Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);

        }
        catch (IOException e) { e.printStackTrace();}

        return client;
    }

    public List<Client> saveAll(List<Client> listeClients) {
        return
                listeClients
                        .stream()
                        .map(client -> save(client))
                        .collect(Collectors.toList());
    }

    public List<Client> saveAllWithIds(List<Client> listeClients) {
        return
                listeClients
                        .stream()
                        .map(client -> saveWithID(client))
                        .collect(Collectors.toList());
    }

    @Override
    public Client update(Client newClient) {
        List<Client> clientsUpdated =
                findAll()
                        .stream()
                        .map(client -> {
                            if(client.getId() == newClient.getId())
                                return newClient;
                            else
                                return client;
                        })
                        .collect(Collectors.toList());
        FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
        saveAll(clientsUpdated);
        return newClient;
    }
    @Override
    public boolean delete(Long idClient) {
        Client clientS = findById(idClient);
        var clients = findAll();
        ICompteDAO compteDAO = new CompteDAOFile();
        if(!compteDAO.findByOwner(findById(idClient)).isEmpty())
            compteDAO.findByOwner(clientS).forEach(compte -> compteDAO.delete(compte.getNumeroCompte()));
        boolean deleted  =
                clients.remove(clientS);
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
            saveAllWithIds(clients);
        }
        return deleted;

    }

    public List<Client> findByKeywordLike(String keyWord){

        List<Client> clients = findAll();

        return
                clients
                        .stream()
                        .filter(client ->
                                        client.getId().toString().equals(keyWord) ||
                                        client.getNom().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        client.getPrenom().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        client.getLogin().equals(keyWord)    ||
                                        client.getMotDePasse().equals(keyWord)    ||
                                        client.getCin().equalsIgnoreCase(keyWord)    ||
                                        client.getEmail().equalsIgnoreCase(keyWord)    ||
                                        client.getTel().equals(keyWord)    ||
                                        client.getSexe().toString().toLowerCase().equalsIgnoreCase(keyWord)
                        )
                        .collect(Collectors.toList());

    }


}

