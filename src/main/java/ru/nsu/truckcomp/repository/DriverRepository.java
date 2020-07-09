package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Driver;

@Transactional
public interface DriverRepository extends EmployeeBaseRepository<Driver> {
    Iterable<Driver> findByTransportIdAndBrigade_AreaAreaId(int transportId, int areaId);
    Iterable<Driver> findByBrigade_AreaAreaId(int areaId);
    Iterable<Driver> findByTransportId(int transportId);
}

