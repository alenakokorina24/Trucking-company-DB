package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Area;

import java.util.List;

public interface AreaRepository extends CrudRepository<Area, Integer> {
    Area findByAreaId(int id);

    List<Area> findAll(Pageable pageable);
}
