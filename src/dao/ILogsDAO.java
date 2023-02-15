package dao;

import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

import java.time.LocalDate;
import java.util.List;

public interface ILogsDAO {
    List<Log>  findAll(Compte compte);
    List<Log>  findByDate(Compte compte , LocalDate date);

    List<Log>  findByType(Compte compte ,TypeLog log);

    void        save(Compte compte ,TypeLog type , String msg);

}
