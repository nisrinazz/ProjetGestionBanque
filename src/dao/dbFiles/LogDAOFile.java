package dao.dbFiles;

import dao.ILogsDAO;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LogDAOFile implements ILogsDAO {

    @Override
    public List<Log> findAll(Compte compte) {
        Path path = Paths.get(FileBasePaths.LOGSFOLDER.toString(),compte.getNumeroCompte()+".txt");
        List<Log> logs = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(path);
            lines.remove(0);
            if(!lines.isEmpty())
               logs = lines.stream().filter(line->line.trim().length()>0)
                    .map(line->{
                        StringTokenizer stringTokenizer = new StringTokenizer(line , "\t");

                        LocalDate date = LocalDate.parse(stringTokenizer.nextToken());
                        LocalTime time = LocalTime.parse(stringTokenizer.nextToken());
                        String type = stringTokenizer.nextToken();
                        String msg = stringTokenizer.nextToken();
                        if(type.equals("CREATION"))
                            return new Log(date,time,TypeLog.CREATION,msg);
                         if(type.equals("VERSEMENT"))
                            return new Log(date,time,TypeLog.VERSEMENT,msg);
                         if(type.equals("VIREMENT"))
                            return new Log(date,time,TypeLog.VIREMENT,msg);
                         else
                            return new Log(date,time,TypeLog.RETRAIT,msg);

                    }).collect(Collectors.toList());
        } catch (IOException e) {
           e.printStackTrace();
        }
      return logs ;
    }

    @Override
    public List<Log> findByDate(Compte compte, LocalDate date) {
        return findAll(compte).stream().filter(log-> {
           int result =  log.getDate().compareTo(date);
           if(result == 0) return true ;
           return false ;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Log> findByType(Compte compte, TypeLog logTy) {
        return findAll(compte).stream().filter(log->log.getType().equals(logTy)).collect(Collectors.toList());
    }

    @Override
    public void save(Compte compte, TypeLog type, String msg) {
         String logStr= LocalDate.now().toString() +"\t\t\t"+
                        LocalTime.now().toString()+"\t\t\t";
         if (type.equals(TypeLog.CREATION)) logStr += "CREATION"+"\t\t\t";
        if (type.equals(TypeLog.RETRAIT)) logStr += "RETRAIT"+"\t\t\t";
        if (type.equals(TypeLog.VERSEMENT)) logStr += "VERSEMENT"+"\t\t\t";
        if (type.equals(TypeLog.VIREMENT)) logStr += "VIREMENT"+"\t\t\t";
        logStr+=msg+"\n";
        Path path = Paths.get(FileBasePaths.LOGSFOLDER.toString(),compte.getNumeroCompte()+".txt");
        try {
            Files.writeString(path,logStr, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
