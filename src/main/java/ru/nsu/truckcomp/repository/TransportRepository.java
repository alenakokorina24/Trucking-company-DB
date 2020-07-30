package ru.nsu.truckcomp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Transport;
import ru.nsu.truckcomp.model.TransportArea;

import java.sql.Date;
import java.util.List;

@Transactional
public interface TransportRepository extends TransportBaseRepository<Transport> {
    Iterable<Transport> findByAcquirementDateAfterAndAcquirementDateBeforeOrDecommissionDateAfterAndDecommissionDateBefore(Date start1, Date end1, Date start2, Date end2);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.TransportArea(t.id, t.brand, t.garage, g.area) FROM Transport t INNER JOIN Garage g ON t.garage.garageId=g.garageId")
    List<TransportArea> getTransportDistribution();
}
