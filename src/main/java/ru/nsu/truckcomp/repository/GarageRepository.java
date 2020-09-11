package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Garage;

import java.util.List;

public interface GarageRepository extends CrudRepository<Garage, Integer> {
    Garage findByGarageId(int id);

    List<Garage> findAll(Pageable pageable);
}

