package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Driver;

import java.util.List;

@Transactional
public interface DriverRepository extends EmployeeBaseRepository<Driver> {
    List<Driver> findByTransportIdAndBrigade_AreaAreaId(int transportId, int areaId);

    List<Driver> findByBrigade_AreaAreaId(int areaId);

    List<Driver> findByTransportId(int transportId);

    List<Driver> findAll();

    Driver findByEmpId(int id);
}

