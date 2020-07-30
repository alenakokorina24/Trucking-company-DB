package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Brigade;

public interface BrigadeRepository extends CrudRepository<Brigade, Integer> {
    Iterable<Brigade> findByAreaAreaId(int areaId);

    Brigade findByBrigadeId(int id);
}
