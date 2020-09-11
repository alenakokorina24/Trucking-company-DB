package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.RepairList;

import java.util.Date;
import java.util.List;

public interface RepairListRepository extends CrudRepository<RepairList, Integer> {
    String SELECT_REPAIR_LIST = "SELECT new ru.nsu.truckcomp.model.RepairList(r.brigade, r.transport, r.sparePart, r.cost, r.received, r.returned) FROM RepairList r ";
    String REPAIR_LIST_JOIN_SERVICE_STAFF = "INNER JOIN ServiceStaff s ON r.brigade.brigadeId=s.brigade.brigadeId ";
    String REPAIR_LIST_JOIN_PASSENGER_TRANSPORT = "INNER JOIN PassengerTransport t ON r.transport.id=t.id ";
    String REPAIR_LIST_JOIN_TRUCK = "INNER JOIN Truck t ON r.transport.id=t.id ";

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_SERVICE_STAFF +
            "WHERE s.empId = ?1 AND r.received >= ?2 AND r.returned <= ?3")
    List<RepairList> getWork(int emp, Date start, Date end, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_SERVICE_STAFF +
            "WHERE s.empId = ?1 AND r.transport.id = ?2 AND r.received >= ?3 AND r.returned <= ?4")
    List<RepairList> getWork(int emp, int t, Date start, Date end, Pageable pageable);

    RepairList findByRepId(int id);

    List<RepairList> findByTransport_IdAndReceivedAfterAndReturnedBefore(int id, Date start, Date end, Pageable pageable);

    List<RepairList> findByTransport_BrandAndReceivedAfterAndReturnedBefore(String brand, Date start, Date end, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_PASSENGER_TRANSPORT +
                "WHERE r.received >= ?1 AND r.returned <= ?2")
    List<RepairList> getPassengerTransportRepairs(Date start, Date end, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_TRUCK +
            "WHERE r.received >= ?1 AND r.returned <= ?2")
    List<RepairList> getTruckRepairs(Date start, Date end, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_PASSENGER_TRANSPORT +
                "WHERE t.brand = ?1 AND r.received >= ?2 AND r.returned <= ?3")
    List<RepairList> getPassengerTransportRepairsWithBrand(String brand, Date start, Date end, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_TRUCK +
                "WHERE t.brand = ?1 AND r.received >= ?2 AND r.returned <= ?3")
    List<RepairList> getTruckRepairsWithBrand(String brand, Date start, Date end, Pageable pageable);

    List<RepairList> findAll(Pageable pageable);

    List<RepairList> findBySparePartAndReceivedAfterAndReturnedBefore(String sparePart, Date start, Date end, Pageable pageable);

    List<RepairList> findBySparePartAndTransport_IdAndReceivedAfterAndReturnedBefore(String sparePart, int id, Date start, Date end, Pageable pageable);

    List<RepairList> findBySparePartAndTransport_BrandAndReceivedAfterAndReturnedBefore(String sparePart, String Brand, Date start, Date end, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_PASSENGER_TRANSPORT +
                "WHERE t.brand = ?1 AND r.received >= ?2 AND r.returned <= ?3 AND r.sparePart = ?4")
    List<RepairList> getPassengerTransportSparePartsWithBrand(String brand, Date start, Date end, String sparePart, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_TRUCK +
            "WHERE t.brand = ?1 AND r.received >= ?2 AND r.returned <= ?3 AND r.sparePart = ?4")
    List<RepairList> getTruckSparePartsWithBrand(String brand, Date start, Date end, String sparePart, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_PASSENGER_TRANSPORT +
            "WHERE r.received >= ?1 AND r.returned <= ?2 AND r.sparePart = ?3")
    List<RepairList> getPassengerTransportSpareParts(Date start, Date end, String sparePart, Pageable pageable);

    @Query(value = SELECT_REPAIR_LIST + REPAIR_LIST_JOIN_TRUCK +
            "WHERE r.received >= ?1 AND r.returned <= ?2 AND r.sparePart = ?3")
    List<RepairList> getTruckSpareParts(Date start, Date end, String sparePart, Pageable pageable);
}
