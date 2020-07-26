package ru.nsu.truckcomp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.RepairList;

import java.util.Date;
import java.util.List;

public interface RepairListRepository extends CrudRepository<RepairList, Integer> {
    @Query(value = "SELECT new ru.nsu.truckcomp.model.RepairList(r.brigade, r.transport, r.sparePart, r.cost, r.received, r.returned) " +
            "FROM RepairList r INNER JOIN ServiceStaff s ON r.brigade.brigadeId=s.brigade.brigadeId " +
            "WHERE s.empId = ?1 AND r.received >= ?2 AND r.returned <= ?3")
    List<RepairList> getWork(int emp, Date start, Date end);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.RepairList(r.brigade, r.transport, r.sparePart, r.cost, r.received, r.returned) " +
            "FROM RepairList r INNER JOIN ServiceStaff s ON r.brigade.brigadeId=s.brigade.brigadeId " +
            "WHERE s.empId = ?1 AND r.transport.id = ?2 AND r.received >= ?3 AND r.returned <= ?4")
    List<RepairList> getWork(int emp, int t, Date start, Date end);

    RepairList findByRepId(int id);
}
