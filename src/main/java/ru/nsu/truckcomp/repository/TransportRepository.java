package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Transport;

import java.sql.Date;

@Transactional
public interface TransportRepository extends TransportBaseRepository<Transport> {
    Iterable<Transport> findByAcquirementDateAfterAndAcquirementDateBeforeOrDecommissionDateAfterAndDecommissionDateBefore(Date start1, Date end1, Date start2, Date end2);
}
