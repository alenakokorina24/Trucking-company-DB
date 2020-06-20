package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Garage;

public interface GarageRepository extends CrudRepository<Garage, Integer> {

}

