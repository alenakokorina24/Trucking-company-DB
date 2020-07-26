package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Area;

public interface AreaRepository extends CrudRepository<Area, Integer> {
    Area findByAreaId(int id);
}
