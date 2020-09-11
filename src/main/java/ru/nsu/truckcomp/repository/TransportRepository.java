package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Transport;
import ru.nsu.truckcomp.model.TransportArea;

import java.sql.Date;
import java.util.List;

// перед началом исполнения методов начинается транзакция,
// после исполнения метода транзакция коммитится,
// в случае RuntimeException - откатывается
@Transactional
public interface TransportRepository extends TransportBaseRepository<Transport> {
    // ищем транспорт, который был приобретён либо списан в указанный период
    List<Transport> findByAcquirementDateAfterAndAcquirementDateBeforeOrDecommissionDateAfterAndDecommissionDateBefore(Date start1, Date end1, Date start2, Date end2, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.TransportArea(t.id, t.brand, t.garage, g.area) FROM Transport t INNER JOIN Garage g ON t.garage.garageId=g.garageId")
    List<TransportArea> getTransportDistribution(Pageable pageable);

    List<Transport> findAll(Pageable pageable);
}
