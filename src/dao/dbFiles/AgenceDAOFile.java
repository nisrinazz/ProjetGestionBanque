package dao.dbFiles;

import dao.IAgenceDAO;
import presentation.modele.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class AgenceDAOFile implements IAgenceDAO {
    private long getIncrementedId(){
        Long id = 1L;
        if(!findAll().isEmpty())
        {
            id = findAll().stream().map(Agence::getIdBanque).max(Long::compareTo).get();
            id++;
        }
        return id;
    }
        @Override
    public Agence save(Agence agence) {
            Long id = getIncrementedId();
            String agenceStr = id + "\t\t\t" +
                    agence.getNomBanque()+ "\t\t\t" +
                    agence.getEmailBanque()+ "\t\t\t" +
                    agence.getTelBanque()+ "\t\t\t" +
                    agence.getAdresseBanque()+ "\n" ;
            try {
                Files.writeString(FileBasePaths.BANK_AGENCIES_TABLE, agenceStr, StandardOpenOption.APPEND);
                agence.setIdBanque(id);
            }
            catch (IOException e) { e.printStackTrace();}
            return agence;
    }

    @Override
    public Agence findFirst() {
        return findAll().get(0);
    }

    @Override
    public List<Agence> findAll() {
        List<Agence> agences = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FileBasePaths.BANK_AGENCIES_TABLE);
            lines.remove(0);
            if(!lines.isEmpty())
                agences = lines.stream().filter(line -> {
                    return line.trim().length() > 0;
                }).map(
                        line ->
                        {
                            StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");

                            Long id = Long.parseLong(stringTokenizer.nextToken());
                            String nom = stringTokenizer.nextToken();
                            String email = stringTokenizer.nextToken();
                            String telephone = stringTokenizer.nextToken();
                            String adresse = stringTokenizer.nextToken();
                            Agence agence = new Agence(nom,adresse,telephone,email);
                            agence.setIdBanque(id);
                            return agence;
                        }
                ).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return agences;
    }

    @Override
    public List<Agence> findByKeywordLike(String keyword) {
        List<Agence> agences = findAll();
        return agences.stream().filter(agence ->
                        agence.getIdBanque().equals(keyword) ||
                        agence.getAdresseBanque().contains(keyword) ||
                        agence.getEmailBanque().equals(keyword) ||
                        agence.getNomBanque().contains(keyword) ||
                        agence.getTelBanque().equals(keyword)
        ).collect(Collectors.toList());
    }
}
