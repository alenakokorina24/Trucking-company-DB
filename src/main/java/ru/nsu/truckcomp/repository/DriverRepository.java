package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Driver;

import java.util.List;

@Transactional
public interface DriverRepository extends EmployeeBaseRepository<Driver> {
    List<Driver> findByTransportIdAndBrigade_AreaAreaId(int transportId, int areaId, Pageable pageable);

    List<Driver> findByBrigade_AreaAreaId(int areaId, Pageable pageable);

    List<Driver> findByTransportId(int transportId, Pageable pageable);

    List<Driver> findAll(Pageable pageable);

    Driver findByEmpId(int id);
}

